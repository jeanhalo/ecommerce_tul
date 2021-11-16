package com.tul.ecommerce.repository

import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.model.CarritoCompra
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CarritoCompraRepository : CrudRepository<CarritoCompra, UUID> {
    fun findByCodigoCompraAndProductoIdAndEstadoCarrito(
        codigoCompra: String, productoId: UUID, estadoCarrito: EstadoCarrito
    ) : CarritoCompra?

    fun findAllByCodigoCompra(codigoCompra: String) : MutableList<CarritoCompra>?

    fun findByIdAndEstadoCarrito(id: UUID, estadoCarrito: EstadoCarrito) : CarritoCompra?

}