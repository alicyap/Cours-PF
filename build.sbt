ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "TD-PF"
  )

libraryDependencies += "io.vavr" % "vavr" % "0.10.4"