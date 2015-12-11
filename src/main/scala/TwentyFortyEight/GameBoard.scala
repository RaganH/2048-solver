package TwentyFortyEight

import scala.util.Random

object GameBoard{

  def apply(size : Int) : GameBoard = {

    val board = Array.tabulate[Option[Int]](size, size)((x,y) => None)

    insertRandomNumber(board, 2)
    insertRandomNumber(board, 2)

    new GameBoard(board)
  }

  def insertRandomNumber(board: Array[Array[Option[Int]]], toInsert : Int) : Unit = {

    val random = new Random()

    val emptyCoords = findEmptyCoords(board)

    val (x,y) = emptyCoords(random.nextInt(emptyCoords.length))

    board(x)(y) = Some(toInsert)
  }

  def findEmptyCoords(board: Array[Array[Option[Int]]]) : Seq[(Int, Int)] = {

    for (i <- 0 until board.length;
         j <- 0 until board.length
        if board(i)(j) == None)
        yield {
            (i,j)
        }
  }
}

class GameBoard(val board : Array[Array[Option[Int]]]) {

  def isFinished : Boolean = {

    for (i <- 0 until board.length; j <- 0 until board.length)
      board(i)(j) match {
        case Some(x) =>
        case None => return false
      }

    true
  }

  def slideLeft() : GameBoard = {

    val newBoard = board.map(
      a => ArrayTransforms.slideLeft(a)
    )

    GameBoard.insertRandomNumber(newBoard, 2)

    new GameBoard(newBoard)
  }

  def slideRight(): GameBoard = {

    val newBoard = board.map(a => ArrayTransforms.slideRight(a))

    GameBoard.insertRandomNumber(newBoard, 2)

    new GameBoard(newBoard)
  }

  def slideUp(): GameBoard = {

    val slices = for(i <- 0 until board.length) yield getVerticalSlice(board, i)

    val transformedSlices = slices.map(s => ArrayTransforms.slideLeft(s.toArray))

    val newBoard = Array.tabulate[Option[Int]](board.length, board.length)((x,y) => None)

    for(i <- 0 until board.length; j <- 0 until board.length)
      newBoard(i)(j) = transformedSlices(j)(i)

    GameBoard.insertRandomNumber(newBoard, 2)

    new GameBoard(newBoard)
  }

  def slideDown(): GameBoard = {

    val slices = TranslateBoardToVertical

    val transformedSlices = slices.map(s => ArrayTransforms.slideRight(s.toArray))

    val newBoard = TranslateSlicesFromVertical(transformedSlices)

    GameBoard.insertRandomNumber(newBoard, 2)

    new GameBoard(newBoard)
  }

  def TranslateSlicesFromVertical(transformedSlices: IndexedSeq[Array[Option[Int]]]): Array[Array[Option[Int]]] = {
    val newBoard = Array.tabulate[Option[Int]](board.length, board.length)((x,y) => None)

    for (i <- 0 until board.length; j <- 0 until board.length)
      newBoard(i)(j) = transformedSlices(j)(i)
    newBoard
  }

  def TranslateBoardToVertical: IndexedSeq[Seq[Option[Int]]] = {
    for (i <- 0 until board.length)
      yield getVerticalSlice(board, i)
  }

  def getVerticalSlice[T](array : Array[Array[T]], colIndex : Int) : Seq[T] = {
    for(i <- 0 until array.length) // TODO: This is only correct for square arrays
      yield array(i)(colIndex)
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
