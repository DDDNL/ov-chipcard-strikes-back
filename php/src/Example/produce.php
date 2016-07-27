<?php

require_once __DIR__ . "/../../vendor/autoload.php";

use Bunny\Client;
use Example\Config;

$message = 'hello world';

$config = Config::create();

$client = new Client($config->toArray());
$client->connect();
$channel = $client->channel();
$channel->publish($message, [], '', $config->queueName());

echo "Added 1 new message to the queue {$config->queueName()} with content: $message" . PHP_EOL;