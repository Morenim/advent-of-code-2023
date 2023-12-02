package com.advent.day2

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day2Test {

    @Test
    fun testPartOneSample() {
        val lines = Day2Test::class.java.getResource("/day2/sample.txt").readText().lines()
        assertEquals(8, day2part1(lines))
    }

    @Test
    fun testPartOne() {
        val lines = Day2Test::class.java.getResource("/day2/part.txt").readText().lines()
        assertEquals(1853, day2part1(lines))
    }

    @Test
    fun testPartTwoSample() {
        val lines = Day2Test::class.java.getResource("/day2/sample.txt").readText().lines()
        assertEquals(2286, day2part2(lines))
    }

    @Test
    fun testPartTwo() {
        val lines = Day2Test::class.java.getResource("/day2/part.txt").readText().lines()
        assertEquals(72706, day2part2(lines))
    }
}