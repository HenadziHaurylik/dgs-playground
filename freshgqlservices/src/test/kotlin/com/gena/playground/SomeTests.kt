package com.gena.playground

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

//@SpringBootTest
private const val TEST_CONSTANT_T="test"
class SomeTests {

    @Test
    fun contextLoads() {
        Const.TEST_CONSTANT
        TEST_CONSTANT_T
    }

    object Const{
        const val TEST_CONSTANT="test"
        const val TEST_CONSTANT1="test"
        const val TEST_CONSTANT11="test"
        const val TEST_CONSTANT12="test"
        const val TEST_CONSTANT13="test"

    }
}
