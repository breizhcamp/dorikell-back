package org.breizhcamp.dorikell.infrastructure.http.kalon

import mu.KotlinLogging
import org.breizhcamp.dorikell.config.DorikellConfig
import org.breizhcamp.dorikell.domain.entities.Event
import org.breizhcamp.dorikell.infrastructure.http.utils.createAuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {  }

@Service
class EventKalonService(
    config: DorikellConfig,
    authorizedClientManager: OAuth2AuthorizedClientManager
) {
    private val kalonClient =
        createAuthorizedClient<EventKalonClient>(
            authorizedClientManager,
            config.kalon
        )

    fun import(): List<Event> {
        logger.info { "Importing all Event Ids from Kalon" }
        val eventIds = kalonClient.getEventIds()

        logger.info { "Importing [${eventIds.size}] Events from Kalon" }
        return eventIds.map { id ->
            kalonClient.getEventById(id)
                .toEvent()
        }
    }

}