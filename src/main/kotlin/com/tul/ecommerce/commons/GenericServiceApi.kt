package com.tul.ecommerce.commons

import java.io.Serializable

interface GenericServiceApi<T, ID : Serializable?> {
    fun save(entity: T): T
    fun delete(id: ID)
    operator fun get(id: ID): T?
    val all: MutableList<T>?
}