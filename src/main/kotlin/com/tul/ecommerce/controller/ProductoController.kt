package com.tul.ecommerce.controller

import com.tul.ecommerce.model.Producto
import com.tul.ecommerce.service.api.ProductoServiceApi
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
class ProductoController(private val productoServiceApi: ProductoServiceApi) {

    @GetMapping
    fun getAll() : MutableList<Producto>? {
        return productoServiceApi.all
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        return if (producto != null) {
            ResponseEntity<Producto>(producto, HttpStatus.OK)
        } else {
            ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }
    }

    @PostMapping
    fun save(@RequestBody producto: Producto) : ResponseEntity<Producto> {
        val obj = productoServiceApi.save(producto)
        return ResponseEntity<Producto>(obj, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun save(@PathVariable id: UUID, @RequestBody producto: Producto) : ResponseEntity<Producto> {
        val prod = productoServiceApi[id]
        return if (prod != null) {
            prod.sku = producto.sku
            prod.nombre = producto.nombre
            prod.descripcion = producto.descripcion
            prod.precio = producto.precio
            ResponseEntity<Producto>(productoServiceApi.save(prod), HttpStatus.OK)
        } else {
            ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        if (producto != null) {
            productoServiceApi.delete(id)
        } else {
            return ResponseEntity<Producto>(HttpStatus.NO_CONTENT)
        }

        return ResponseEntity<Producto>(producto, HttpStatus.OK)
    }

}