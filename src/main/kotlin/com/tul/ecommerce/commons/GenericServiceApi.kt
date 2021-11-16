package com.tul.ecommerce.commons

import java.io.Serializable

/**
 * GenericServiceApi interface generica crud base.
 *
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
interface GenericServiceApi<T, ID : Serializable?> {

    /**
     * Guardar y actualizar entidad.
     *
     * @param entity
     * @return entity
     */
    fun save(entity: T): T

    /**
     * Eliminar entidad.
     *
     * @param id
     * @return entity
     */
    fun delete(id: ID)

    /**
     * Buscar por id una entidad.
     *
     * @param id
     * @return entity
     */
    operator fun get(id: ID): T?

    /**
     * Busca todos los registros de la entidad especificada.
     *
     * @return MutableList<Entidad>
     */
    val all: MutableList<T>?
}