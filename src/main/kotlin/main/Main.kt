package main

import day.*

class Main {



    companion object {

        private val days = listOf(
            Day2(),
            Day3(),
            Day4(),
            Day5(),
            Day6(),
            Day7(),
            Day8(),
            Day9(),
        )

        @JvmStatic
        public fun main(args: Array<String>) {
            days.forEach { day ->
                println("${day::class.java} first answer: ${day.runFirst()}")
                println("${day::class.java} second answer: ${day.runSecond()}")
            }
        }
    }
}