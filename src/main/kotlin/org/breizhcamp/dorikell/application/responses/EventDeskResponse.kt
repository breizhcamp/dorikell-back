package org.breizhcamp.dorikell.application.responses

import java.util.*

data class EventDeskResponse(
    val id: UUID,
    val name: String,
    val codes: Pair<Int, Int>
)
