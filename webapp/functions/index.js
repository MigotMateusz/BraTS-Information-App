const functions = require("firebase-functions");

const express = require('express');

exports.bigben = functions.https.onRequest((req, res) => {
    const hours = (new Date().getHours() % 12) + 1  // London is UTC + 1hr;
  res.status(200).send(`<!doctype html>
    <head>
      <title>Time</title>
    </head>
    <body>
      ${'BONG '.repeat(hours)}
    </body>
  </html>`);
});

const app = express();

const port = 8000;
app.listen(port,()=> {
    console.log('listen port 8000');
});

app.get('/hello_world', (req,res)=>{
    res.send('Hello World');
});