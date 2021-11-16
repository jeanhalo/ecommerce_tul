package com.tul.ecommerce.controller

import com.tul.ecommerce.dto.CarritoCompraDto
import com.tul.ecommerce.dto.CheckoutDto
import com.tul.ecommerce.enums.EstadoCarrito
import com.tul.ecommerce.exception.GenericException
import com.tul.ecommerce.model.CarritoCompra
import com.tul.ecommerce.service.api.CarritoCompraServiceApi
import com.tul.ecommerce.service.api.ProductoServiceApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
@RestController
@RequestMapping("/carrito")
@CrossOrigin("*")
@Validated
class CarritoCompraController(
    private val carritoCompraServiceApi: CarritoCompraServiceApi,
    private val productoServiceApi: ProductoServiceApi
) : GenericException() {

    /**
     * Buscar todos los registros.
     */
    @GetMapping
    fun getAll() : MutableList<CarritoCompra>? {
        return carritoCompraServiceApi.all
    }

    /**
     * Buscar todos los registros por codigo de la compra.
     */
    @GetMapping("/filter")
    fun getAllByCodigoCompra(
        @NotBlank(message = "Identificador obligatorio") @RequestParam codigoCompra: String
    ) : MutableList<CarritoCompra>? {
        return carritoCompraServiceApi.findAllByCodigoCompra(codigoCompra)
    }

    /**
     * Buscar registro por id.
     */
    @GetMapping("/{id}")
    fun getById(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<CarritoCompra> {
        val carrito = carritoCompraServiceApi[id]
        // Valida que exista el registro.
        return if (carrito != null) {
            ResponseEntity<CarritoCompra>(carrito, HttpStatus.OK)
        } else {
            ResponseEntity<CarritoCompra>(HttpStatus.NO_CONTENT)
        }
    }

    /**
     * Guardar y actualizar registro del carrito.
     */
    @PostMapping
    fun save(@Valid @RequestBody carritoCompraDto: CarritoCompraDto) : ResponseEntity<CarritoCompra> {

        val prod = productoServiceApi[carritoCompraDto.productoId]

        // Valida que el producto exista para continuar.
        return if (prod != null) {

            var carritoCompra = carritoCompraServiceApi.findByCodigoCompraAndProductoIdAndEstadoCarrito(
                carritoCompraDto.codigoCompra, carritoCompraDto.productoId, EstadoCarrito.PENDIENTE)

            // Valida que el registro carrito exista para actualizar sino crea nuevo registro.
            if (carritoCompra == null) {
                carritoCompra = CarritoCompra(
                    carritoCompraDto.codigoCompra, carritoCompraDto.cantidad, EstadoCarrito.PENDIENTE, prod
                )
            } else {
                carritoCompra.cantidad = carritoCompraDto.cantidad
            }

            val obj = carritoCompraServiceApi.save(carritoCompra)
            ResponseEntity<CarritoCompra>(obj, HttpStatus.CREATED)

        } else {
            ResponseEntity<CarritoCompra>(HttpStatus.NO_CONTENT)
        }


    }

    /**
     * Actualizar registro del carrito por id.
     */
    @PutMapping("/{id}")
    fun save(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID,
             @Valid @RequestBody carritoCompra: CarritoCompra) : ResponseEntity<CarritoCompra> {
        val carr = carritoCompraServiceApi[id]
        // Valida que el registro exista.
        return if (carr != null) {
            carr.cantidad = carritoCompra.cantidad
            ResponseEntity<CarritoCompra>(carritoCompraServiceApi.save(carr), HttpStatus.OK)
        } else {
            ResponseEntity<CarritoCompra>(HttpStatus.NO_CONTENT)
        }
    }

    /**
     * Eliminar registro carrito por id.
     */
    @DeleteMapping("/{id}")
    fun delete(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<CarritoCompra> {
        val carrito = carritoCompraServiceApi.findByIdAndEstadoCarrito(id, EstadoCarrito.PENDIENTE)
        // Valida que el registro exista y esté en estado PENDIENTE para eliminar.
        if (carrito != null) {
            carritoCompraServiceApi.delete(id)
        } else {
            return ResponseEntity<CarritoCompra>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<CarritoCompra>(carrito, HttpStatus.OK)
    }

    /**
     * Realiza checkout de la compra, actualiza a estado COMPLETADO y retorna detalle.
     */
    @PutMapping("/checkout/{codigoCompra}")
    fun checkout(
        @NotBlank(message = "Identificador obligatorio") @PathVariable codigoCompra: String
    ) : ResponseEntity<CheckoutDto> {
        val checkout = carritoCompraServiceApi.checkout(codigoCompra)
        return if (checkout != null) {
            ResponseEntity<CheckoutDto>(checkout, HttpStatus.OK)
        } else {
            ResponseEntity<CheckoutDto>(HttpStatus.NO_CONTENT)
        }
    }

}