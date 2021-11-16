package com.tul.ecommerce.model

import com.tul.ecommerce.commons.StandardEntity
import com.tul.ecommerce.enums.EstadoCarrito
import org.hibernate.Hibernate
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@Entity(name = "ec_tul_carrito_compra")
data class CarritoCompra(
    @NotBlank(message = "Campo obligatorio")
    @Length(message = "Máximo 100 carácteres", max = 100)
    @Column(nullable = false, length = 100)
    var codigoCompra: String?,
    @PositiveOrZero(message = "Cantidad positivos o cero")
    @Column(nullable = false)
    var cantidad: Double?,
    @NotNull(message = "Campo obligatorio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var estadoCarrito: EstadoCarrito = EstadoCarrito.PENDIENTE,
    @Valid
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    var producto: Producto
) : StandardEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CarritoCompra

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , version = $version , codigoCompra = $codigoCompra , cantidad = $cantidad , estadoCarrito = $estadoCarrito )"
    }
}
