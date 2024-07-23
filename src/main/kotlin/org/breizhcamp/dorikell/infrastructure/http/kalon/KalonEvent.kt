package org.breizhcamp.dorikell.infrastructure.http.kalon

import org.breizhcamp.dorikell.domain.entities.Event
import java.time.LocalDate

data class KalonEvent(
    val id: Int,
    val debutEvent: LocalDate?
)

fun KalonEvent.toEvent() = Event(
    id = id,
    eventStart = debutEvent
)
