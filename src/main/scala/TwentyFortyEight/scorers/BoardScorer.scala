package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

trait BoardScorer {
  def scoreSingleGameBoard(gameBoard: GameBoard): Double
}
