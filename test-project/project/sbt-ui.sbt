// This plugin represents functionality that is to be added to sbt in the future

addSbtPlugin("org.scala-sbt" % "sbt-core-next" % "0.1.1")

lazy val root = Project("plugins", file(".")) dependsOn(sbt_dataBaseControl)
lazy val sbt_dataBaseControl = file("..").getAbsoluteFile.toURI