import scala.util.Random

class GameBoard(val size : Int) {

  val board = Array.tabulate[Option[Int]](size, size)((x,y) => None)

  insertRandomNumber()
  insertRandomNumber()

  def insertRandomNumber(): Unit = {
    val random = new Random()

    val x = random.nextInt(size)
    val y = random.nextInt(size)

    board(x)(y) = Some(2)
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
