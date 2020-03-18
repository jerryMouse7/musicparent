package com.liu.userservice.websocket.endpoint;

import com.liu.userservice.entity.UserBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/api/websocket/{key}/{name}")
public class WebSocketEndPoint {

}
