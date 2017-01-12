Scala Example
===============

# Setup

Start sbt

    ./sbt

Start a local instance of RabbitMQ

    docker-compose up -d

Login on [rabbitmq management](http://localhost:15672) with guest / guest to see the messages and
queues.

# To publish several events and have consumer handle them all, in any order
- Within one sbt session run `runMain ov_chipcard.Consumer` to start a consumer that will listen to the queue until ENTER is hit
- Within another sbt session run `runMain ov_chipcard.Producer` several times 

