package com.socket.web.quartz;

import com.socket.web.config.WebSocketServer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 清风一阵吹我心
 * @ProjectName: socket
 * @Package: com.socket.web.quartz
 * @ClassName: TestJob
 * @Description:
 * @CreateDate: 2018/11/12 12:50
 * @Version: 1.0
 **/
public class TestJob {

    public void doSomething() {
        //获取WebSocketServer对象的映射。
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        if (map.size() != 0) {
            for (Map.Entry<String, WebSocketServer> entry : map.entrySet()) {
                WebSocketServer webSocketServer = entry.getValue();
                try {
                    //向客户端推送消息
                    webSocketServer.getSession().getBasicRemote().sendText("每隔两秒,向客户端推送一次数据");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("WebSocket未连接");
        }
    }
}
