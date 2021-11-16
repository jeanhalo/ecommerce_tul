package com.tul.ecommerce.repository

import com.tul.ecommerce.model.Producto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductoRepository : CrudRepository<Producto, UUID>