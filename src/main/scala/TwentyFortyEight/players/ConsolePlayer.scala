package TwentyFortyEight.players

import scala.io.StdIn
import TwentyFortyEight.{Left, Right, Down, Up, Direction, GameBoard}

class ConsolePlayer extends Player {
  override def getMoveDirection(gameBoard : GameBoard) : Direction = {

    val key = StdIn.readLine()

    key match {
      case "w" => Up
      case "a" => Left
      case "s" => Down
      case "d" => Right
      case _ => getMoveDirection(gameBoard)
    }
  }
}

