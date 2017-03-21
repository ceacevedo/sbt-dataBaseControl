name := "sbt-dataBaseControl"
organization := "com.co.devco"
version := "1.0"
scalaVersion := "2.10.6"
sbtPlugin := true

resolvers ++= Seq(
  "Typesafe Releases"                at "http://repo.typesafe.com/typesafe/maven-releases/"
)

libraryDependencies ++= Seq(
  "org.slf4j"                   % "slf4j-nop"                     % "1.6.4",
  "com.typesafe.slick"          %% "slick"                        % "3.1.0",
  "com.typesafe.slick"          %% "slick-extensions"             % "3.1.0"
)