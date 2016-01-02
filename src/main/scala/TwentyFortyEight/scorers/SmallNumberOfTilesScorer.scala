package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

class SmallNumberOfTilesScorer extends BoardScorer {
  def scoreSingleGameBoard(gameBoard : GameBoard) : Int = {

    val numberOfTiles = gameBoard.board.flatMap(a => a.map(b =>
      b match {
        case Some(_) => 0
        case None => 1
      }
    )).sum

    numberOfTiles / gameBoard.size
  }
}