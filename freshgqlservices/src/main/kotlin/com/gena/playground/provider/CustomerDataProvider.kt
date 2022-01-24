package com.gena.playground.provider

import com.gena.fdstore.data.CustomerData
import com.gena.fdstore.domain.Customer
import com.gena.playground.api.ICustomerDataProvider
import com.gena.playground.dto.CustomerDto
import org.springframework.stereotype.Repository

@Repository
class CustomerDataProvider : ICustomerDataProvider {
    override fun customersData(): List<CustomerDto>?{
        val mCustomer: List<Customer> = CustomerData.customerList()
        return listOf(
            CustomerDto("1","First","First"),
            CustomerDto("2","Second","Second"),
            CustomerDto("3","Tre","Tre"),
        )
    }
}