const express = require("express");
const app = express();

app.get("/", (req, res) => {
  res.send("Hello from SERVER 2");
});

app.listen(3002, () => {
  console.log("Server 2 running at port 3002");
});
