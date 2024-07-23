package org.breizhcamp.dorikell.application.responses

import org.breizhcamp.dorikell.domain.entities.enums.BadgeTypeEnum
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class PersonResponse(
    val id: UUID,
    val code: Int,
    val barcode: String,
    val lastname: String,
    val firstname: String,
    val company: String?,
    val badgeType: BadgeTypeEnum,
    val days: List<LocalDate>,
    val desk: DeskResponse,
    val checkInDate: LocalDateTime?
)