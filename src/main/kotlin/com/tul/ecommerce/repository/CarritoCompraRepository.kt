package com.tul.ecommerce.repository

import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.model.CarritoCompra
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
interface CarritoCompraRepository : CrudRepository<CarritoCompra, UUID> {
    /**
     * Busca carrito de compra por codigoCompra, productoId y estadoCarrito.
     *
     * @param codigoCompra codigo de la compra.
     * @param productoId productoId especifico a buscar.
     * @param estadoCarrito estado Enum.
     * @return CarritoCompra?
     */
    fun findByCodigoCompraAndProductoIdAndEstadoCarrito(
        codigoCompra: String, productoId: UUID, estadoCarrito: EstadoCarrito
    ) : CarritoCompra?

    /**
     * Buscar todos los resultados por codigo de compra.
     *
     * @param codigoCompra codigo de la compra.
     * @return MutableList<CarritoCompra>?
     */
    fun findAllByCodigoCompra(codigoCompra: String) : MutableList<CarritoCompra>?

    /**
     * Busca carrito de compra por id y estado.
     *
     * @param id el identificador del carrito.
     * @param estadoCarrito estado Enum.
     * @return CarritoCompra?.
     */
    fun findByIdAndEstadoCarrito(id: UUID, estadoCarrito: EstadoCarrito) : CarritoCompra?

}