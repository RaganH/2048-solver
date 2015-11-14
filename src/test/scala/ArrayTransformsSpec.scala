import org.specs2.mutable._

object ArrayTransformsSpec extends Specification {
  "slideLeft" should {

    "merge consecutive twos" in {
      val array = Array(Some(2),Some(2),None,None)

      ArrayTransforms.slideLeft(array) must beEqualTo(Array(Some(4), None,None,None))
    }

    "remove gaps but preserve order" in {
      val array = Array(Some(2),None,Some(4),None)

      ArrayTransforms.slideLeft(array) must beEqualTo(Array(Some(2), Some(4),None,None))
    }

  }
}

