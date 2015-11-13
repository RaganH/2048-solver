lazy val root = (project in file(".")).settings(
    name := "2048",
    version := "0.1.0",
    scalaVersion := "2.11.7",
    mainClass := Option("Game")
	)
