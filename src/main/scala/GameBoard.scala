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
}
