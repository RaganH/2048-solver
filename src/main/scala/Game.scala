import scala.io.StdIn

object Game extends App {

  println("Starting 2048")

  var gameBoard = GameBoard(4)
  gameBoard.draw()

  var gameRunning = true
  while(gameRunning){

    val key = StdIn.readLine()

    println("---------------------")

    if (key.isEmpty)
      gameRunning = false

    key match {
      case "a" => gameBoard = gameBoard.slideLeft()
      case "d" => gameBoard = gameBoard.slideRight()
      case _ =>
    }


    gameBoard.draw()
  }

}
