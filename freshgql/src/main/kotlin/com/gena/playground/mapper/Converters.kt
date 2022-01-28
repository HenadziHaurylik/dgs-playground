package com.gena.playground.mapper

import com.gena.model.gql.types.Customer
import com.gena.playground.dto.CustomerDto
import kotlin.reflect.full.memberProperties

fun CustomerDto.toCustomerReflection()= with(::Customer){
    val propertiesByName=CustomerDto::class.memberProperties.associateBy { it.name }
    callBy(parameters.associate { p-> p to propertiesByName[p.name]?.get(this@toCustomerReflection) })
}