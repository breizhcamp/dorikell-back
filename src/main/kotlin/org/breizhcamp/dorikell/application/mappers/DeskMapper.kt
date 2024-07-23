package org.breizhcamp.dorikell.application.mappers

import org.breizhcamp.dorikell.application.responses.DeskResponse
import org.breizhcamp.dorikell.domain.entities.Desk

fun Desk.toResponse(): DeskResponse = DeskResponse(
    id = id,
    name = name,
    codes = codes
)