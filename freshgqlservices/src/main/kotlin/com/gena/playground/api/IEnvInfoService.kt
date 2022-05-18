package com.gena.playground.api

import com.gena.model.gql.types.EnvInfo

interface IEnvInfoService {
    fun init(): EnvInfo
    fun getBuildNumber(): String
    fun getActiveProfiles(): String
}
