package TwentyFortyEight.players

import TwentyFortyEight._

import scala.io.StdIn

class EagerlyMergingPlayer extends Player {
  override def getMoveDirection(gameBoard: GameBoard): Direction = {

    StdIn.readLine()

    val directions = Array(Left, Up, Right, Down)

    val availableDirections = directions.filter(d => gameBoard.canSlide(d))

    val directionsByNumberOfTiles = availableDirections.map(d => (d, averageGameBoardScore(gameBoard.allSlideOutcomes(d))))

    directionsByNumberOfTiles.reduceLeft((l, r) => if (l._2 > r._2) l else r)._1
  }

  def averageGameBoardScore(gameBoards : Seq[Chance[GameBoard]]) : Double = {

    val totalScore = gameBoards.map(c => Chance(c.probability, scoreSingleGameBoard(c.outcome)))
              .map(c => c.probability * c.outcome)
              .sum

    totalScore / gameBoards.length
  }

  def scoreSingleGameBoard(gameBoard : GameBoard) : Int = {

    gameBoard.board.flatMap(a => a.map(b =>
        b match {
          case Some(_) => 0
          case None => 1
        }
    )).sum
  }
}
