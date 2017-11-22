lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := "score-store",
    version := "0.0.0",
    scalaVersion := "2.12.3",
    libraryDependencies ++= Seq(
      guice,
      "org.reactivemongo" % "play2-reactivemongo_2.12" % "0.12.7-play26"
    ),
  )
