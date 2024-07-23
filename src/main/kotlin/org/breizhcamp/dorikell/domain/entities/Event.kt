package org.breizhcamp.dorikell.domain.entities

import java.time.LocalDate

data class Event(
    val id: Int,
    val eventStart: LocalDate?
)
