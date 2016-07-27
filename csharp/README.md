C# Example
===========

This repository contains a simple console application as an example to get you started with producing and consuming messages on a RabbitMQ message queue.

# Setup
- Open the solution in Visual Studio
- Fill out the `uri` and `queueName` variables with the proper values in `Program.cs`
- Build the `MessagingExample` project

# Usage
- Run `MessagingExample.exe produce` to produce a message and publish it on the queue
- Run `MessagingExample.exe consume` to listen for messages on the queue. It keeps listening until you exit the program.
