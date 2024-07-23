package org.breizhcamp.dorikell.infrastructure.db.repos

import org.breizhcamp.dorikell.infrastructure.db.model.DeskDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface DeskRepo: JpaRepository<DeskDB, UUID> {

    @Query("""
        SELECT desk.id, desk.name, codes.first_code, codes.last_code 
        FROM public.desk 
        LEFT JOIN public.codes codes ON desk.id = codes.desk_id
        WHERE codes.event_id = ?1
        AND ?2 BETWEEN codes.first_code AND codes.last_code
    """, nativeQuery = true)
    fun getByEventIdAndCode(eventId: Int, code: Int): DeskDB

    @Query("""
        SELECT desk.id, desk.name, codes.first_code, codes.last_code
        FROM public.desk desk
        LEFT JOIN public.codes codes on desk.id = codes.desk_id
        WHERE codes.event_id = ?1
    """, nativeQuery = true)
    fun getAllByEventId(eventId: Int): List<DeskDB>

}