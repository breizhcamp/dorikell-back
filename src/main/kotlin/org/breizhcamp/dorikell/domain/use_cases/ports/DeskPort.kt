package org.breizhcamp.dorikell.domain.use_cases.ports

import org.breizhcamp.dorikell.domain.entities.Desk
import org.breizhcamp.dorikell.domain.entities.Person

interface DeskPort {

    fun getByPerson(person: Person): Desk
    fun getAllByEventId(eventId: Int): List<Desk>

}