const amqp = require('amqplib');

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

// Nhận sự kiện từ RabbitMQ
async function consumeEvent() {
  const channel = await connectRabbitMQ();
  console.log(`Waiting for messages in ${queue}. To exit press CTRL+C`);

  channel.consume(queue, (msg) => {
    if (msg !== null) {
      const message = msg.content.toString();
      console.log(`Received: ${message}`);
      channel.ack(msg); // Xác nhận rằng message đã được nhận
    }
  });
}

// Bắt đầu lắng nghe
consumeEvent();
