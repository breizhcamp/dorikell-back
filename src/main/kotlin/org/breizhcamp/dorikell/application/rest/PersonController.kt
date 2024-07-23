package org.breizhcamp.dorikell.application.rest

import mu.KotlinLogging
import org.breizhcamp.dorikell.application.mappers.toResponse
import org.breizhcamp.dorikell.application.responses.PersonResponse
import org.breizhcamp.dorikell.domain.use_cases.DeskCRUD
import org.breizhcamp.dorikell.domain.use_cases.PersonCRUD
import org.breizhcamp.dorikell.domain.use_cases.PersonImport
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/api")
class PersonController(
    private val personCRUD: PersonCRUD,
    private val personImport: PersonImport,
    private val deskCRUD: DeskCRUD
) {

    @PostMapping("/search/{eventId}")
    fun search(@PathVariable eventId: Int, @RequestParam("q") query: String): List<PersonResponse> {
        logger.info { "Search person with query $query" }

        return personCRUD.search(eventId, query).map {
            it.toResponse(deskCRUD.getByPerson(it))
        }
    }

    @PostMapping("/import/{eventId}")
    fun importFromBilhed(@PathVariable eventId: Int): Int {
        logger.info { "Importing people from Bilhed for Event:$eventId" }

        return personImport.importForEvent(eventId)
    }

}