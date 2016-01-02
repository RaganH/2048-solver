package TwentyFortyEight.players

import TwentyFortyEight._
import TwentyFortyEight.scorers.BoardScorer

class ExpectimaxPlayer(scorer: BoardScorer) extends Player {
  override def getMoveDirection(gameBoard: GameBoard): Direction = {

    val directions = Array(Left, Up, Right, Down)

    val availableDirections = directions.filter(d => gameBoard.canSlide(d))

    val scoredDirections = availableDirections.map(d => (d, averageGameBoardScore(gameBoard.allSlideOutcomes(d))))

    scoredDirections.reduceLeft((l, r) => if (l._2 > r._2) l else r)._1
  }

  def averageGameBoardScore(gameBoards : Seq[Chance[GameBoard]]) : Double = {

    val totalScore = gameBoards.map(c => Chance(c.probability, scorer.scoreSingleGameBoard(c.outcome)))
      .map(c => c.probability * c.outcome)
      .sum

    totalScore / gameBoards.length
  }
}