package org.breizhcamp.dorikell.infrastructure

import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk
import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.DeskPort
import org.breizhcamp.dorikell.infrastructure.db.mappers.toEventDesk
import org.breizhcamp.dorikell.infrastructure.db.mappers.toGeneralDesk
import org.breizhcamp.dorikell.infrastructure.db.repos.DeskRepo
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeskAdapter(
    private val deskRepo: DeskRepo
): DeskPort {

    override fun getByPerson(person: Person): EventDesk =
        deskRepo.getByEventIdAndCode(person.event.id, person.code).toEventDesk()

    override fun getAllByEventId(eventId: Int): List<EventDesk> =
        deskRepo.getAllByEventId(eventId).map { it.toEventDesk() }

    override fun getAll(): List<GeneralDesk> =
        deskRepo.findAll().map { it.toGeneralDesk() }


    override fun create(name: String): GeneralDesk =
        deskRepo.createByName(name).toGeneralDesk()

    override fun associate(eventId: Int, deskId: UUID, first: Int, last: Int): EventDesk {
        deskRepo.associate(eventId, deskId, first, last)

        return deskRepo.getByIdAndEventId(deskId, eventId).toEventDesk()
    }

    override fun dissociate(eventId: Int, deskId: UUID): GeneralDesk {
        deskRepo.dissociate(eventId, deskId)

        return deskRepo.findById(deskId).map { it.toGeneralDesk() }.orElseThrow()
    }

}

