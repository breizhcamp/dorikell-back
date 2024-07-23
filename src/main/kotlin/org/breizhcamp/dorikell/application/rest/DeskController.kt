package org.breizhcamp.dorikell.application.rest

import mu.KotlinLogging
import org.breizhcamp.dorikell.application.mappers.toResponse
import org.breizhcamp.dorikell.application.responses.DeskResponse
import org.breizhcamp.dorikell.domain.use_cases.DeskCRUD
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {  }

@RestController
@RequestMapping("/api/desks")
class DeskController(
    private val deskCRUD: DeskCRUD
) {

    @GetMapping("/{eventId}")
    fun listForEvent(@PathVariable eventId: Int): List<DeskResponse> {
        logger.info { "Retrieve all Desks in Event:$eventId" }

        return deskCRUD.getAllByEventId(eventId).map { it.toResponse() }
    }
}