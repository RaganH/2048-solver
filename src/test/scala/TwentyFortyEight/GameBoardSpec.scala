package TwentyFortyEight

import org.specs2.mutable._
object GameBoardSpec extends Specification {
  "At start" should {

    "contain exactly 2 tiles" in {
        val board = GameBoard(4)
        val numberOfDefinedTiles = board.board
                    .flatMap((x : Array[Option[Int]]) => x)
                    .count((y: Option[Int]) => y.isDefined)

        numberOfDefinedTiles must beEqualTo(2)
      }
  }

  "isFinished" should {

    "be false when there is an empty tile" in {

      val array = Array(
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), None, Some(2))
      )

      val board = new GameBoard(4, array)

      board.isFinished must beFalse

    }

    "be false when board is full but merges available" in {

      val array = Array(
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(4), Some(2))
      )

      val board = new GameBoard(4, array)

      board.isFinished must beFalse

    }

    "be true when there is no possible merges" in {

      val array = Array(
        Array[Option[Int]](Some(2),  Some(16), Some(2),  Some(16)),
        Array[Option[Int]](Some(4),  Some(8),  Some(4),  Some(8)),
        Array[Option[Int]](Some(8),  Some(4),  Some(8),  Some(4)),
        Array[Option[Int]](Some(16), Some(2),  Some(16), Some(2))
      )

      val board = new GameBoard(4, array)

      board.isFinished must beTrue

    }
  }
}