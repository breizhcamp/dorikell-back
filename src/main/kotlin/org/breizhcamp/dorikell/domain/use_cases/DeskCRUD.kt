package org.breizhcamp.dorikell.domain.use_cases

import org.breizhcamp.dorikell.domain.entities.Desk
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

    fun getByPerson(person: Person): Desk = deskPort.getByPerson(person)
    fun getAllByEventId(eventId: Int): List<Desk> {
        if (eventPort.list().none { it.id == eventId }) {
            eventPort.import()
            if (eventPort.list().none { it.id == eventId }) {
                throw NotFoundException()
            }
        }

        return deskPort.getAllByEventId(eventId)
    }

}