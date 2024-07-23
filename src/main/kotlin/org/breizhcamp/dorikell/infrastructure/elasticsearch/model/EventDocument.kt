package org.breizhcamp.dorikell.infrastructure.elasticsearch.model

import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

data class EventDocument(
    @Id
    val id: Int,
    @Field(type = FieldType.Date, format = [DateFormat.date])
    val eventStart: LocalDate?
)
