package org.breizhcamp.dorikell.domain.entities

import java.util.*

data class EventDesk(
    val id: UUID,
    val name: String,
    val codes: Pair<Int, Int>
)
