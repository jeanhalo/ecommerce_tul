package com.tul.ecommerce.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "ec_tul_producto")
data class Producto(
        @Id
        @Column(name = "id", nullable = false)
        val id: UUID = UUID.randomUUID(),
        @Column(nullable = false, length = 100)
        val nombre: String = "",
        @Column(length = 150)
        val sku: String = "",
        @Column(nullable = false, length = 500)
        val descripcion: String = "",
        @Column(nullable = false)
        val precio: Double = 0.0
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
                other as Producto

                return id == other.id
        }

        override fun hashCode(): Int = javaClass.hashCode()

        @Override
        override fun toString(): String {
                return this::class.simpleName + "(id = $id , nombre = $nombre , sku = $sku , descripcion = $descripcion , precio = $precio )"
        }
}
