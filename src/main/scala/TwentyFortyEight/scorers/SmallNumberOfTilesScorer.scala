package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

class SmallNumberOfTilesScorer extends BoardScorer {
  def scoreSingleGameBoard(gameBoard : GameBoard) : Int = {

    gameBoard.board.flatMap(a => a.map(b =>
      b match {
        case Some(_) => 0
        case None => 1
      }
    )).sum
  }
}