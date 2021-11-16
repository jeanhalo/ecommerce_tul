package com.tul.ecommerce.service.impl

import com.tul.ecommerce.commons.GenericServiceImpl
import com.tul.ecommerce.enums.TipoProducto
import com.tul.ecommerce.model.Producto
import com.tul.ecommerce.repository.ProductoRepository
import com.tul.ecommerce.service.api.ProductoServiceApi
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductoServiceImpl(private val productoRepository: ProductoRepository) : GenericServiceImpl<Producto, UUID>(), ProductoServiceApi {

    val DESCUENTO_APLICAR: Double = 2.0;

    override val dao: CrudRepository<Producto, UUID>
        get() = productoRepository

    override fun valorFinalProducto(producto : Producto) {
        if (producto.tipoProducto?.equals(TipoProducto.DESCUENTO) == true) {
            producto.precio = producto.precio?.div(DESCUENTO_APLICAR);
        }
    }
}