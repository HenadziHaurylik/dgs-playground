package com.gena.playground.dto

data class CustomerDto(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val positions: List<String>,
    val addreses: List<AddressDto>
)
