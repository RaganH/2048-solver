package TwentyFortyEight.players

import TwentyFortyEight.{Left, Right, Up, Down, GameBoard, Direction}

import scala.io.StdIn

class EagerlyMergingPlayer extends Player {
  override def getMoveDirection(gameBoard: GameBoard): Direction = {

    StdIn.readLine()

    val directions = Array(Left, Up, Right, Down)

    val availableDirections = directions.filter(d => gameBoard.canSlide(d))

    val directionsByNumberOfTiles = availableDirections.map(d => (d, scoreGameBoard(gameBoard.slide(d))))

    directionsByNumberOfTiles.reduceLeft((l, r) => if (l._2 > r._2) l else r)._1
  }

  def scoreGameBoard(gameBoard : GameBoard) : Int = {

    gameBoard.board.flatMap(a => a.map(b =>
        b match {
          case Some(_) => 0
          case None => 1
        }
    )).sum
  }
}
