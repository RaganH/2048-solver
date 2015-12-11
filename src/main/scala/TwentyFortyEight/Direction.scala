package TwentyFortyEight

sealed trait Direction { def name: String }

case object Up extends Direction { val name = "Up" }
case object Down extends Direction { val name = "Down" }
case object Left extends Direction { val name = "Left" }
case object Right extends Direction { val name = "Right" }