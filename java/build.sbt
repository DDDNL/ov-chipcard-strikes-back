name := "ov-chipcard"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  Seq(
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.0",
    "org.projectlombok" % "lombok" % "1.16.12",
    "com.rabbitmq" % "amqp-client" % "4.0.1",
    "ch.qos.logback" % "logback-classic" % "1.1.8"
  )
}

cancelable in Global := true
