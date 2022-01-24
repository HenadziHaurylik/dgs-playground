package com.gena.playground

class Some {
    val array: Array<Int> = arrayOf(
        1,
        2,
        3,
    )

    fun pr(array: Array<Int>) {
        array.iterator().forEach { println(it) }
    }
}
