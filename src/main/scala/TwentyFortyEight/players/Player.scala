package TwentyFortyEight.players

import TwentyFortyEight.{Direction, GameBoard}

trait Player {
  def getMoveDirection(gameBoard : GameBoard) : Direction
}
