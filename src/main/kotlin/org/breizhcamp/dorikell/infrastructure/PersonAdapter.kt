package org.breizhcamp.dorikell.infrastructure

import org.breizhcamp.dorikell.domain.entities.Person
import org.breizhcamp.dorikell.domain.use_cases.ports.PersonPort
import org.breizhcamp.dorikell.infrastructure.elasticsearch.PersonRepo
import org.breizhcamp.dorikell.infrastructure.elasticsearch.mappers.toDocument
import org.breizhcamp.dorikell.infrastructure.elasticsearch.mappers.toPerson
import org.breizhcamp.dorikell.infrastructure.http.bilhed.Attendee
import org.breizhcamp.dorikell.infrastructure.http.bilhed.BilhedService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import kotlin.jvm.optionals.toList

@Component
class PersonAdapter(
    private val personRepo: PersonRepo,
    private val bilhedService: BilhedService
) : PersonPort {

    override fun getById(id: UUID): Person =
        personRepo.findById(id).get().toPerson()

    override fun search(eventId: Int, query: String): List<Person> {
        return try {
            personRepo.findById(UUID.fromString(query)).toList().map { it.toPerson() }
        } catch (e: IllegalArgumentException) {
            if (query.toIntOrNull() != null)
                personRepo.findByCodeAndEvent_Id(query.toInt(), eventId).toList().map { it.toPerson() }
            else if (query.toBigIntegerOrNull() != null)
                personRepo.findByBarcodeAndEvent_Id(query, eventId).toList().map { it.toPerson() }
            else personRepo.findAllByQueryAndEvent_Id(query, eventId).map { it.toPerson() }
        }
    }


    override fun import(people: List<Person>) {
        personRepo.saveAll(people.map { it.toDocument() })
    }

    override fun checkin(id: UUID): Person {
        val person = personRepo.findById(id).get()
        val updatedPerson = person.copy(checkin = LocalDateTime.now())

        return personRepo.save(updatedPerson).toPerson()
    }

    override fun getAllFromBilhed(): List<Attendee> =
        bilhedService.list()

}