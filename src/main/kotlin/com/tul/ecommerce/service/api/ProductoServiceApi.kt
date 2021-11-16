package com.tul.ecommerce.service.api

import com.tul.ecommerce.commons.GenericServiceApi
import com.tul.ecommerce.model.Producto
import java.util.*

/**
 * ProductoServiceApi interface de productosService.
 *
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
interface ProductoServiceApi : GenericServiceApi<Producto, UUID> {
    /**
     * Valida según el tipo de producto y actualiza el precio de ser necesario.
     * (SIMPLE = igual, DESCUENTO = dividido 2).
     *
     * @param producto el producto a guardar.
     */
    fun valorFinalProducto(producto : Producto)
}