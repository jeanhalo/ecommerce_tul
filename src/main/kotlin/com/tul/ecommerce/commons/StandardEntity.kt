package com.tul.ecommerce.commons

import java.util.*
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class StandardEntity(
        @Id @Column(name = "id", length = 16, unique = true, nullable = false)
        val id: UUID = UUID.randomUUID(),
        @Version
        val version: Long? = null,
) {

    override fun equals(other: Any?) = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        id != (other as StandardEntity).id -> false
        else -> true
    }

    override fun hashCode(): Int = id.hashCode()

}