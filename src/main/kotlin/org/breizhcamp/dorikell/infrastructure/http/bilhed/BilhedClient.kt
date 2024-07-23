package org.breizhcamp.dorikell.infrastructure.http.bilhed

import org.breizhcamp.dorikell.infrastructure.http.utils.AuthorizedHttpClient
import org.springframework.web.service.annotation.GetExchange

interface BilhedClient: AuthorizedHttpClient {

    @GetExchange("/admin/attendees/export")
    fun getAttendeesCSV(): String

}