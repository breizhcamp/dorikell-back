package org.breizhcamp.dorikell.infrastructure

import org.breizhcamp.dorikell.domain.entities.Desk
import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.DeskPort
import org.breizhcamp.dorikell.infrastructure.db.mappers.toDesk
import org.breizhcamp.dorikell.infrastructure.db.repos.DeskRepo
import org.springframework.stereotype.Component

@Component
class DeskAdapter(
    private val deskRepo: DeskRepo
): DeskPort {

    override fun getByPerson(person: Person): Desk =
        deskRepo.getByEventIdAndCode(person.event.id, person.code).toDesk()

    override fun getAllByEventId(eventId: Int): List<Desk> =
        deskRepo.getAllByEventId(eventId).map { it.toDesk() }

}

