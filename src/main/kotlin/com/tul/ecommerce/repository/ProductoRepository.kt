package com.tul.ecommerce.repository

import com.tul.ecommerce.model.Producto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author Jean Khalo Lozano Ruiz
 * @version 2021/11/15
 */
@Repository
interface ProductoRepository : CrudRepository<Producto, UUID>