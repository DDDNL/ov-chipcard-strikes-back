package ov_chipcard

import java.nio.charset.StandardCharsets.UTF_8
import org.slf4j.LoggerFactory.getLogger
import com.rabbitmq.client._

import io.circe._, generic.auto._, parser._, syntax._


class Channel(
  host: String = "docker-host",
  exchange: String = "",
  queue: String = "ov-chipcard"
) {
  private val log = getLogger("Channel")

  private val factory = new ConnectionFactory {
    setHost(host)
  }

  private val connection = factory.newConnection()
  private val channel = connection.createChannel()

  channel.queueDeclare(
    queue,
    /* durable */ true,
    /* exclusive */ false,
    /* autoDelete */ false,
    /* arguments */ null
  )

  def publish(event: Event): Unit = {
    val message = event.asJson.spaces2

    channel.basicPublish(
      /* exchange */ exchange,
      /* routingKey */ queue,
      /* props */ null,
      /* body */ message.getBytes(UTF_8))

    log.debug(s"Sent to queue $queue message\n$message")
  }

  def subscribe(thunk: Event => Unit): Unit = {
    channel.basicConsume(
      queue,
      /* autoAck */ true,
      new DefaultConsumer(channel) {
        override def handleDelivery(
          consumerTag: String, envelope: Envelope,
          properties: AMQP.BasicProperties, body: Array[Byte]
        ) {
          val message = new String(body, UTF_8);
          log.debug(s"Received from queue $queue message:\n$message")

          decode[Event](message).fold(
            ex => log.error("Couldn't decode message", ex),
            thunk
          )
        }
      }
    )

  }

  def close(): Unit = {
    channel.close()
    connection.close()
  }
}
