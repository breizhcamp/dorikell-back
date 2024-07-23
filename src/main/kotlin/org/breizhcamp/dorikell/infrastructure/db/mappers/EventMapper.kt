package org.breizhcamp.dorikell.infrastructure.db.mappers

import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.infrastructure.db.model.EventDB

fun EventDB.toEvent(): Event = Event(
    id = id,
    eventStart = eventStart
)

fun Event.toDB(): EventDB = EventDB(
    id = id,
    eventStart = eventStart
)