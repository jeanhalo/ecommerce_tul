package com.tul.ecommerce.service.impl

import com.tul.ecommerce.commons.GenericServiceImpl
import com.tul.ecommerce.dto.CheckoutDto
import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.model.CarritoCompra
import com.tul.ecommerce.repository.CarritoCompraRepository
import com.tul.ecommerce.service.api.CarritoCompraServiceApi
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarritoCompraServiceImpl(private val carritoCompraRepository: CarritoCompraRepository) : GenericServiceImpl<CarritoCompra, UUID>(), CarritoCompraServiceApi {

    override val dao: CrudRepository<CarritoCompra, UUID>
        get() = carritoCompraRepository

    override fun findByCodigoCompraAndProductoIdAndEstadoCarrito(
        codigoCompra: String,
        productoId: UUID,
        estadoCarrito: EstadoCarrito
    ): CarritoCompra? {
        return carritoCompraRepository.findByCodigoCompraAndProductoIdAndEstadoCarrito(codigoCompra, productoId, estadoCarrito)
    }

    override fun findAllByCodigoCompra(codigoCompra: String) : MutableList<CarritoCompra>? {
        return carritoCompraRepository.findAllByCodigoCompra(codigoCompra)
    }

    override fun checkout(codigoCompra: String) : CheckoutDto? {
        val carrito = carritoCompraRepository.findAllByCodigoCompra(codigoCompra)

        return if (carrito != null) {
            if (carrito.isNotEmpty()) {
                var costoFinal = 0.0
                for (car: CarritoCompra in carrito) {
                    car.estadoCarrito = EstadoCarrito.COMPLETADO
                    costoFinal += car.producto.precio!! * car.cantidad!!
                }
                carritoCompraRepository.saveAll(carrito)
                CheckoutDto(carrito.size, costoFinal, carrito)
            } else {
                null
            }
        } else {
            null
        }

    }

    override fun findByIdAndEstadoCarrito(id: UUID, estadoCarrito: EstadoCarrito): CarritoCompra? {
        return carritoCompraRepository.findByIdAndEstadoCarrito(id, estadoCarrito)
    }


}