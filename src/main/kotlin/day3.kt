package com.advent.day3

import kotlin.math.max
import kotlin.math.min

fun isSymbol(char: Char): Boolean = !char.isDigit() && char != '.'

typealias RangeToCheck = Pair<Int, IntRange>

fun rangesToCheck(grid: Array<CharArray>, y: Int, range: IntRange): List<RangeToCheck> {
    val first = range.first
    val last = range.last

    val ranges: MutableList<RangeToCheck> = mutableListOf()

    // Check left of the part.
    if (first != 0) {
        ranges.add(Pair(y, IntRange(first - 1, first - 1)))
    }

    // Check right of the part.
    val width = grid.first().size
    if (last != width - 1) {
        ranges.add(Pair(y, IntRange(last + 1, last + 1)))
    }

    val scanRange = IntRange(max(0, first - 1), min(width - 1, last + 1))

    // Check above the part.
    if (y != 0) {
        ranges.add(Pair(y - 1, scanRange))
    }

    // Check under the part.
    if (y != grid.size - 1) {
        ranges.add(Pair(y + 1, scanRange))
    }

    return ranges
}

fun isPart(grid: Array<CharArray>, y: Int, range: IntRange): Boolean {
    val ranges = rangesToCheck(grid, y, range)
    return ranges.any { (yToCheck, rangeToCheck) -> rangeToCheck.any { xToCheck -> isSymbol(grid[yToCheck][xToCheck]) } }
}

val numberRegex = Regex("\\d+")

fun day3part1(lines: List<String>): Int {
    // Assuming the string is pure ascii here...
    val grid = lines.map { it.toCharArray() }.toTypedArray()

    return lines.asSequence()
        .flatMapIndexed { i, line ->
            numberRegex.findAll(line)
                .filter { isPart(grid, i, it.range) }
        }
        .map { it.value.toInt() }
        .sum()
}

fun day3part2(lines: List<String>): Int {
    val grid = lines.map { it.toCharArray() }.toTypedArray()

    val parts = lines.asSequence()
        .flatMapIndexed { i, line ->
            numberRegex.findAll(line)
                .filter { isPart(grid, i, it.range) }
                .map { Pair(i, it) }
        }
        .groupBy({ it.first }, { it.second })

    return lines.asSequence()
        .flatMapIndexed { y, line ->
            line.asSequence().mapIndexedNotNull() { x, char ->
                if (char == '*') findGearParts(grid, parts, Pair(y, x)) else null
            }
        }
        .sumOf { (part1, part2) -> part1.value.toInt() * part2.value.toInt() }
}

fun findGearParts(
    grid: Array<CharArray>,
    parts: Map<Int, List<MatchResult>>,
    gear: Pair<Int, Int>
): Pair<MatchResult, MatchResult>? {
    val gearY = gear.first
    val gearX = gear.second

    val relevantParts: MutableList<Pair<Int, MatchResult>> = mutableListOf()
    relevantParts.addAll(parts[gearY]?.map { Pair(gearY, it) } ?: emptyList())
    if (gearY != 0) {
        relevantParts.addAll(parts[gearY-1]?.map { Pair(gearY-1, it) } ?: emptyList())
    }
    if (gearY != grid.size - 1) {
        relevantParts.addAll(parts[gearY+1]?.map { Pair(gearY+1, it) } ?: emptyList())
    }

    val adjacentParts = relevantParts.asSequence()
        .filter {
            val ranges = rangesToCheck(grid, it.first, it.second.range)
            ranges.any { (y, range) -> range.any { x -> gearX == x && gearY == y } }
        }
        .toList()

    if (adjacentParts.size == 2) {
        return Pair(adjacentParts[0].second, adjacentParts[1].second)
    }

    return null
}
