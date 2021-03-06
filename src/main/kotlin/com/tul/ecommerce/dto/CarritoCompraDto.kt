package com.tul.ecommerce.dto

import org.hibernate.validator.constraints.Length
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

/**
 * Objeto request para crear y actualizar carrito.
 *
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
data class CarritoCompraDto(
    @NotBlank(message = "Campo obligatorio")
    @Length(message = "Máximo 100 carácteres", max = 100)
    var codigoCompra: String,
    @PositiveOrZero(message = "Cantidad positivos o cero")
    var cantidad: Double,
    @NotNull(message = "Campo obligatorio")
    var productoId: UUID
)
