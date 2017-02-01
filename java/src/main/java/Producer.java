package ov_chipcard;

import java.util.Arrays;
import ov_chipcard.Event.*;


@lombok.extern.slf4j.Slf4j(topic="Producer")
class Producer {

  public static void main(String[] args) {
    Channel channel = new Channel();

    /* Replace with actual producer logic */
    for(Event e : Arrays.asList(
      new SomethingHappened("something good"),
      new ErIsIetsGebeurd("iets goed", "nu")
    )) {
      log.info("Published event:\n"+e.toString());
      channel.publish(e);
    };

    channel.close();
  }

}

