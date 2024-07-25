package org.breizhcamp.dorikell.domain.use_cases.ports

import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk
import org.breizhcamp.dorikell.domain.entities.Person
import java.util.*

interface DeskPort {

    fun getByPerson(person: Person): EventDesk
    fun getAllByEventId(eventId: Int): List<EventDesk>
    fun getAll(): List<GeneralDesk>
    fun create(name: String): GeneralDesk
    fun associate(eventId: Int, deskId: UUID, first: Int, last: Int): EventDesk
    fun dissociate(eventId: Int, deskId: UUID): GeneralDesk

}