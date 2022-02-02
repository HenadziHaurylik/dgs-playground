package com.gena.playground.mapper

import com.gena.model.gql.types.Customer
import com.gena.playground.dto.CustomerDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface CustomerConverter {
    @InheritInverseConfiguration
    fun convertToModel(customerDto: CustomerDto): Customer
}
