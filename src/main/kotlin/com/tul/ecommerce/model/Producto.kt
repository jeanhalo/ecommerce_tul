package com.tul.ecommerce.model

import com.tul.ecommerce.commons.StandardEntity
import com.tul.ecommerce.enums.TipoProducto
import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "ec_tul_producto")
data class Producto(
        @Column(nullable = false, length = 100)
        var nombre: String = "",
        @Column(length = 150)
        var sku: String = "",
        @Column(nullable = false, length = 500)
        var descripcion: String = "",
        @Column(nullable = false)
        var precio: Double = 0.0,
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var tipoProducto: TipoProducto
) : StandardEntity() {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
                other as Producto

                return id == other.id
        }

        override fun hashCode(): Int = javaClass.hashCode()

        @Override
        override fun toString(): String {
                return this::class.simpleName + "(id = $id , version = $version , nombre = $nombre , sku = $sku , descripcion = $descripcion , precio = $precio , tipoProducto = $tipoProducto )"
        }

}
