package org.breizhcamp.dorikell.application.ws

import mu.KotlinLogging
import org.breizhcamp.dorikell.application.mappers.toResponse
import org.breizhcamp.dorikell.application.responses.PersonResponse
import org.breizhcamp.dorikell.domain.use_cases.DeskCRUD
import org.breizhcamp.dorikell.domain.use_cases.PersonCheckin
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.*

private val logger = KotlinLogging.logger {  }

@Controller
class CheckInOutController(
    private val personCheckin: PersonCheckin,
    private val deskCRUD: DeskCRUD
) {

    @MessageMapping("/checkin")
    @SendTo("/topic/checkin")
    fun broadcastCheckin(id: UUID): PersonResponse {
        logger.info { "Broadcast Person:${id}'s checkin" }

        val person = personCheckin.checkin(id)
        val desk = deskCRUD.getByPerson(person)

        return person.toResponse(desk)
    }

    @MessageMapping("/remove")
    @SendTo("/topic/remove")
    fun broadcastRemove(id: UUID): UUID {
        logger.info { "Remove Person:$id from all screens" }
        return id
    }
}