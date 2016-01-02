package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard

class LargeTilesOnEdgeScorer extends BoardScorer {
  def scoreSingleGameBoard(gameBoard : GameBoard) : Double = {

    var tileValuesOnEdge = 0

    for (i <- 0 until gameBoard.size; j <- 0 until gameBoard.size;
         if (i == 0 || j == 0 || i == gameBoard.size -1 || j == gameBoard.size - 1)) {

      val tileScore = gameBoard.board(i)(j) match {
        case Some(x) => x
        case None => 0
      }

      tileValuesOnEdge += tileScore
    }

    val totalTileValues = gameBoard.board.flatMap(a => a).flatten.sum
    (tileValuesOnEdge:Double) / (totalTileValues)

  }
}
