import org.specs2.mutable._

object ArrayTransformsSpec extends Specification {
  "slideLeft" should {

    "do nothing for array of None" in {
      val array = Array[Option[Int]](None, None, None, None)

      ArrayTransforms.slideLeft(array) must beEqualTo(Array[Option[Int]](None, None, None, None))
    }

    "merge consecutive twos" in {
      val array = Array(Some(2),Some(2),None,None)

      ArrayTransforms.slideLeft(array) must beEqualTo(Array(Some(4), None,None,None))
    }

    "remove gaps but preserve order" in {
      val array = Array(Some(2),None,Some(4),None)

      ArrayTransforms.slideLeft(array) must beEqualTo(Array(Some(2), Some(4),None,None))
    }

    "not modify full array with no adjacent numbers" in {
      val array = Array[Option[Int]](Some(2),Some(4),Some(2),Some(4))

      ArrayTransforms.slideLeft(array) must beEqualTo(Array[Option[Int]](Some(2),Some(4),Some(2),Some(4)))
    }
  }

  "slideRight" should {

    "do nothing for array of None" in {
      val array = Array[Option[Int]](None, None, None, None)

      ArrayTransforms.slideRight(array) must beEqualTo(Array[Option[Int]](None, None, None, None))
    }

    "merge consecutive twos" in {
      val array = Array(Some(2),Some(2),None,None)

      ArrayTransforms.slideRight(array) must beEqualTo(Array(None, None,None,Some(4)))
    }

    "remove gaps but preserve order" in {
      val array = Array(Some(2),None,Some(4),None)

      ArrayTransforms.slideRight(array) must beEqualTo(Array(None, None,Some(2),Some(4)))
    }

    "not modify full array with no adjacent numbers" in {
      val array = Array[Option[Int]](Some(2),Some(4),Some(2),Some(4))

      ArrayTransforms.slideRight(array) must beEqualTo(Array[Option[Int]](Some(2),Some(4),Some(2),Some(4)))
    }
  }
}

