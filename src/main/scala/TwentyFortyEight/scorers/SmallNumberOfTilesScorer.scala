package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

class SmallNumberOfTilesScorer extends BoardScorer {
  def scoreGameBoard(gameBoard : GameBoard) : Double = {

    val numberOfTiles = gameBoard.board.flatMap(a => a.map(b =>
      b match {
        case Some(_) => 0
        case None => 1
      }
    )).sum

    (numberOfTiles:Double) / (gameBoard.size * gameBoard.size)
  }
}



