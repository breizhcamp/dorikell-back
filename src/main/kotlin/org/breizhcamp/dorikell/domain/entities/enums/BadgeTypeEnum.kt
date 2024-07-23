package org.breizhcamp.dorikell.domain.entities.enums

enum class BadgeTypeEnum(label: String) {
    TWO_DAYS("2 Days"),
    THREE_DAYS("3 Days"),
    SPONSOR_THREE_DAYS("Sponsor 3 Days"),
    EXHIBITOR("Exhibitor"),
    STAFF("Staff"),
    SPEAKER("Speaker"),
    INVITATION("Invitation");

    companion object {
        fun getNumberOfDays(type: BadgeTypeEnum): Int =
            when (type) {
                TWO_DAYS -> 2
                THREE_DAYS,
                SPONSOR_THREE_DAYS,
                EXHIBITOR,
                STAFF,
                SPEAKER -> 3
                else -> 0
            }

        fun getFromBilletWebValue(billetWebValue: String): BadgeTypeEnum =
            when (billetWebValue.trim()) {
                "Pass 3 jours" -> THREE_DAYS
                "Pass 2 jours" -> TWO_DAYS
                "Invitation" -> INVITATION
                "Staff" -> STAFF
                "Orateur" -> SPEAKER
                "Exposant" -> EXHIBITOR
                "Pass 3 jours (Sponsors)" -> SPONSOR_THREE_DAYS
                else -> INVITATION
            }
    }
}