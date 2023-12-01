package com.advent.day1

fun day1part1(lines: List<String>): Int {
    return lines.asSequence()
        .map { it.filter { c -> c.isDigit() } }
        .map { "${it.first()}${it.last()}" }
        .sumOf { it.toInt() }
}

val isDigitRegex = Regex("([0-9]|one|two|three|four|five|six|seven|eight|nine)")

fun toDigit(str: String): Char = when (str) {
    "one" -> '1'
    "two" -> '2'
    "three" -> '3'
    "four" -> '4'
    "five" -> '5'
    "six" -> '6'
    "seven" -> '7'
    "eight" -> '8'
    "nine" -> '9'
    else -> str.first()
}

fun day1part2(lines: List<String>): Int {
    return lines.asSequence()
        .map { line -> line.indices.mapNotNull { isDigitRegex.find(line, it) }.map{ toDigit(it.value) } }
        .map { "${it.first()}${it.last()}" }
        .sumOf { it.toInt() }
}
