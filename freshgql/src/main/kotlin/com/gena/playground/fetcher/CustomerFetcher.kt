package com.gena.playground.fetcher

import com.gena.model.gql.types.Customer
import com.gena.playground.api.ICustomerDataProvider
import com.gena.playground.mapper.CustomerMapper
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class CustomerFetcher(
    private val customerDataProvider: ICustomerDataProvider,
    private val customerMapper: CustomerMapper,
) {
    @DgsQuery
    fun customer(): List<Customer>? {
        val source=customerDataProvider.customersData()
        return customerMapper.convert(source)
    }
}