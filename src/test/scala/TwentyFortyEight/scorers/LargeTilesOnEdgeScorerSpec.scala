package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard
import org.specs2.mutable.Specification

object LargeTilesOnEdgeScorerSpec extends Specification {

  "score gameboard" should {

    "score 1 when all tiles are on the edge" in {
      val gameBoard = new GameBoard(3, Array(
        Array(Some(2), Some(2), Some(2)),
        Array(Some(2), None, Some(2)),
        Array(Some(2), Some(2), Some(2))
      ))

      val scorer = new LargeTilesOnEdgeScorer()

      scorer.scoreGameBoard(gameBoard) must beEqualTo(1.0)
    }

    "score 0 when no tiles are on the edge" in {
      val gameBoard = new GameBoard(3, Array(
        Array(None, None, None),
        Array(None, Some(2048), None),
        Array(None, None, None)
      ))

      val scorer = new LargeTilesOnEdgeScorer()

      scorer.scoreGameBoard(gameBoard) must beEqualTo(0.0)
    }

    "score high when high value tiles on the edge" in {
      val gameBoard = new GameBoard(3, Array(
        Array(None, None, None),
        Array(Some(2048), Some(2), None),
        Array(None, None, None)
      ))

      val scorer = new LargeTilesOnEdgeScorer()

      scorer.scoreGameBoard(gameBoard) must beEqualTo((2048:Double)/2050)
    }

    "score low when high value tiles in the middle" in {
      val gameBoard = new GameBoard(3, Array(
        Array(None, None, None),
        Array(Some(2), Some(2048), None),
        Array(None, None, None)
      ))

      val scorer = new LargeTilesOnEdgeScorer()

      scorer.scoreGameBoard(gameBoard) must beEqualTo((2:Double)/2050)
    }
  }
}
