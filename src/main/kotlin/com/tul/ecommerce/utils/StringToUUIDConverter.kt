package com.tul.ecommerce.utils

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

@Component
class StringToUUIDConverter : Converter<String, UUID> {
    /**
     * Convert the source object of type `S` to target type `T`.
     * @param source the source object to convert, which must be an instance of `S` (never `null`)
     * @return the converted object, which must be an instance of `T` (potentially `null`)
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    override fun convert(source: String): UUID? {
        return UUID.fromString(source)
    }

}