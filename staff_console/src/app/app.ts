import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

type MembershipAction = 'PAUSE' | 'RESUME' | 'REACTIVATE' | 'CANCEL';

interface ToolCallTrace {
  tool: string;
  result: string;
}

interface MembershipCaseAssessment {
  membershipId: string | null;
  currentStatus: 'ACTIVE' | 'PAUSED' | 'SUSPENDED' | 'CANCELLED' | null;
  summary: string;
  relevantEvidence: string[];
  possibleActions: MembershipAction[];
  proposedAction: MembershipAction | null;
  requiresHumanConfirmation: boolean;
  warnings: string[];
}

interface StaffAssistantResponse {
  conversationId: string;
  assessment: MembershipCaseAssessment;
  trace: ToolCallTrace[];
}

interface ConversationMessage {
  author: 'staff' | 'assistant';
  text: string;
}

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class App {
  private readonly http = inject(HttpClient);
  private readonly conversationId = crypto.randomUUID();

  protected readonly prompt = signal('');
  protected readonly pending = signal(false);
  protected readonly error = signal<string | null>(null);
  protected readonly messages = signal<ConversationMessage[]>([]);
  protected readonly result = signal<StaffAssistantResponse | null>(null);

  protected readonly examples = [
    'Find member Maya and explain the current membership status.',
    'What happened to this membership, and what can staff safely propose?',
    'Check whether an open invoice explains the suspension.',
  ];

  protected useExample(example: string): void {
    this.prompt.set(example);
  }

  protected async send(): Promise<void> {
    const message = this.prompt().trim();
    if (!message || this.pending()) return;

    this.pending.set(true);
    this.error.set(null);
    this.prompt.set('');
    this.messages.update((messages) => [...messages, { author: 'staff', text: message }]);

    try {
      const response = await firstValueFrom(
        this.http.post<StaffAssistantResponse>(
          `/api/staff-assistant/conversations/${this.conversationId}/messages`,
          { message },
        ),
      );
      this.result.set(response);
      this.messages.update((messages) => [
        ...messages,
        { author: 'assistant', text: response.assessment.summary },
      ]);
    } catch (error) {
      this.error.set(this.describeError(error));
    } finally {
      this.pending.set(false);
    }
  }

  private describeError(error: unknown): string {
    if (error instanceof HttpErrorResponse && error.status === 503) {
      return 'No LLM provider is configured. Add GOOGLE_API_KEY to the workshop .env file and restart the stack.';
    }
    return 'The assistant could not complete this request. Check the backend logs and try again.';
  }
}
