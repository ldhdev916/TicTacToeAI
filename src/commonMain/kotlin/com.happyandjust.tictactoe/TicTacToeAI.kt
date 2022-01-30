@file:JvmName("TicTacToeAI")

package com.happyandjust.tictactoe

import kotlin.js.JsName
import kotlin.jvm.JvmName

private val possibleLines = hashSetOf(
    setOf(Pos.TOP_LEFT, Pos.TOP_CENTER, Pos.TOP_RIGHT),
    setOf(Pos.LEFT, Pos.CENTER, Pos.RIGHT),
    setOf(Pos.BOTTOM_LEFT, Pos.BOTTOM_CENTER, Pos.BOTTOM_RIGHT),

    setOf(Pos.TOP_LEFT, Pos.LEFT, Pos.BOTTOM_LEFT),
    setOf(Pos.TOP_CENTER, Pos.CENTER, Pos.BOTTOM_CENTER),
    setOf(Pos.TOP_RIGHT, Pos.RIGHT, Pos.BOTTOM_RIGHT),

    setOf(Pos.TOP_LEFT, Pos.CENTER, Pos.BOTTOM_RIGHT),
    setOf(Pos.TOP_RIGHT, Pos.CENTER, Pos.BOTTOM_LEFT)
)

@JsName("getCurrentState")
fun TicTacToe.getCurrentState(forMove: TicTacToeMove) = when {
    possibleLines.any { line -> line.all { getValue(it) == forMove } } -> State.WIN
    possibleLines.any { line -> line.all { getValue(it) == forMove.getOpposite() } } -> State.LOSE
    values.any { it == null } -> null
    else -> State.TIE
}

@JsName("toTicTacToeString")
fun TicTacToe.toTicTacToeString(): String {
    val values = Pos.values().toList().chunked(3)

    return values.joinToString("\n") {
        it.joinToString(" ") { pos ->
            when (getValue(pos)) {
                TicTacToeMove.O -> "O"
                TicTacToeMove.X -> "X"
                else -> "."
            }
        }
    }
}

@JsName("getBestMove")
fun TicTacToe.getBestMove(nextMove: TicTacToeMove): Pos {
    val copy = toMutableMap()
    val remainMoves = copy.values.count { it == null }

    if (remainMoves == 0) {
        error("No available position")
    }
    if (remainMoves == 9) return Pos.CENTER

    var bestMove: Pos? = null

    fun miniMax(currentMove: TicTacToeMove, remainCount: Int): Int {
        val evaluate = copy.evaluate(nextMove)
        if (evaluate != 0 || remainCount == 0) return evaluate

        val pickMax = currentMove == nextMove

        val evaluations = copy.keys.shuffled().filter { copy.getValue(it) == null }.associateWith {
            copy[it] = currentMove
            val value = miniMax(currentMove.getOpposite(), remainCount - 1)
            copy[it] = null

            if ((pickMax && value == Int.MAX_VALUE) || (!pickMax && value == Int.MIN_VALUE)) {
                bestMove = it
                return value
            }

            value
        }
        val (pos, value) = (if (pickMax) evaluations.maxByOrNull { it.value } else evaluations.minByOrNull { it.value })!!
        bestMove = pos
        return value
    }

    miniMax(nextMove, remainMoves)
    return bestMove!!
}

private fun TicTacToe.evaluate(myMove: TicTacToeMove): Int {
    val func: (TicTacToeMove) -> Boolean = { move ->
        possibleLines.any { line -> line.all { getValue(it) == move } }
    }
    return when {
        func(myMove) -> Int.MAX_VALUE
        func(myMove.getOpposite()) -> Int.MIN_VALUE
        else -> 0
    }
}

typealias TicTacToe = Map<Pos, TicTacToeMove?>

