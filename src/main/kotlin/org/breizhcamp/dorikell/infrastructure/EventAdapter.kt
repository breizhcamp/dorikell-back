package org.breizhcamp.dorikell.infrastructure

import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.domain.use_cases.ports.EventPort
import org.breizhcamp.dorikell.infrastructure.db.mappers.toDB
import org.breizhcamp.dorikell.infrastructure.db.mappers.toEvent
import org.breizhcamp.dorikell.infrastructure.db.repos.EventRepo
import org.breizhcamp.dorikell.infrastructure.http.kalon.EventKalonService
import org.springframework.stereotype.Component

@Component
class EventAdapter(
    private val eventRepo: EventRepo,
    private val eventKalonService: EventKalonService
): EventPort {
    override fun getById(id: Int): Event = eventRepo.findById(id).get().toEvent()

    override fun list(): List<Event> = eventRepo.findAll().map { it.toEvent() }

    override fun import() {
        val events = eventKalonService.import()

        eventRepo.saveAll(events.map { it.toDB() })
    }
}