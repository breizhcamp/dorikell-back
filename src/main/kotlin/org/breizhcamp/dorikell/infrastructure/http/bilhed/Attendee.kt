package org.breizhcamp.dorikell.infrastructure.http.bilhed

import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.entities.enums.BadgeTypeEnum
import java.util.*

data class Attendee(
    val id: Int,
    val ticketType: String,
    val barcode: String,
    val lastname: String,
    val firstname: String,
    val company: String?
)

fun Attendee.toPerson(event: Event): Person = Person(
    id = UUID.randomUUID(),
    barcode = barcode,
    lastname = lastname,
    firstname = firstname,
    company = company,
    badgeType = BadgeTypeEnum.getFromBilletWebValue(ticketType),
    code = id,
    checkInDate = null,
    event = event,
)