package com.gena.playground.mapper

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.gena.model.gql.types.Customer
import com.gena.playground.dto.CustomerDto
import org.springframework.stereotype.Component

@Component
class CustomerMapper(
    private val dgsObjectMapper: ObjectMapper,
) {
    fun convert(source: List<CustomerDto>?): List<Customer>? {
        CustomerMapperConstants.USER_NAME_CONST
        return if (source == null) null else
            source.map { Customer(it.id, it.firstName, it.lastName) }
    }

    object CustomerMapperConstants {
        const val USER_NAME_CONST = "some username"
    }

    fun toModel(source: List<CustomerDto>?): List<Customer>? {
        dgsObjectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return source?.map { dgsObjectMapper.convertValue(it, Customer::class.java) }

    }
}
