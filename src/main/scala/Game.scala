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

    if (key == "l")
      gameBoard = gameBoard.slideLeft()

    gameBoard.draw()
  }

}
