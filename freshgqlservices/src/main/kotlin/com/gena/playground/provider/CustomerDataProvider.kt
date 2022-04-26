package com.gena.playground.provider

import com.gena.fdstore.data.CustomerData
import com.gena.fdstore.domain.Customer
import com.gena.playground.api.ICustomerDataProvider
import com.gena.playground.dto.AddressDto
import com.gena.playground.dto.CustomerDto
import org.springframework.stereotype.Repository

@Repository
class CustomerDataProvider : ICustomerDataProvider {
    override fun customersData(): List<CustomerDto>? {
        val mCustomer: List<Customer> = CustomerData.customerList()
        return listOf(
            CustomerDto("1", "First", "First",
                positions = listOf("TL", "Dev"),
                addreses = listOf(AddressDto("15-502", "Bialystok", 15),
                    AddressDto("15-501", "Bialystok", 1))
            ),
            CustomerDto("2", "Second", "Second",
                positions = listOf( "Dev"),
                addreses = listOf(AddressDto("15-502", "Bialystok", 15))
            ),
            CustomerDto("3", "Tre", "Tre",
                positions = listOf("QA", "Dev"),
                addreses = listOf(AddressDto("15-502", "Bialystok", 15),
                    AddressDto("230023", "Grodno", 1))

            ),
        )
    }
}
