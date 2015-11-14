import scala.util.Random

object GameBoard{

  def apply(size : Int) : GameBoard = {

    val board = Array.tabulate[Option[Int]](size, size)((x,y) => None)

    insertRandomNumber(board, 2)
    insertRandomNumber(board, 2)

    new GameBoard(board)
  }

  def insertRandomNumber(board: Array[Array[Option[Int]]], toInsert : Int) : Unit = {
    //TODO: Fix this, doesn't always overwrite empty cell
    val random = new Random()

    val x = random.nextInt(board.length)
    val y = random.nextInt(board.length)

    board(x)(y) = Some(toInsert)
  }

}

class GameBoard(val board : Array[Array[Option[Int]]]) {

  def slideLeft() : GameBoard = {

    val newBoard = board.map(
      a => ArrayTransforms.slideLeft(a)
    )

    GameBoard.insertRandomNumber(newBoard, 2)

    new GameBoard(newBoard)
  }

  def draw(): Unit = {

    val maxTile = board.flatten.map((x) => x.getOrElse(0)).max
    val maxWidth = maxTile.toString.length

    val stringBoard = board.map(
        x => x.map(y => intToFixedLengthString(y, maxWidth)
      )
    )

    val consoleString = stringBoard.map(
        x => x.mkString("|")
      ).mkString("\n")

    println(consoleString)
  }

  def intToFixedLengthString(x : Option[Int], maxWidth : Int) : String = {
    x match {
      case None => "".padTo(maxWidth, " ").mkString
      case Some(z) => z.toString.padTo(maxWidth, " ").mkString
    }
  }
}
