package TwentyFortyEight

import scala.util.Random

object GameBoard{

  def apply(size : Int) : GameBoard = {

    val board = Array.tabulate[Option[Int]](size, size)((x,y) => None)

    insertRandomNumber(board)
    insertRandomNumber(board)

    new GameBoard(size, board)
  }

  def insertRandomNumber(board: Array[Array[Option[Int]]]) : Unit = {

    val random = new Random()

    val emptyCoords = findEmptyCoords(board)

    val (x,y) = emptyCoords(random.nextInt(emptyCoords.length))

    val toInsert = if (random.nextDouble() < 0.75) 2 else 4

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

class GameBoard(val size : Int, val board : Array[Array[Option[Int]]]) {

  def isFinished : Boolean = {
    !canSlide(Left) && !canSlide(Right) && !canSlide(Up) && !canSlide(Down)
  }

  def canSlide(direction : Direction) : Boolean = {

    def canSlideLeft : Boolean = {
      board.exists(ArrayTransforms.canSlideLeft(_))
    }

    def canSlideRight : Boolean = {
      board.exists(ArrayTransforms.canSlideRight(_))
    }

    def canSlideUp : Boolean = {
      val slices = for(i <- 0 until board.length) yield getVerticalSlice(board, i)

      slices.exists(s => ArrayTransforms.canSlideLeft(s.toArray))
    }

    def canSlideDown : Boolean = {
      val slices = for(i <- 0 until board.length) yield getVerticalSlice(board, i)

      slices.exists(s => ArrayTransforms.canSlideRight(s.toArray))
    }

    direction match {
      case Left => canSlideLeft
      case Right => canSlideRight
      case Up => canSlideUp
      case Down => canSlideDown
    }
  }

  def slide(direction : Direction) : GameBoard = {

    val newBoard = performSlide(direction)

    GameBoard.insertRandomNumber(newBoard)

    new GameBoard(size, newBoard)
  }

  def allSlideOutcomes(direction : Direction) : Seq[Chance[GameBoard]] = {

    val newBoard = performSlide(direction)

    GameBoard.findEmptyCoords(newBoard).flatMap({
      case (x, y) => Array(
        Chance(0.75, new GameBoard(size, insertAt(newBoard, x, y, 2))),
        Chance(0.25, new GameBoard(size, insertAt(newBoard, x, y, 4)))
      )
    })
  }

  def insertAt(board : Array[Array[Option[Int]]], x : Int, y : Int, value : Int) : Array[Array[Option[Int]]] = {

    val copy = board.map(x => x.map(y => y))

    copy(x)(y) = Some(value)

    copy
  }

  private[GameBoard] def performSlide(direction : Direction) : Array[Array[Option[Int]]] = {

    def slideLeft() : Array[Array[Option[Int]]] = {
      board.map(a => ArrayTransforms.slideLeft(a))
    }

    def slideRight(): Array[Array[Option[Int]]] = {
      board.map(a => ArrayTransforms.slideRight(a))
    }

    def slideUp(): Array[Array[Option[Int]]] = {
      val slices = for(i <- 0 until board.length) yield getVerticalSlice(board, i)

      val transformedSlices = slices.map(s => ArrayTransforms.slideLeft(s.toArray))

      val newBoard = Array.tabulate[Option[Int]](board.length, board.length)((x,y) => None)

      for(i <- 0 until board.length; j <- 0 until board.length)
        newBoard(i)(j) = transformedSlices(j)(i)

      newBoard
    }

    def slideDown(): Array[Array[Option[Int]]] = {
      val slices = translateBoardToVertical

      val transformedSlices = slices.map(s => ArrayTransforms.slideRight(s.toArray))

      translateSlicesFromVertical(transformedSlices)
    }

    direction match {
      case Left => slideLeft
      case Right => slideRight
      case Up => slideUp
      case Down => slideDown
    }
  }

  def translateSlicesFromVertical(transformedSlices: IndexedSeq[Array[Option[Int]]]): Array[Array[Option[Int]]] = {
    val newBoard = Array.tabulate[Option[Int]](board.length, board.length)((x,y) => None)

    for (i <- 0 until board.length; j <- 0 until board.length)
      newBoard(i)(j) = transformedSlices(j)(i)
    newBoard
  }

  def translateBoardToVertical: IndexedSeq[Seq[Option[Int]]] = {
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