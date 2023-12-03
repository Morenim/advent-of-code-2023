package com.advent.day3

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day3Test {

    @Test
    fun testPartOneSample() {
        val lines = Day3Test::class.java.getResource("/day3/sample.txt").readText().lines()
        assertEquals(4361, day3part1(lines))
    }

    @Test
    fun testPartOne() {
        val lines = Day3Test::class.java.getResource("/day3/part.txt").readText().lines()
        assertEquals(554003, day3part1(lines))
    }

    @Test
    fun testPartTwoSample() {
        val lines = Day3Test::class.java.getResource("/day3/sample.txt").readText().lines()
        assertEquals(467835, day3part2(lines))
    }

    @Test
    fun testPartTwo() {
        val lines = Day3Test::class.java.getResource("/day3/part.txt").readText().lines()
        assertEquals(87263515, day3part2(lines))
    }
}