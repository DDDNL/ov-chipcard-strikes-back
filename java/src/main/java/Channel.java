package ov_chipcard;

import java.util.*;
import java.util.function.Consumer;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.nio.charset.StandardCharsets;
import com.rabbitmq.client.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;


@lombok.extern.slf4j.Slf4j(topic="Channel")
class Channel {

  private ObjectMapper mapper = new ObjectMapper();

  private ConnectionFactory factory;
  private Connection connection;
  private com.rabbitmq.client.Channel channel;
  private String exchange = "";
  private String queue = "ov-chipcard";


  public Channel() {
    this("amqp://guest:guest@localhost:5672/%2f", "", "ov-chipcard");
  }

  public Channel(String aBrokerUri, String anExchange, String aQueue) {
    try {
      factory = new ConnectionFactory();
      factory.setUri(aBrokerUri);
      connection = factory.newConnection();
      channel = connection.createChannel();

      exchange = anExchange;
      queue = aQueue;

      channel.queueDeclare(
        queue,
        /* durable */ true,
        /* exclusive */ false,
        /* autoDelete */ false,
        /* arguments */ null
      );
    } catch (Exception ex) {
      log.error("Oopsie", ex);
    }
  }

  public void publish(Event event) {
    try {

      final Map<String, Object> map = new HashMap<>();
      map.put(event.getClass().getSimpleName(), event);

      String message = mapper.writeValueAsString(map);

      channel.basicPublish(
        /* exchange */ exchange,
        /* routingKey */ queue,
        /* props */ null,
        /* body */ message.getBytes(StandardCharsets.UTF_8));

      log.debug("Sent to queue {} message\n{}", queue, message);
    } catch(Exception ex) {
      log.error("Couldn't send to queue", ex);
    }
  }


  public void subscribe(Consumer<Event> thunk) {
    try {
      channel.basicConsume(
        queue,
        /* autoAck */ true,
        new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(
            String consumerTag, Envelope envelope,
            AMQP.BasicProperties properties, byte[] body
          ) throws IOException {
            String message = new String(body, StandardCharsets.UTF_8);
            TreeNode tree = mapper.readTree(message);
            String eventClassName = tree.fieldNames().next();
            Class<?> cls;

            try {
              cls = Class.forName("ov_chipcard.Event$"+eventClassName);
            } catch(ClassNotFoundException ex) {
              throw new RuntimeException("No event with name '"+eventClassName+"' found", ex);
            }

            Event event = Event.class.cast(
              mapper.readValue(tree.get(eventClassName).traverse(mapper), cls));

            log.debug("Received from queue {} message:\n{}", queue, message);

            thunk.accept(event);
          }
        }
      );
    } catch (Exception ex) {
      log.error("Had troubles consuming message", ex);
    }
  }

  public void close() {
    try {
      channel.close();
      connection.close();
    } catch (IOException|TimeoutException ex) {
      log.error("Can't close channel and/or connection", ex);
    }
  }
}
