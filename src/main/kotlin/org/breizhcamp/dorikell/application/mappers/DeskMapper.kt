package org.breizhcamp.dorikell.application.mappers

import org.breizhcamp.dorikell.application.responses.EventDeskResponse
import org.breizhcamp.dorikell.application.responses.GeneralDeskResponse
import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk

fun EventDesk.toResponse(): EventDeskResponse = EventDeskResponse(
    id = id,
    name = name,
    codes = codes
)

fun GeneralDesk.toResponse(): GeneralDeskResponse = GeneralDeskResponse(
    id = id,
    name = name
)