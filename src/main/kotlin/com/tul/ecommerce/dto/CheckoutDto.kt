package com.tul.ecommerce.dto

import com.tul.ecommerce.model.CarritoCompra

/**
 * Objeto response para checkout.
 *
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
data class CheckoutDto(
    var cantidadProductos: Int,
    var costoFinal: Double,
    var carritoCompra: MutableList<CarritoCompra>
)
