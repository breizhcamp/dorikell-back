package org.breizhcamp.dorikell.domain.use_cases.ports

import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.infrastructure.http.bilhed.Attendee
import java.util.*

interface PersonPort {

    fun getById(id: UUID): Person
    fun search(eventId: Int, query: String): List<Person>
    fun import(people: List<Person>)
    fun checkin(id: UUID): Person
    fun getAllFromBilhed(): List<Attendee>
    
}