lazy val root = Project("plugins", file(".")) dependsOn(sbt_dataBaseControl)
lazy val sbt_dataBaseControl = file("..").getAbsoluteFile.toURI