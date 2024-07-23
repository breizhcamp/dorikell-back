package org.breizhcamp.dorikell.domain.use_cases

import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.PersonPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonCRUD(
    private val personPort: PersonPort
) {

    fun getById(id: UUID): Person = personPort.getById(id)
    fun search(eventId: Int, query: String): List<Person> = personPort.search(eventId, query)

}