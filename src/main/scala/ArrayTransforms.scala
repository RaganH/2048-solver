import scala.collection.immutable.Stack

object ArrayTransforms {

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
}
