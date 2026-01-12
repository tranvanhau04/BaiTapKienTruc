const amqp = require('amqplib');
const express = require('express');
const app = express();
const port = 8080;

const exchange = 'event_exchange';
const routingKey = 'event_routing_key';
const queue = 'event_queue';

// Kết nối với RabbitMQ
async function connectRabbitMQ() {
  const connection = await amqp.connect('amqp://localhost');
  const channel = await connection.createChannel();
  await channel.assertExchange(exchange, 'direct', { durable: true });
  await channel.assertQueue(queue, { durable: true });
  await channel.bindQueue(queue, exchange, routingKey);
  return channel;
}

// Gửi sự kiện vào RabbitMQ
async function sendEvent(message) {
  const channel = await connectRabbitMQ();
  channel.publish(exchange, routingKey, Buffer.from(message));
  console.log(`Sent: ${message}`);
}

// API gửi sự kiện
app.get('/sendEvent', (req, res) => {
  const message = 'Hello from Service 1!';
  sendEvent(message);
  res.send('Event Sent!');
});

// Bắt đầu server HTTP
app.listen(port, () => {
  console.log(`Service 1 running at http://localhost:${port}`);
});
