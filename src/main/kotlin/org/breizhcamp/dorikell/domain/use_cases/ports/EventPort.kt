package org.breizhcamp.dorikell.domain.use_cases.ports

import org.breizhcamp.dorikell.domain.entities.Event

interface EventPort {

    fun getById(id: Int): Event
    fun list(): List<Event>
    fun import()

}