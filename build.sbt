name := "raspi-akkatyped-blink"

maintainer := "frederic@auberson.net"

version := "1.0"

scalaVersion := "2.12.7"

lazy val akkaVersion = "2.6.0-M5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.pi4j" % "pi4j-core" % "1.2",
  "com.pi4j" % "pi4j-native" % "1.2",
  "junit" % "junit" % "4.12"
)

enablePlugins(JavaServerAppPackaging)
