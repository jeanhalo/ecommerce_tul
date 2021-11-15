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

    @PostMapping
    fun save(@RequestBody producto: Producto) : ResponseEntity<Producto> {
        val obj = productoServiceApi.save(producto)
        return ResponseEntity<Producto>(obj, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) : ResponseEntity<Producto> {
        val producto = productoServiceApi[id]
        if (producto != null) {
            productoServiceApi.delete(id)
        } else {
            return ResponseEntity<Producto>(HttpStatus.NOT_FOUND)
        }

        return ResponseEntity<Producto>(producto, HttpStatus.OK)
    }

}