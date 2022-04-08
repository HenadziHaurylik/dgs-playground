package com.gena.playground.Instrumentation

import com.gena.playground.util.logger
import graphql.ExecutionResult
import graphql.execution.instrumentation.InstrumentationContext
import graphql.execution.instrumentation.InstrumentationState
import graphql.execution.instrumentation.SimpleInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters
import graphql.schema.DataFetcher
import graphql.schema.GraphQLNonNull
import graphql.schema.GraphQLObjectType
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class GqlTracingInstrumentation : SimpleInstrumentation() {
    override fun createState(): InstrumentationState {
        return TraceState()
    }

    override fun beginExecution(parameters: InstrumentationExecutionParameters): InstrumentationContext<ExecutionResult> {
        val state: TraceState = parameters.getInstrumentationState()
        state.traceStartTime = System.currentTimeMillis()
        return super.beginExecution(parameters)
    }

    override fun instrumentDataFetcher(dataFetcher: DataFetcher<*>, parameters: InstrumentationFieldFetchParameters): DataFetcher<*> {
        if (parameters.isTrivialDataFetcher) return dataFetcher
        val dataFetcherName = findDataFetcherTag(parameters)
        return DataFetcher { environment ->
            val startTime = System.currentTimeMillis()
            val result = dataFetcher.get(environment)
            val totalTime = System.currentTimeMillis() - startTime
            logger.info("Datafetcher '$dataFetcherName': ${totalTime}ms")
            result
        }
    }
    override fun instrumentExecutionResult(executionResult: ExecutionResult, parameters: InstrumentationExecutionParameters): CompletableFuture<ExecutionResult> {
        val state: TraceState = parameters.getInstrumentationState()
        val totalTime = System.currentTimeMillis() - state.traceStartTime
        logger.info("Total execution time: ${totalTime}ms")
        executionResult.extensions?.put("TotalExecutionMs", totalTime) ?: mapOf("TotalExecutionMs" to totalTime)
        return super.instrumentExecutionResult(executionResult, parameters)
    }
    private fun findDataFetcherTag(parameters: InstrumentationFieldFetchParameters): String {
        val type = parameters.executionStepInfo.parent.type
        val parentType = if (type is GraphQLNonNull) {
            type.wrappedType as GraphQLObjectType
        } else {
            type as GraphQLObjectType
        }
        return "${parentType.name}.${parameters.executionStepInfo.path.segmentName}"
    }
}

data class TraceState(var traceStartTime: Long = 0) : InstrumentationState
