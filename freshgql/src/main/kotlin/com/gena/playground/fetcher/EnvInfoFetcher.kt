package com.gena.playground.fetcher

import com.gena.model.gql.DgsConstants
import com.gena.model.gql.types.EnvInfo
import com.gena.playground.api.IEnvInfoService
import com.gena.playground.util.logger
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class EnvInfoFetcher(private val envInfoService: IEnvInfoService) {

    @DgsQuery()
    fun envInfo(): EnvInfo {
        logger.debug("Debug EnvInfo")
        logger.info("Info EnvInfo")
        logger.warn("Warn EnvInfo")
        logger.error("Error EnvInfo")
        return envInfoService.init()
    }

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
