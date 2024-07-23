package org.breizhcamp.dorikell.infrastructure.db.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class DeskDB(
    @Id
    val id: UUID,
    val name: String,
    val firstCode: Int,
    val lastCode: Int
)
