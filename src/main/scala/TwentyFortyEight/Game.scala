package TwentyFortyEight

import TwentyFortyEight.players.{ExpectimaxPlayer, ConsolePlayer}
import TwentyFortyEight.scorers.{LargeTilesOnEdgeScorer, CompositeScorer, SmallNumberOfTilesScorer}

object Game extends App {

  println("Starting 2048")

  val player = new ExpectimaxPlayer(2, new CompositeScorer(Array(
    (0.5, new SmallNumberOfTilesScorer()),
    (0.5, new LargeTilesOnEdgeScorer())
  )))

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