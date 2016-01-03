package TwentyFortyEight.scorers

import TwentyFortyEight.GameBoard
import org.specs2.mutable.Specification

object SmallNumberOfTilesScorerSpec extends Specification {

   "score gameboard" should {

     "score 1 for an empty gameboard" in {
       val gameBoard = new GameBoard(2, Array(
         Array(None, None),
         Array(None, None)
       ))

       val scorer = new SmallNumberOfTilesScorer()

       scorer.scoreGameBoard(gameBoard) must beEqualTo(1.0)
     }

     "score 0 for a full gameboard" in {
       val gameBoard = new GameBoard(2, Array(
         Array(Some(2), Some(2)),
         Array(Some(2), Some(2))
       ))

       val scorer = new SmallNumberOfTilesScorer()

       scorer.scoreGameBoard(gameBoard) must beEqualTo(0.0)
     }

     "score 0.5 for a half full gameboard" in {
       val gameBoard = new GameBoard(2, Array(
         Array(None, Some(2)),
         Array(Some(2), None)
       ))

       val scorer = new SmallNumberOfTilesScorer()

       scorer.scoreGameBoard(gameBoard) must beEqualTo(0.5)
     }
   }
}