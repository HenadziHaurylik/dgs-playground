package com.gena.playground.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.stereotype.Service

@Service
class EnvInfoService(
    private val buildProperties: BuildProperties,

) {
    @Value("\${spring.profiles.active:}")
    private val activeProfile: String = ""

    fun getBuildNumber(): String {
//        throw RuntimeException("Some Error")
        return buildProperties.version
    }

    fun getActiveProfiles(): String = activeProfile
}
