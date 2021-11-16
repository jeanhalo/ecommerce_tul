package com.tul.ecommerce.controller

import com.tul.ecommerce.exception.GenericException
import com.tul.ecommerce.model.Producto
import com.tul.ecommerce.service.api.ProductoServiceApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
@Validated
class ProductoController(private val productoServiceApi: ProductoServiceApi) : GenericException() {

    /**
     * Buscar todos los registros.
     */
    @GetMapping
    fun getAll() : MutableList<Producto>? {
        return productoServiceApi.all
    }

    /**
     * Buscar un registro por id.
     */
    @GetMapping("/{id}")
    fun getById(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        // Valida que exista un producto.
        return if (producto != null) {
            ResponseEntity<Producto>(producto, HttpStatus.OK)
        } else {
            ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }
    }

    /**
     * Guardar registro.
     */
    @PostMapping
    fun save(@Valid @RequestBody producto: Producto) : ResponseEntity<Producto> {
        productoServiceApi.valorFinalProducto(producto)
        val obj = productoServiceApi.save(producto)
        return ResponseEntity<Producto>(obj, HttpStatus.CREATED)
    }

    /**
     * Actualizar un registro.
     */
    @PutMapping("/{id}")
    fun save(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID, @Valid @RequestBody producto: Producto) : ResponseEntity<Producto> {
        val prod = productoServiceApi[id]
        // Valida que exista el producto.
        return if (prod != null) {
            prod.sku = producto.sku
            prod.nombre = producto.nombre
            prod.descripcion = producto.descripcion
            prod.precio = producto.precio
            productoServiceApi.valorFinalProducto(prod)
            ResponseEntity<Producto>(productoServiceApi.save(prod), HttpStatus.OK)
        } else {
            ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }
    }

    /**
     * Eliminar un registro.
     */
    @DeleteMapping("/{id}")
    fun delete(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        // Valida que exista el producto.
        if (producto != null) {
            productoServiceApi.delete(id)
        } else {
            return ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Producto>(producto, HttpStatus.OK)
    }

}