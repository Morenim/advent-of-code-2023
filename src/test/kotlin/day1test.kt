package com.advent.day1

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day1Test {

    @Test
    fun testPartOneSample() {
        val lines = Day1Test::class.java.getResource("/day1/sample1.txt").readText().lines()
        assertEquals(142, day1part1(lines))
    }

    @Test
    fun testPartOne() {
        val lines = Day1Test::class.java.getResource("/day1/part1.txt").readText().lines()
        assertEquals(55712, day1part1(lines))
    }

    @Test
    fun testPartTwoSample() {
        val lines = Day1Test::class.java.getResource("/day1/sample2.txt").readText().lines()
        assertEquals(281, day1part2(lines))
    }

    @Test
    fun testPartTwo() {
        val lines = Day1Test::class.java.getResource("/day1/part1.txt").readText().lines()
        assertEquals(55413, day1part2(lines))
    }
}