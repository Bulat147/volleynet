<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>Chat</title>
    <script>
        let webSocket;

        function connect() {
            webSocket = new WebSocket("ws://localhost:8080/chat");
            webSocket.onmessage = function receive(response) {
                let json = JSON.parse(response["data"]);
                $('#messagesList').last().after("<li>" + json['pageId'] + ' ' + json['text'] + "</li>")
            }
        }

        function sendMessage(text, pageId) {
            let message = {
                "text": text,
                "pageId": pageId
            }
            webSocket.send(JSON.stringify(message));
        }


    </script>
</head>
<body onload="connect()">

    <label for="message">Message</label><br>
    <input type="text" id="message" name="message"/>
    <button onclick="sendMessage($('#message').val(), '${pageId}')">Send</button>

    <h2>Messages</h2>
    <ul id="messagesList">

    </ul>
</body>
</html>