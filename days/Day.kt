package com.borek.aoc.day

import android.content.Context
import android.util.Log
import com.borek.aoc.util.InputReader

interface Day<T> {

    val data: T

    fun runFirst(): String
    fun runSecond(): String
}