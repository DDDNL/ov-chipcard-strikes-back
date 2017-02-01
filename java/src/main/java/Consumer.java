package ov_chipcard;


@lombok.extern.slf4j.Slf4j(topic="Consumer")
class Consumer {

  static void handle(Event event) {
    log.info("Received event:\n{}", event.toString());

    /* Extend with actual consumer logic*/
  }


  /** Plumbing code */
  public static void main(String[] args) {
    Channel channel = new Channel();
    log.info("Waiting for messages. To exit press ENTER");
    channel.subscribe(Consumer::handle);
    System.console().readLine();
    channel.close();
  }
}

