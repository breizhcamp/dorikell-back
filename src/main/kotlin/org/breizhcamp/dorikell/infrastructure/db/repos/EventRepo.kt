package org.breizhcamp.dorikell.infrastructure.db.repos

import org.breizhcamp.dorikell.infrastructure.db.model.EventDB
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepo: JpaRepository<EventDB, Int>