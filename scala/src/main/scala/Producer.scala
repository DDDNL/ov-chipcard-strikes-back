package ov_chipcard


object Producer extends App {
  val log = org.slf4j.LoggerFactory.getLogger("Producer")

  val channel = new Channel(
    host = "localhost",
    exchange = "",
    queue = "ov-chipcard"
  )

  /* Replace with producer logic */
  channel publish SomethingHappened("something good")

  channel.close()
}

