<?php

require_once __DIR__ . "/../../vendor/autoload.php";

use Bunny\Channel;
use Bunny\Client;
use Bunny\Message;
use Example\Config;

$config = Config::create();

$client = new Client($config->toArray());
$client->connect();

$channel = $client->channel();
$channel->consume(
    function (Message $message, Channel $channel, Client $client) use ($config) {
        $channel->ack($message);

        echo "Received 1 new message from the queue {$config->queueName()} with content: $message->content" . PHP_EOL;
    },
    $config->queueName()
);

$client->run($config->listenTime());