package com.newlecture.webprj.chatservice;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/content/chatserver")
public class ChatServerEndPoint{
	private static Set<Session> clients =
			Collections.synchronizedSet(new HashSet <Session>());
	
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
		System.out.println("접속되었습니다");
		clients.add(session);
	}
	
	@OnMessage
	public void onTextMessage(Session session, String msg) throws IOException{
		System.out.println(msg);
		
		for(Session s:clients)
			s.getBasicRemote().sendText(msg);
	}
	
	@OnClose
	public void onClose(Session session){
		clients.remove(session);
		System.out.println("접속이 끊겼습니다");
		
	}
}
