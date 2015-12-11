package TwentyFortyEight.players

import scala.io.StdIn
import TwentyFortyEight.GameBoard

class ConsolePlayer extends Player {
  override def getNextMove(gameBoard : GameBoard) : GameBoard => GameBoard = {

    val key = StdIn.readLine()

    key match {
      case "w" => g => g.slideUp()
      case "a" => g => g.slideLeft()
      case "s" => g => g.slideDown()
      case "d" => g => g.slideRight()
      case _ => g => g
    }
  }
}
