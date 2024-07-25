package org.breizhcamp.dorikell.infrastructure.db.mappers

import org.breizhcamp.dorikell.domain.entities.EventDesk
import org.breizhcamp.dorikell.domain.entities.GeneralDesk
import org.breizhcamp.dorikell.infrastructure.db.model.DeskDB

fun DeskDB.toEventDesk(): EventDesk = EventDesk(
    id = id,
    name = name,
    codes = Pair(firstCode, lastCode)
)

fun DeskDB.toGeneralDesk(): GeneralDesk = GeneralDesk(
    id = id,
    name = name
)