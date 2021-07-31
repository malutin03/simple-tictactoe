package tictactoe
import kotlin.math.abs

fun main() {
    // ------------------------- 0st variant -------------
    var gameFieldStr = "         ".toCharArray()

    //here all variants of win
    val winSituationsStr = arrayOf(
        "***      ",
        "   ***   ",
        "      ***",
        "*  *  *  ",
        " *  *  * ",
        "  *  *  *",
        "*   *   *",
        "  * * *  "
    )

    showGameField(gameFieldStr)

    var countOfX = 0
    var countOfO = 0
    var countOf_ = 9
    var isWinsX = false
    var isWinsO = false
    var isDraw = false

    var coordStr : String
    var arrCoordStr: List<String>
    var x = 0
    var y = 0
    var itWasCorrectInput = false
    var symbolPlayer = 'X'
    var curSymbol: Char

    do { //main game loop

        // ************ input cycle (player makes move) with checks
        do {
            print("Enter the coordinates:")
            itWasCorrectInput = false
            coordStr = readLine()!!
            arrCoordStr = coordStr.split(" ")
            if (arrCoordStr.size < 2) {
                println("You must enter 2 numbers!")
            } else if (arrCoordStr[0].length > 1 || arrCoordStr[1].length > 1) {
                println("Your coordinates too large!")
            } else {
                if (arrCoordStr[0][0] > '9' || arrCoordStr[0][0] < '0' || arrCoordStr[1][0] > '9' || arrCoordStr[1][0] < '0') {
                    println("You should enter numbers!")
                } else {
                    x = arrCoordStr[0].toInt()
                    y = arrCoordStr[1].toInt()
                    if (x > 3 || y > 3 || x < 1 || y < 1) {
                        println("Coordinates should be from 1 to 3!")
                    } else {
                        curSymbol = gameFieldStr[(x - 1) * 3 + y - 1]
                        if (curSymbol != ' ') {
                            println("This cell is occupied!")
                        } else {
                            itWasCorrectInput = true
                        }
                    }
                }
            }
        } while (!itWasCorrectInput)

        if (itWasCorrectInput) {
            gameFieldStr[(x - 1) * 3 + y - 1] = symbolPlayer
            showGameField(gameFieldStr)
            symbolPlayer = if (symbolPlayer == 'X') 'O' else 'X'
        }

        countOfX = countOf(gameFieldStr,'X')
        countOfO = countOf(gameFieldStr,'O')
        countOf_ = countOf(gameFieldStr,' ')
        isWinsX = checkForWin(gameFieldStr,'X', winSituationsStr)
        isWinsO = checkForWin(gameFieldStr,'O', winSituationsStr)
        isDraw = countOf_ == 0

    } while (!isWinsO && !isWinsX && !isDraw)

    // output result of the game
    if (abs(countOfO - countOfX) > 1 || (isWinsX && isWinsO))
        println("Impossible")
    else if (isWinsX)
        println("X wins")
    else if (isWinsO)
        println("O wins")
    else if (countOf_ == 0)
        println("Draw")

}


fun showGameField(str: CharArray) {
    println("---------")
    println("| ${str[0]} ${str[1]} ${str[2]} |")
    println("| ${str[3]} ${str[4]} ${str[5]} |")
    println("| ${str[6]} ${str[7]} ${str[8]} |")
    println("---------")
}

fun checkForWin(gameField : CharArray, playerSym : Char, winSituationsStr : Array<String>): Boolean {
    var isFound: Boolean
    for (element in winSituationsStr) {
        isFound = true
        for (i in 0 until element.length) {
            if (element[i] == '*' && gameField[i] != playerSym) isFound = false
        }
        if (isFound) return true
    }
    return false
}

fun countOf(gameField : CharArray, sym : Char): Int {
    var count = 0
    for (i in 0 until gameField.size) {
        if (gameField[i] == sym) count++
    }
    return count
}
