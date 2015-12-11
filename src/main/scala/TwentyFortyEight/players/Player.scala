package TwentyFortyEight.players

import TwentyFortyEight.GameBoard

trait Player {
  def getNextMove(gameBoard : GameBoard) : GameBoard => GameBoard
}
