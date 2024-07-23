package org.breizhcamp.dorikell.infrastructure.http.kalon

import org.breizhcamp.dorikell.infrastructure.http.utils.AuthorizedHttpClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange

interface EventKalonClient: AuthorizedHttpClient {

    @GetExchange("/api/events")
    fun getEventIds(): List<Int>

    @GetExchange("/api/events/{id}")
    fun getEventById(@PathVariable id: Int): KalonEvent

}