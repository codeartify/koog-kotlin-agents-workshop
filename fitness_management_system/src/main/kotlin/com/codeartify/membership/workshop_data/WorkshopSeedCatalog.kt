package com.codeartify.membership.workshop_data

import com.codeartify.membership.managing_memberships.domain.values.MembershipStatus
import java.time.LocalDate

object WorkshopSeedCatalog {
    val plans = listOf(
        WorkshopPlanSeed(
            id = "10000000-0000-4000-8000-000000000001",
            title = "Workshop Monthly",
            description = "Flexible one-month plan used by the workshop cases.",
            price = 139,
            durationInMonths = 1
        ),
        WorkshopPlanSeed(
            id = "10000000-0000-4000-8000-000000000003",
            title = "Workshop Quarterly",
            description = "Three-month plan used by the workshop cases.",
            price = 349,
            durationInMonths = 3
        ),
        WorkshopPlanSeed(
            id = "10000000-0000-4000-8000-000000000006",
            title = "Workshop Half-year",
            description = "Six-month plan used by the workshop cases.",
            price = 599,
            durationInMonths = 6
        ),
        WorkshopPlanSeed(
            id = "10000000-0000-4000-8000-000000000012",
            title = "Workshop Annual",
            description = "Twelve-month plan used by the workshop cases.",
            price = 999,
            durationInMonths = 12
        )
    )

    val memberships = listOf(
        case(
            number = 1,
            name = "Maya Example",
            email = "maya.example@workshop.test",
            dateOfBirth = LocalDate.of(1992, 5, 14),
            plan = plans[2],
            expectedStatus = MembershipStatus.SUSPENDED,
            Pause(30), Resume, Suspend
        ),
        case(
            number = 2,
            name = "Liam Sample",
            email = "liam.sample@workshop.test",
            dateOfBirth = LocalDate.of(1985, 2, 9),
            plan = plans[0],
            expectedStatus = MembershipStatus.ACTIVE
        ),
        case(
            number = 3,
            name = "Noah Scenario",
            email = "noah.scenario@workshop.test",
            dateOfBirth = LocalDate.of(1978, 11, 3),
            plan = plans[1],
            expectedStatus = MembershipStatus.PAUSED,
            Pause(45)
        ),
        case(
            number = 4,
            name = "Olivia Demo",
            email = "olivia.demo@workshop.test",
            dateOfBirth = LocalDate.of(1996, 7, 21),
            plan = plans[3],
            expectedStatus = MembershipStatus.CANCELLED,
            Cancel
        ),
        case(
            number = 5,
            name = "Ethan Fixture",
            email = "ethan.fixture@workshop.test",
            dateOfBirth = LocalDate.of(1990, 1, 30),
            plan = plans[2],
            expectedStatus = MembershipStatus.ACTIVE,
            Suspend, Reactivate
        ),
        case(
            number = 6,
            name = "Sophia Synthetic",
            email = "sophia.synthetic@workshop.test",
            dateOfBirth = LocalDate.of(1988, 9, 12),
            plan = plans[1],
            expectedStatus = MembershipStatus.ACTIVE,
            Pause(30), Resume
        ),
        case(
            number = 7,
            name = "Ava Testcase",
            email = "ava.testcase@workshop.test",
            dateOfBirth = LocalDate.of(1994, 4, 6),
            plan = plans[0],
            expectedStatus = MembershipStatus.CANCELLED,
            Pause(60), Cancel
        ),
        case(
            number = 8,
            name = "Lucas Example",
            email = "lucas.example@workshop.test",
            dateOfBirth = LocalDate.of(1983, 12, 18),
            plan = plans[3],
            expectedStatus = MembershipStatus.CANCELLED,
            Suspend, Cancel
        ),
        case(
            number = 9,
            name = "Emma Sample",
            email = "emma.sample@workshop.test",
            dateOfBirth = LocalDate.of(1998, 6, 2),
            plan = plans[2],
            expectedStatus = MembershipStatus.SUSPENDED,
            Suspend
        ),
        case(
            number = 10,
            name = "Mateo Scenario",
            email = "mateo.scenario@workshop.test",
            dateOfBirth = LocalDate.of(1981, 3, 27),
            plan = plans[1],
            expectedStatus = MembershipStatus.PAUSED,
            Pause(30)
        ),
        case(
            number = 11,
            name = "Isabella Demo",
            email = "isabella.demo@workshop.test",
            dateOfBirth = LocalDate.of(1991, 10, 8),
            plan = plans[3],
            expectedStatus = MembershipStatus.ACTIVE
        ),
        case(
            number = 12,
            name = "James Fixture",
            email = "james.fixture@workshop.test",
            dateOfBirth = LocalDate.of(1975, 8, 16),
            plan = plans[0],
            expectedStatus = MembershipStatus.CANCELLED,
            Pause(30), Resume, Suspend, Reactivate, Cancel
        )
    )

    private fun case(
        number: Int,
        name: String,
        email: String,
        dateOfBirth: LocalDate,
        plan: WorkshopPlanSeed,
        expectedStatus: MembershipStatus,
        vararg lifecycle: WorkshopLifecycleStep
    ) = WorkshopMembershipSeed(
        membershipId = "20000000-0000-4000-8000-${number.toString().padStart(12, '0')}",
        customerId = "workshop-customer-${number.toString().padStart(2, '0')}",
        customerName = name,
        customerEmail = email,
        customerDateOfBirth = dateOfBirth,
        plan = plan,
        expectedStatus = expectedStatus,
        lifecycle = lifecycle.toList()
    )
}

data class WorkshopPlanSeed(
    val id: String,
    val title: String,
    val description: String,
    val price: Int,
    val durationInMonths: Int
)

data class WorkshopMembershipSeed(
    val membershipId: String,
    val customerId: String,
    val customerName: String,
    val customerEmail: String,
    val customerDateOfBirth: LocalDate,
    val plan: WorkshopPlanSeed,
    val expectedStatus: MembershipStatus,
    val lifecycle: List<WorkshopLifecycleStep>
)

sealed interface WorkshopLifecycleStep

data class Pause(val durationInDays: Int) : WorkshopLifecycleStep

data object Resume : WorkshopLifecycleStep

data object Suspend : WorkshopLifecycleStep

data object Reactivate : WorkshopLifecycleStep

data object Cancel : WorkshopLifecycleStep
