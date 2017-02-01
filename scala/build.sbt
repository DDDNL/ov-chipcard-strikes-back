name := "ov-chipcard"

scalaVersion := "2.12.1"

val circeVersion = "0.6.1"

libraryDependencies ++= {
  Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion) ++
  Seq(
    "com.rabbitmq" % "amqp-client" % "4.0.1",
    "ch.qos.logback" % "logback-classic" % "1.1.8"
  )
}

cancelable in Global := true
