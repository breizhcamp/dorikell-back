package org.breizhcamp.dorikell.domain.use_cases

import mu.KotlinLogging
import org.breizhcamp.dorikell.domain.use_cases.ports.EventPort
import org.breizhcamp.dorikell.domain.use_cases.ports.PersonPort
import org.breizhcamp.dorikell.infrastructure.http.bilhed.toPerson
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {  }

@Service
class PersonImport(
    private val personPort: PersonPort,
    private val eventPort: EventPort
) {

    fun importForEvent(eventId: Int): Int {
        if (eventPort.list().none { it.id == eventId }) {
            eventPort.import()
            if (eventPort.list().none { it.id == eventId }) {
                throw ChangeSetPersister.NotFoundException()
            }
        }

        val event = eventPort.getById(eventId)
        val attendees = personPort.getAllFromBilhed()

        personPort.import(attendees.map { it.toPerson(event) })

        logger.info { "Imported [${attendees.size}] attendees" }
        return attendees.size
    }

}