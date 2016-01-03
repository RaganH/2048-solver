package TwentyFortyEight.players

import TwentyFortyEight._
import TwentyFortyEight.scorers.BoardScorer

class ExpectimaxPlayer(recursionDepth : Int, scorer: BoardScorer) extends Player {

  val directions = Array(Left, Up, Right, Down)

  override def getMoveDirection(gameBoard: GameBoard): Direction = {

    player(gameBoard, recursionDepth)._1

  }

  def machine(gameBoard: GameBoard, slideDirection : Direction, recursionLimit : Int) : Double = {

    val gameBoards = gameBoard.allSlideOutcomes(slideDirection)

    val totalScore = gameBoards.map(c => Chance(c.probability, player(c.outcome, recursionLimit)._2))
      .map(c => c.probability * c.outcome)
      .sum

    totalScore / gameBoards.length
  }

  def player(gameBoard: GameBoard, recursionLimit : Int) : (Direction, Double) = {

    if (recursionLimit > 0)
    {
      val availableDirections = directions.filter(d => gameBoard.canSlide(d))

      if (availableDirections.isEmpty) {
        //game is over
        (Left, 0)
      }
      else {
        val scoredDirections = availableDirections.map(d => (d, machine(gameBoard, d, recursionLimit-1)))

        scoredDirections.reduceLeft((l, r) => if (l._2 > r._2) l else r)
      }
    }
    else {
      (Left, scorer.scoreSingleGameBoard(gameBoard))
    }
  }
}