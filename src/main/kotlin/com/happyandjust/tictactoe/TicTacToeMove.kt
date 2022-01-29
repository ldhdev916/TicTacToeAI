package com.happyandjust.tictactoe

enum class TicTacToeMove {
    O {
        override fun getOpposite() = X
    },
    X {
        override fun getOpposite() = O
    };

    abstract fun getOpposite(): TicTacToeMove;
}