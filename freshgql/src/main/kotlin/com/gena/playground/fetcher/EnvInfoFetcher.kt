package com.gena.playground.fetcher

import com.gena.model.gql.DgsConstants
import com.gena.model.gql.types.EnvInfo
import com.gena.playground.service.EnvInfoService
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class EnvInfoFetcher(
    private val envInfoService: EnvInfoService,

) {

    @DgsQuery()
    fun envInfo(): EnvInfo = EnvInfo()

    @DgsData(
        parentType = DgsConstants.ENVINFO.TYPE_NAME,
        field = DgsConstants.ENVINFO.ActiveProfiles
    )
    fun activeProfiles(): String =
        envInfoService.getActiveProfiles()

    @DgsData(
        parentType = DgsConstants.ENVINFO.TYPE_NAME,
        field = DgsConstants.ENVINFO.BuildNumber
    )
    fun buildNumber(): String =
        envInfoService.getBuildNumber()
}
