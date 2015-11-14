import org.specs2.mutable._
object GameBoardSpec extends Specification {
  "GameBoard" should {

    "start with exactly 2 tiles" in {
        val board = GameBoard(4)
        val numberOfDefinedTiles = board.board
                    .flatMap((x : Array[Option[Int]]) => x)
                    .count((y: Option[Int]) => y.isDefined)

        numberOfDefinedTiles must beEqualTo(2)
      }
  }
}