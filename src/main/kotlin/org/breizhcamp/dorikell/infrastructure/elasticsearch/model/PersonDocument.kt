package org.breizhcamp.dorikell.infrastructure.elasticsearch.model

import jakarta.persistence.Id
import org.breizhcamp.dorikell.domain.entities.enums.BadgeTypeEnum
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime
import java.util.*

@Document(indexName = "participants")
data class PersonDocument(
    @Id
    @Field(type = FieldType.Text)
    val id: UUID,
    val barcode: String,
    val lastname: String,
    val firstname: String,
    val company: String?,
    @Field(type = FieldType.Text)
    val badge: BadgeTypeEnum,
    val code: Int,
    @Field(type = FieldType.Date, format = [DateFormat.date_time])
    val checkin: LocalDateTime?,
    @Field(type = FieldType.Nested, includeInParent = true)
    val event: EventDocument
)
