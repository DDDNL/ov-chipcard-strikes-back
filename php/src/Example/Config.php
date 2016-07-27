<?php

namespace Example;

class Config {

    private $host;
    private $vhost;
    private $user;
    private $password;
    private $queue;
    private $listenTime;

    public function queueName()
    {
        return $this->queue;
    }

    public function listenTime()
    {
        return $this->listenTime;
    }

    public function toArray()
    {
        return [
            'host' => $this->host,
            'vhost' => $this->vhost,
            'user' => $this->user,
            'password' => $this->password,
        ];
    }

    public static function fromArray(array $array)
    {
        $config = new static();
        foreach ($array as $key => $value) {
            if (property_exists($config, $key)) {
                $config->{$key} = $value;
            }
        }

        return $config;
    }

    public static function create()
    {
        if ( ! is_file(__DIR__ . '/params.php')) {
            throw new \Exception('File params.php can not be found');
        }
        $config = require_once __DIR__ . '/params.php';

        return self::fromArray($config);
    }
}