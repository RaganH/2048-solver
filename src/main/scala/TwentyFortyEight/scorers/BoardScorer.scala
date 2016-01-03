package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

trait BoardScorer {
  def scoreGameBoard(gameBoard: GameBoard): Double
}
