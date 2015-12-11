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

      val board = new GameBoard(array)

      board.isFinished must beFalse

    }

    "be true when there is no more space for a tile" in {

      val array = Array(
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(2), Some(2)),
        Array[Option[Int]](Some(2), Some(2), Some(4), Some(2))
      )

      val board = new GameBoard(array)

      board.isFinished must beTrue

    }

  }
}