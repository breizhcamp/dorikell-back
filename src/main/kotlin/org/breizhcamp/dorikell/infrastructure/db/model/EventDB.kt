package org.breizhcamp.dorikell.infrastructure.db.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "event")
data class EventDB(
    @Id
    val id: Int,
    @Column(name = "event_start")
    @Temporal(TemporalType.DATE)
    val eventStart: LocalDate?
)
