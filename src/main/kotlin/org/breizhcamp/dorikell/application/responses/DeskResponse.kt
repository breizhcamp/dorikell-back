package org.breizhcamp.dorikell.application.responses

import java.util.*

data class DeskResponse(
    val id: UUID,
    val name: String,
    val codes: Pair<Int, Int>
)
