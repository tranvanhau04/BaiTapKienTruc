const express = require("express");
const app = express();

app.get("/", (req, res) => {
  res.send("Hello from SERVER 1");
});

app.listen(3001, () => {
  console.log("Server 1 running at port 3001");
});
