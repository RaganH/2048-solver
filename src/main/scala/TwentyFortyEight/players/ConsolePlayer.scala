package TwentyFortyEight.players

import scala.io.StdIn
import TwentyFortyEight.{Down, Up, Direction, GameBoard}

class ConsolePlayer extends Player {
  override def getMoveDirection(gameBoard : GameBoard) : Direction = {

    val key = StdIn.readLine()

    key match {
      case "w" => Up
      case "a" => TwentyFortyEight.Left
      case "s" => Down
      case "d" => TwentyFortyEight.Right
      case _ => getMoveDirection(gameBoard)
    }
  }
}
