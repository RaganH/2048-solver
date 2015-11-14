libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.6.5" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")

lazy val root = (project in file(".")).settings(
    name := "2048",
    version := "0.1.0",
    scalaVersion := "2.11.7",
    mainClass := Option("Game")
	)
