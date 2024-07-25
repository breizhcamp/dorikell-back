package org.breizhcamp.dorikell.infrastructure.db.repos

import org.breizhcamp.dorikell.infrastructure.db.model.DeskDB
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*

interface DeskRepo: JpaRepository<DeskDB, UUID> {

    @Query("""
        SELECT desk.id, desk.name, 0, 0 FROM desk
    """, nativeQuery = true)
    override fun findAll(): MutableList<DeskDB>

    @Query("""
        SELECT desk.id, desk.name, 0, 0 FROM desk WHERE id = ?1
    """, nativeQuery = true)
    override fun findById(id: UUID): Optional<DeskDB>

    @Query("""
        SELECT d.id, d.name, c.first_code, c.last_code
        FROM desk d LEFT JOIN codes c on d.id = c.desk_id
    """, nativeQuery = true)
    fun getByIdAndEventId(id: UUID, eventId: Int): DeskDB

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

    @Modifying
    @Query("""
        INSERT INTO desk(name) VALUES (?1)
        RETURNING desk.id, desk.name, 0, 0
    """, nativeQuery = true)
    fun createByName(name: String): DeskDB

    @Modifying
    @Query("""
        INSERT INTO codes(event_id, desk_id, first_code, last_code)
        VALUES (?1, ?2, ?3, ?4)
    """, nativeQuery = true)
    fun associate(eventId: Int, deskId: UUID, firstCode: Int, lastCode: Int)

    @Modifying
    @Query("""
        DELETE FROM codes WHERE event_id = ?1 AND desk_id = ?2
    """, nativeQuery = true)
    fun dissociate(eventId: Int, deskId: UUID)

}