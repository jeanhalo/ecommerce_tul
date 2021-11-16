package com.tul.ecommerce.model

import com.tul.ecommerce.commons.StandardEntity
import com.tul.ecommerce.enums.TipoProducto
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.Length
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@Entity(name = "ec_tul_producto")
data class Producto(
        @NotBlank(message = "Campo obligatorio")
        @Length(message = "Máximo 100 carácteres", max = 100)
        @Column(nullable = false, length = 100)
        var nombre: String? = null,
        @Length(message = "Máximo 150 carácteres", max = 150)
        @Column(length = 150)
        var sku: String? = null,
        @NotBlank(message = "Campo obligatorio")
        @Length(message = "Máximo 500 carácteres", max = 500)
        @Column(nullable = false, length = 500)
        var descripcion: String? = null,
        @PositiveOrZero(message = "Precios positivos o cero")
        @Column(nullable = false)
        var precio: Double? = null,
        @NotNull(message = "Campo obligatorio")
        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        var tipoProducto: TipoProducto? = null
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
