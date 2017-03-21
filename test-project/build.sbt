import slick.lifted.TableQuery

name := """test-project"""

mainClass in Compile := Some("HelloSlick")

scalaVersion := "2.11.6"

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.h2database" % "h2" % "1.3.175",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

slickDataBaseConfig := "h2mem1"

//slickTables := List(TableQuery[Coffees], TableQuery[Suppliers])

enablePlugins(DataBaseControlPlugin)

fork in run := true

