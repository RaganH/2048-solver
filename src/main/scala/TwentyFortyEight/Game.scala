package TwentyFortyEight

import TwentyFortyEight.players.{EagerlyMergingPlayer, ConsolePlayer}

object Game extends App {

  println("Starting 2048")

  var player = new EagerlyMergingPlayer()

  var gameBoard = GameBoard(4)

  while (!gameBoard.isFinished) {

    gameBoard.draw()
    println("---------------------")

    val slideDirection = player.getMoveDirection(gameBoard)

    if (gameBoard.canSlide(slideDirection))
    {
      gameBoard = gameBoard.slide(slideDirection)
    }
  }

  if (gameBoard.isFinished) {
    gameBoard.draw()
    println("---------------------")
    println("Game over!")
  }
}