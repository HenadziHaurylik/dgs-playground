package com.gena.playground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.tracing.TracingInstrumentation
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AppConfiguration {
    @Bean
    @Qualifier("dgsObjectMapper")
    fun dgsObjectMapper(): ObjectMapper {
        val customMapper: ObjectMapper = ObjectMapper()
        customMapper.registerModule(JavaTimeModule())
        return customMapper;
    }

    @Bean
    @ConditionalOnProperty(prefix = "graphql.tracing", name = ["enabled"], matchIfMissing = false)
    fun tracingInstrumentation(): Instrumentation? {
        return TracingInstrumentation()
    }


}
