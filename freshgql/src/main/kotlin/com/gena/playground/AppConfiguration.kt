package com.gena.playground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Qualifier
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
}
