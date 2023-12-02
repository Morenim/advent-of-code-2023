package com.advent.day2

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken

sealed class Expr {
    enum class CubeColour { RED, GREEN, BLUE }
    data class Game(val id: Int, val samples: List<CubesSample>)
    data class CubeSample(val colour: CubeColour, val nrOfCubes: Int)
    data class CubesSample(val nrOfRed: Int, val nrOfGreen: Int, val nrOfBlue: Int)
}

val cubeGrammar = object : Grammar<Expr.Game>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    val number by regexToken("\\d+")
    val game by literalToken("Game", ignored = true)
    val redColour by literalToken("red") map { Expr.CubeColour.RED }
    val greenColour by literalToken("green") map { Expr.CubeColour.GREEN }
    val blueColour by literalToken("blue") map { Expr.CubeColour.BLUE }
    val cubeColour by parser { choose(redColour, greenColour, blueColour) }
    val gameSeparator by literalToken(":")
    val semicolon by literalToken(";")
    val comma by literalToken(",")

    val cubeSample by parser {
        val id = number().text.toInt()
        val cubeColour = cubeColour()
        Expr.CubeSample(cubeColour, id)
    }

    val cubesSample by separated(cubeSample, comma, allowEmpty = false) map {samples ->
        Expr.CubesSample(
            samples.find { it.colour == Expr.CubeColour.RED }?.nrOfCubes ?: 0,
            samples.find { it.colour == Expr.CubeColour.GREEN }?.nrOfCubes ?: 0,
            samples.find { it.colour == Expr.CubeColour.BLUE }?.nrOfCubes ?: 0
        )
    }

    override val root by parser {
        game()
        val id = number().text.toInt()
        gameSeparator()
        val samples = separated(cubesSample, semicolon, allowEmpty = false)()
        Expr.Game(id, samples)
    }
}

fun isValidGame(game: Expr.Game, nrOfRed: Int, nrOfGreen: Int, nrOfBlue: Int): Boolean {
    return game.samples.all { it.nrOfRed <= nrOfRed && it.nrOfGreen <= nrOfGreen && it.nrOfBlue <= nrOfBlue }
}

fun day2part1(lines: List<String>): Int {
    return lines.asSequence()
        .map { cubeGrammar.parse(it).getOrThrow() }
        .filter { isValidGame(it, 12, 13, 14) }
        .sumOf { it.id }
}

fun minimalViableCubes(game: Expr.Game): Int {
    val minNrOfRed = game.samples.maxOf { it.nrOfRed }
    val minNrOfBlue = game.samples.maxOf { it.nrOfBlue }
    val minNrOfGreen = game.samples.maxOf { it.nrOfGreen }
    return minNrOfRed * minNrOfBlue * minNrOfGreen
}

fun day2part2(lines: List<String>): Int {
    return lines.asSequence()
        .map { cubeGrammar.parse(it).getOrThrow() }
        .sumOf { minimalViableCubes(it) }
}