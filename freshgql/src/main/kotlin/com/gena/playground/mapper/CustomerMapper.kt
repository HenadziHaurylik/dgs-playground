package com.gena.playground.mapper

import com.gena.model.gql.types.Customer
import com.gena.playground.dto.CustomerDto
import org.springframework.stereotype.Component

@Component
class CustomerMapper {
    fun convert(source: List<CustomerDto>?): List<Customer>? {
        return if (source == null) null else
            source.map { Customer(it.id, it.firstName, it.lastName) }
    }
}
