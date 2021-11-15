package com.tul.ecommerce.repository

import com.tul.ecommerce.model.Producto
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductoRepository : CrudRepository<Producto, UUID> {
}