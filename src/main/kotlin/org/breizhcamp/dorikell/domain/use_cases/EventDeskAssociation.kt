package org.breizhcamp.dorikell.domain.use_cases

import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk
import org.breizhcamp.dorikell.domain.use_cases.ports.DeskPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventDeskAssociation(
    private val deskPort: DeskPort
) {

    fun associate(eventId: Int, deskId: UUID, first: Int, last: Int): EventDesk =
        deskPort.associate(eventId, deskId, first, last)

    fun dissociate(eventId: Int, deskId: UUID): GeneralDesk =
        deskPort.dissociate(eventId, deskId)

}