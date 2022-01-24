package com.gena.playground.api

import com.gena.playground.dto.CustomerDto

interface ICustomerDataProvider {
    fun customersData(): List<CustomerDto>?
}