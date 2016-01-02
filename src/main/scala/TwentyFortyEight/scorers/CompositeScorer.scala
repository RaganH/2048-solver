package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

class CompositeScorer(weightedScorers : Seq[(Double, BoardScorer)]) extends BoardScorer {
  override def scoreSingleGameBoard(gameBoard: GameBoard): Double = {

    weightedScorers.map( { case (weight, scorer) =>
      weight * scorer.scoreSingleGameBoard(gameBoard)
    }).sum

  }
}
