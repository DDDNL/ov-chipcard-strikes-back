PHP Example
===========

# Setup
- Run `composer install`
- Goto `src/Example`
- Create a new `params.php` file in `src/Example` by copying `params.php.dist`
- Fill out credentials in `params.php`

# Produce and consume single message
- Run `php produce.php`      // creates a message on the queue
- Run `php consume_one.php`  // reads a message off the queue

# Produce several messages and have 1 consumer handle them all
- Run `php consume_all.php`  // start a consumer that will listen to the queue for N seconds
- Run `php produce.php`      // several times in a row in a new shell
