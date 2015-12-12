package TwentyFortyEight

import scala.collection.immutable.Stack

object ArrayTransforms {
  def canSlideRight(boardSlice: Array[Option[Int]]): Boolean = {
    val newSlice = slideRight(boardSlice)

    newSlice.deep != boardSlice.deep
  }

  def canSlideLeft(boardSlice: Array[Option[Int]]): Boolean = {
    val newSlice = slideLeft(boardSlice)

    newSlice.deep != boardSlice.deep
  }

  def slideRight(boardSlice: Array[Option[Int]]): Array[Option[Int]] = {

    val reversedSlice = boardSlice.reverse

    slideLeft(reversedSlice).reverse
  }

  def slideLeft(boardSlice: Array[Option[Int]]): Array[Option[Int]] = {

    var stack = Stack[MergedInt]()

    for (tile <- boardSlice)
      tile match {
        case None =>
        case Some(x) => {

          if (!stack.isEmpty && !stack.top.merged && stack.top.value == x)
            stack = stack.pop.push(MergedInt(true, x * 2))
          else
            stack = stack.push(MergedInt(false, x))
        }
      }

    stack.map(_.value).toArray.reverse.map(x => Some(x)).padTo(boardSlice.length, None)
  }
}

case class MergedInt(merged: Boolean, value : Int) { }