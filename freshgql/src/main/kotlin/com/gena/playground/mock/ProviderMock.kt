package com.gena.playground.mock

import com.gena.model.gql.types.Product
import com.netflix.graphql.mocking.MockProvider
import org.springframework.stereotype.Component

@Component
class ProviderMock:MockProvider {
    override fun provide(): Map<String, Any> {
       return mapOf(
          "product" to Product(id = "id",available = true, name = "Test")
       )
    }
}
