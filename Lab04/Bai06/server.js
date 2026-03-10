const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
  res.send('Hello Multi-stage Build! 🚀 <br/> Stage 1 (node:18) đã build và truyền code sang Stage 2 (node:18-alpine) thành công.');
});

app.listen(port, () => {
  console.log(`App running on port ${port}`);
});