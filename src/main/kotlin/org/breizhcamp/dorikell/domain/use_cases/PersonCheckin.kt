package org.breizhcamp.dorikell.domain.use_cases

import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.PersonPort
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonCheckin(
    private val personPort: PersonPort
) {

    fun checkin(id: UUID): Person = personPort.checkin(id)

}