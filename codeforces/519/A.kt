fun main(args: Array<String>) {
    val board = mutableListOf<String>()
    repeat(8) {
        board.add(readLine()!!)
    }
    var whiteScore : Int = 0
    var blackScore : Int = 0
    for (i in 0..board.size-1) {
        whiteScore += addedWeight(board[i].toList().filter{it.isUpperCase()})
        blackScore += addedWeight(board[i].toList().filter{it.isLowerCase()})
    }
    print(winner(whiteScore, blackScore))
}

fun addedWeight(pieces : List<Char>): Int {
    var toAdd: Int = 0
    for (piece in pieces) {
        toAdd += weight(piece)
    }
    return toAdd
}

fun weight(piece: Char) = when(piece.toUpperCase()) {
    'Q' -> 9
    'R' -> 5
    'B', 'N' -> 3
    'P' -> 1
    else -> 0
}

fun winner(whiteScore: Int, blackScore: Int): String = when {
    whiteScore > blackScore -> "White"
    blackScore > whiteScore -> "Black"
    else -> "Draw"
}