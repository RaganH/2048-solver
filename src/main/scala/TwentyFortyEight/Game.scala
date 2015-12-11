package TwentyFortyEight

import TwentyFortyEight.players.ConsolePlayer

object Game extends App {

  println("Starting 2048")

  var player = new ConsolePlayer()

  var gameBoard = GameBoard(4)

  while(!gameBoard.isFinished){

    gameBoard.draw()
    println("---------------------")

    val move = player.getNextMove(gameBoard)

    gameBoard = move(gameBoard)
  }

  if (gameBoard.isFinished) {
    gameBoard.draw()
    println("---------------------")
    println("Game over!")
  }
}




