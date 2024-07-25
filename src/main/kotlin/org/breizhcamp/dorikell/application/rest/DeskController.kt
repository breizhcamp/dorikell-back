package org.breizhcamp.dorikell.application.rest

import mu.KotlinLogging
import org.breizhcamp.dorikell.application.mappers.toResponse
import org.breizhcamp.dorikell.application.requests.AssociateRequest
import org.breizhcamp.dorikell.application.responses.EventDeskResponse
import org.breizhcamp.dorikell.application.responses.GeneralDeskResponse
import org.breizhcamp.dorikell.domain.use_cases.DeskCRUD
import org.breizhcamp.dorikell.domain.use_cases.EventDeskAssociation
import org.springframework.web.bind.annotation.*
import java.util.*

private val logger = KotlinLogging.logger {  }

@RestController
@RequestMapping("/api/desks")
class DeskController(
    private val deskCRUD: DeskCRUD,
    private val deskAssoc: EventDeskAssociation
) {

    @GetMapping
    fun listAll() : List<GeneralDeskResponse> {
        logger.info { "Retrieve all Desks" }

        return deskCRUD.getAll().map { it.toResponse() }
    }

    @GetMapping("/{eventId}")
    fun listForEvent(@PathVariable eventId: Int): List<EventDeskResponse> {
        logger.info { "Retrieve all Desks in Event:$eventId" }

        return deskCRUD.getAllByEventId(eventId).map { it.toResponse() }
    }

    @PostMapping
    fun create(@RequestBody name: String): GeneralDeskResponse {
        logger.info { "Create a Desk with name=$name" }

        return deskCRUD.create(name).toResponse()
    }

    @PostMapping("/{eventId}")
    fun associateDeskToEvent(@PathVariable eventId: Int, @RequestBody req: AssociateRequest): EventDeskResponse {
        logger.info { "Associate Desk:${req.deskId} to Event:$eventId with codes ${req.first} to ${req.last}" }

        return deskAssoc.associate(eventId, req.deskId, req.first, req.last).toResponse()
    }

    @DeleteMapping("/{eventId}/{deskId}")
    fun dissociateDeskFromEvent(@PathVariable eventId: Int, @PathVariable deskId: UUID): GeneralDeskResponse {
        logger.info { "Dissociate Desk:$deskId from Event:$eventId" }

        return deskAssoc.dissociate(eventId, deskId).toResponse()
    }

}