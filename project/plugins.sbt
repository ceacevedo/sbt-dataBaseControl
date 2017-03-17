sbtPlugin := true

name := "sbt-databasecontrol"

organization := "com.co.devco"


addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

// plain library (not an sbt plugin) for use in the build definition
libraryDependencies ++= Seq(
  "com.typesafe.slick"          %% "slick-extensions"             % "3.1.0",
  "com.typesafe.slick"          %% "slick-codegen"                % "3.1.0",
  "oracle"                      %  "ojdbc"                        % "7"
)

resolvers ++= Seq(
  "Typesafe Releases"                at "http://repo.typesafe.com/typesafe/maven-releases/"
)