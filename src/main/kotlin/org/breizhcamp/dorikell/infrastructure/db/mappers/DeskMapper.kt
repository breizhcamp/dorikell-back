package org.breizhcamp.dorikell.infrastructure.db.mappers

import org.breizhcamp.dorikell.domain.entities.Desk
import org.breizhcamp.dorikell.infrastructure.db.model.DeskDB

fun DeskDB.toDesk(): Desk = Desk(
    id = id,
    name = name,
    codes = Pair(firstCode, lastCode)
)