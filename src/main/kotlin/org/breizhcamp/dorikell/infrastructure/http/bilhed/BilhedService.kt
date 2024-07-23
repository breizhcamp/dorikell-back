package org.breizhcamp.dorikell.infrastructure.http.bilhed

import mu.KotlinLogging
import org.apache.commons.csv.CSVFormat
import org.breizhcamp.dorikell.config.DorikellConfig
import org.breizhcamp.dorikell.infrastructure.http.utils.createAuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {  }

@Service
class BilhedService(
    config: DorikellConfig,
    authorizedClientManager: OAuth2AuthorizedClientManager
) {
    private val bilhedClient =
        createAuthorizedClient<BilhedClient>(
            authorizedClientManager,
            config.bilhed
        )

    fun list(): List<Attendee> {
        logger.info { "Importing attendees from Bilhed" }

        val input = bilhedClient.getAttendeesCSV()

        return CSVFormat
            .Builder
            .create()
            .apply {
                setIgnoreEmptyLines(true)
                setIgnoreSurroundingSpaces(true)
            }
            .build()
            .parse(input.reader())
            .drop(1)
            .map {
                Attendee(
                    id = it[0].toInt(),
                    ticketType = it[1],
                    barcode = it[2],
                    lastname = it[3],
                    firstname = it[4],
                    company = it[6]
                )
            }
    }

}