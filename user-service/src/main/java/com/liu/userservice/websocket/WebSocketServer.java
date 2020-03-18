package com.liu.userservice.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

@ServerEndpoint("/ws/asset")
@Component
public class WebSocketServer {

    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static final AtomicLong onlineCount = new AtomicLong(0);

    /**
     *  存放每个客户端对应的session对象
     */
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    @PostConstruct
    public void init(){
        System.out.println("websocket 加载");
    }

    @OnOpen
    public void onOpen(Session  session){
        sessionSet.add(session);
        long count = onlineCount.incrementAndGet();
        logger.info("有新连接加入， 当前连接数为："+count);
        sendMessage(session,"连接成功");
    }

    @OnClose
    public void onClose(Session session){
        sessionSet.remove(session);
        long count = onlineCount.decrementAndGet();
        logger.info("有连接关闭, 当前连接数为:{}",count);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        logger.info("来自客户端的消息:{}",message);
        sendMessage(session, "收到消息， 消息内容:"+message);
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        logger.error("发生错误:{}, session ID :{}",throwable.getMessage(),session.getId());
        throwable.printStackTrace();
    }



    public static void sendMessage(Session session,String message){
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("发送消息出错，{}",e.getMessage());
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message, String sessionId){
        Session session = null;
        session = sessionSet.stream().filter(session1 -> session1.getId().equals(sessionId)).findFirst().get();

        if(session != null){
            sendMessage(session,message);
        }else{
            logger.warn("没有找到你的回话id;{}",sessionId);
        }
    }

}
