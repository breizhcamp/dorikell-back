package org.breizhcamp.dorikell.domain.use_cases

import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk
import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.DeskPort
import org.breizhcamp.dorikell.domain.use_cases.ports.EventPort
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class DeskCRUD(
    private val deskPort: DeskPort,
    private val eventPort: EventPort
) {

    fun getByPerson(person: Person): EventDesk = deskPort.getByPerson(person)
    fun getAllByEventId(eventId: Int): List<EventDesk> {
        if (eventPort.list().none { it.id == eventId }) {
            eventPort.import()
            if (eventPort.list().none { it.id == eventId }) {
                throw NotFoundException()
            }
        }

        return deskPort.getAllByEventId(eventId)
    }

    fun getAll(): List<GeneralDesk> =
        deskPort.getAll()

    fun create(name: String): GeneralDesk =
        deskPort.create(name)
}