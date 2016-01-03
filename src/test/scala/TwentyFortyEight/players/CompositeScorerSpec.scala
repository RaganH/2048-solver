package TwentyFortyEight.players

import TwentyFortyEight.GameBoard
import TwentyFortyEight.scorers.{BoardScorer, CompositeScorer}
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

object CompositeScorerSpec extends Specification with Mockito {

    "creation" should {

      "throw invalid argument exception when no scorers are passed" in {

        new CompositeScorer(Array[(Double, BoardScorer)]()) must throwA[IllegalArgumentException]("Cannot combine no scorers")

      }
    }

    "score single gameboard" should {

      "apply weighting to single scorer" in {
        val gameBoard = mock[GameBoard]

        val oneScorer = mock[BoardScorer]
        oneScorer.scoreSingleGameBoard(gameBoard) returns 1d

        val compositeScorer = new CompositeScorer(Array((0.5, oneScorer)))

        compositeScorer.scoreSingleGameBoard(gameBoard) must beEqualTo(0.5)
      }

      "weight then sum multiple scorers" in {
        val gameBoard = mock[GameBoard]

        val oneScorer = mock[BoardScorer]
        oneScorer.scoreSingleGameBoard(gameBoard) returns 1d

        val twoScorer = mock[BoardScorer]
        twoScorer.scoreSingleGameBoard(gameBoard) returns 2d

        val compositeScorer = new CompositeScorer(Array(
          (0.4, oneScorer),
          (0.6, twoScorer)
        ))

        compositeScorer.scoreSingleGameBoard(gameBoard) must beEqualTo(1.6)
      }
    }
}
