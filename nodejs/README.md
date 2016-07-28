Node.js Example
===============

# Setup

Install dependencies with npm run

    npm install

Start a local instance of RabbitMQ with [docker][]

    docker run -d --name amqp.test -p 5672:5672 -p 15672:15672 rabbitmq:management

Login on [rabbitmq management][] with guest / guest to see the messages and
queues.

# Produce several messages and have 1 consumer handle them all
- Run `./bin/consume` start a consumer that will listen to the queue for N seconds
- Run `./bin/produce` several times in a row in a new shell

[docker]: https://docs.docker.com/
[rabbitmq management]: http://localhost:15672/