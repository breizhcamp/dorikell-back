package org.breizhcamp.dorikell.application.requests

import java.util.*

data class AssociateRequest(
    val deskId: UUID,
    val first: Int,
    val last: Int
)
