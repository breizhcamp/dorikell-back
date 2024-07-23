package org.breizhcamp.dorikell.infrastructure.elasticsearch.mappers

import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.infrastructure.elasticsearch.model.EventDocument

fun Event.toDocument(): EventDocument = EventDocument(
    id = id,
    eventStart = eventStart
)

fun EventDocument.toEvent(): Event = Event(
    id = id,
    eventStart = eventStart
)