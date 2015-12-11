package TwentyFortyEight

import scala.collection.immutable.Stack

object ArrayTransforms {
  
  def canSlide(boardSlice: Array[Option[Int]]): Boolean = {
    if(boardSlice.forall(t => !t.isDefined))
      return false // empty row

    if (!boardSlice.forall(t => t.isDefined))
      return true // not empty but not full (has gaps)

    //row is full
    val tiles = boardSlice.map(t => t.get)
    val tilePairs = tiles zip tiles.drop(1)

    tilePairs.exists({case (i,j) => i == j})
  }

  def slideLeft(boardSlice: Array[Option[Int]]): Array[Option[Int]] = {

    var stack = Stack[Int]()

    for (tile <- boardSlice)
      tile match {
        case None =>
        case Some(x) => {

          if (!stack.isEmpty && stack.top == x)
            stack = stack.pop.push(x * 2)
          else
            stack = stack.push(x)
        }
      }

    stack.toArray.reverse.map(x => Some(x)).padTo(boardSlice.length, None)
  }

  def slideRight(boardSlice: Array[Option[Int]]): Array[Option[Int]] = {

    val reversedSlice = boardSlice.reverse

    slideLeft(reversedSlice).reverse
  }
}
