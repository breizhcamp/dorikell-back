package org.breizhcamp.dorikell.application.mappers

import org.breizhcamp.dorikell.application.responses.PersonResponse
import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.entities.enums.BadgeTypeEnum
import java.time.LocalDate

fun Person.toResponse(desk: EventDesk): PersonResponse =
    PersonResponse(
        id = id,
        code = code,
        barcode = barcode,
        lastname = lastname,
        firstname = firstname,
        company = company,
        badgeType = badgeType,
        days = daysFromEventAndBadgeType(event, badgeType),
        desk = desk.toResponse(),
        checkInDate = checkInDate
    )

private fun daysFromEventAndBadgeType(event: Event, badgeType: BadgeTypeEnum): List<LocalDate> {
    val firstDay = requireNotNull(event.eventStart)
    return (0..<BadgeTypeEnum.getNumberOfDays(badgeType))
        .map { daysToAdd ->
            firstDay.plusDays(daysToAdd.toLong())
        }
}
