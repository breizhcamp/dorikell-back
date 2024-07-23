package org.breizhcamp.dorikell.infrastructure.elasticsearch.mappers

import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.infrastructure.elasticsearch.model.PersonDocument

fun Person.toDocument(): PersonDocument = PersonDocument(
    id = id,
    barcode = barcode,
    lastname = lastname,
    firstname = firstname,
    company = company,
    badge = badgeType,
    code = code,
    checkin = checkInDate,
    event = event.toDocument()
)

fun PersonDocument.toPerson(): Person = Person(
    id = id,
    barcode = barcode,
    lastname = lastname,
    firstname = firstname,
    company = company,
    badgeType = badge,
    code = code,
    checkInDate = checkin,
    event = event.toEvent()
)