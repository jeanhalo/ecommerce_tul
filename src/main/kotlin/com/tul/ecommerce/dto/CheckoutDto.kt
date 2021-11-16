package com.tul.ecommerce.dto

import com.tul.ecommerce.model.CarritoCompra

data class CheckoutDto(
    var cantidadProductos: Int,
    var costoFinal: Double,
    var carritoCompra: MutableList<CarritoCompra>
)
