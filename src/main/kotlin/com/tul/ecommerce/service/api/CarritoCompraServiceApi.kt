package com.tul.ecommerce.service.api

import com.tul.ecommerce.commons.GenericServiceApi
import com.tul.ecommerce.dto.CheckoutDto
import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.model.CarritoCompra
import java.util.*

/**
 * CarritoCompraServiceApi interface de carritoCompraService.
 *
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
interface CarritoCompraServiceApi : GenericServiceApi<CarritoCompra, UUID> {
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
     * Realizar checkout de una compra especifica en estado pendiente por codigo de compra.
     *
     * @param codigoCompra codigo de la compra.
     * @return CheckoutDto? datos finales de la compra.
     */
    fun checkout(codigoCompra: String) : CheckoutDto?

    /**
     * Busca carrito de compra por id y estado.
     *
     * @param id el identificador del carrito.
     * @param estadoCarrito estado Enum.
     * @return CarritoCompra?.
     */
    fun findByIdAndEstadoCarrito(id: UUID, estadoCarrito: EstadoCarrito) : CarritoCompra?
}