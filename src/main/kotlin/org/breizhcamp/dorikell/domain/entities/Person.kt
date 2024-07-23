package org.breizhcamp.dorikell.domain.entities

import org.breizhcamp.dorikell.domain.entities.enums.BadgeTypeEnum
import java.time.LocalDateTime
import java.util.*

data class Person(
    val id: UUID,
    val barcode: String,
    val lastname: String,
    val firstname: String,
    val company: String?,
    val badgeType: BadgeTypeEnum,
    val code: Int,
    val checkInDate: LocalDateTime?,
    val event: Event
)
