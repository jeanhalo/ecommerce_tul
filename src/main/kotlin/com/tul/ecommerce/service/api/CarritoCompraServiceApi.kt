package com.tul.ecommerce.service.api

import com.tul.ecommerce.commons.GenericServiceApi
import com.tul.ecommerce.dto.CheckoutDto
import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.model.CarritoCompra
import java.util.*

interface CarritoCompraServiceApi : GenericServiceApi<CarritoCompra, UUID> {
    fun findByCodigoCompraAndProductoIdAndEstadoCarrito(
        codigoCompra: String, productoId: UUID, estadoCarrito: EstadoCarrito
    ) : CarritoCompra?

    fun findAllByCodigoCompra(codigoCompra: String) : MutableList<CarritoCompra>?

    fun checkout(codigoCompra: String) : CheckoutDto?

    fun findByIdAndEstadoCarrito(id: UUID, estadoCarrito: EstadoCarrito) : CarritoCompra?
}