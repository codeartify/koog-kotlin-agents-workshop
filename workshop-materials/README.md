# Workshop presentation

`building-ai-agents-koog-kotlin.md` is the source-of-truth slide deck in Marp Markdown. It uses the established
Codeartify visual language and includes detailed speaker notes plus source URLs.

The notes are also a self-study guide. Each slide continues the same membership-assistant story through five parts:

1. story so far and the failure exposed by the previous increment
2. definitions of the new Koog or architecture concepts
3. a concrete fitness-management example
4. the relevant exercise branch and Kotlin files
5. the transition that creates the need for the next slide

Read the visible slide first, then its HTML comment block, then compare the referenced branch and code. The comments
do not appear on the rendered slide, but presentation tools can use them as speaker notes.

Render it to PDF with a local Marp CLI installation:

```bash
marp --allow-local-files --pdf workshop-materials/building-ai-agents-koog-kotlin.md
```

Or use the Marp extension in Visual Studio Code and choose **Export Slide Deck → PDF**.
