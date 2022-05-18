package com.gena.playground.service

import com.gena.model.gql.types.EnvInfo
import com.gena.playground.api.IEnvInfoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class EnvInfoService(
) : IEnvInfoService {
    @Value("\${spring.profiles.active:}")
    private val activeProfile: String = ""
    override fun init(): EnvInfo = EnvInfo()

    override fun getBuildNumber(): String {
//        throw RuntimeException("Some Error")
        return "buildProperties.version"
    }

    override fun getActiveProfiles(): String = activeProfile
}
