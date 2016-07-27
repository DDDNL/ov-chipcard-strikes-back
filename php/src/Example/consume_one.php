<?php

require_once __DIR__ . "/../../vendor/autoload.php";

use Bunny\Client;
use Bunny\Message;
use Example\Config;

$config = Config::create();

$client = new Client($config->toArray());
$client->connect();
$channel = $client->channel();
$message = $channel->get($config->queueName());
if (! $message instanceof Message) {
    die('No message to consume');
}

$channel->ack($message);

echo "Received 1 new message from the queue {$config->queueName()} with content: $message->content" . PHP_EOL;