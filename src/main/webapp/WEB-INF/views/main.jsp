<%--
  Created by IntelliJ IDEA.
  User: 清风一阵吹我心
  Date: 2018/11/9
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<html>
<head>

    <style rel="stylesheet">
        .message-div{
            width: 500px;
            min-height: 500px;
            margin: auto;
            padding: 0;
            background: coral;
            color: white;
        }
    </style>
    <title>聊天</title>

    <script type="text/javascript">
        //判断当前浏览器是否支持WebSocket
        var webSocket = null;
        if ('WebSocket' in window) {
            webSocket = new WebSocket("ws://localhost:8080/socket/127.0.0.1");
        } else if ('MozWebSocket' in window) {
            webSocket = new MozWebSocket("ws://localhost:8080/socket/127.0.0.1");
        } else {
            alert('Not support webSocket');
        }

        //打开socket,握手
        webSocket.onopen = function (event) {
            alert("websocket已经连接");
        }
        //接收推送的消息
        webSocket.onmessage = function (event) {
            console.info(event);
            $("<div></div>").append(event.data).appendTo($(".message-div"));
        }
        //错误时
        webSocket.onerror = function (event) {
            console.info("发生错误");
            alert("websocket发生错误" + event);
        }

        //关闭连接
        webSocket.onclose = function () {
            console.info("关闭连接");
        }

        //监听窗口关闭
        window.onbeforeunload = function (event) {
            webSocket.close();
        }

        //单发
        function sendMessageToUser() {
            if (webSocket.readyState == webSocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                var json = {"username": "admin"};
                webSocket.send(json); //发送json数据
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }

        //群发
        function sendMessageToAll() {
            if (webSocket.readyState == webSocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                webSocket.send(msg);
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }

    </script>

</head>
<body>
    <div class="message-div">

    </div>

</body>
</html>
