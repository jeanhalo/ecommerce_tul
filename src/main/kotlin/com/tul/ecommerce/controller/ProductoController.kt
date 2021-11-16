package com.tul.ecommerce.controller

import com.tul.ecommerce.model.Producto
import com.tul.ecommerce.service.api.ProductoServiceApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Consumer
import javax.validation.Valid
import javax.validation.constraints.NotNull


@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
@Validated
class ProductoController(private val productoServiceApi: ProductoServiceApi) {

    @GetMapping
    fun getAll() : MutableList<Producto>? {
        return productoServiceApi.all
    }

    @GetMapping("/{id}")
    fun getById(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        return if (producto != null) {
            ResponseEntity<Producto>(producto, HttpStatus.OK)
        } else {
            ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping
    fun save(@Valid @RequestBody producto: Producto) : ResponseEntity<Producto> {
        productoServiceApi.valorFinalProducto(producto)
        val obj = productoServiceApi.save(producto)
        return ResponseEntity<Producto>(obj, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun save(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID, @Valid @RequestBody producto: Producto) : ResponseEntity<Producto> {
        val prod = productoServiceApi[id]
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

    @DeleteMapping("/{id}")
    fun delete(@NotNull(message = "Identificador obligatorio") @PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        if (producto != null) {
            productoServiceApi.delete(id)
        } else {
            return ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Producto>(producto, HttpStatus.OK)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String?>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }

}