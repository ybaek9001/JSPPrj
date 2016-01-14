package com.newlecture.webprj.chatservice;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@ServerEndpoint(value="/content/chatserver"/*, configurator = HttpServletSessionConfigurator.class*/)
public class ChatServerEndPoint {
   private static Set<Session> clients = 
         Collections.synchronizedSet(new HashSet<Session>());
   private HttpSession httpSession;
   
   @OnOpen
   public void onOpen(Session session, EndpointConfig config){
      System.out.println("접속이 되었습니다.");
      
      /*this.httpSession = (HttpSession) config
                        .getUserProperties()
                        .get(HttpSession.class.getName());
      
      System.out.println(httpSession.getAttribute("uid"));*/
      clients.add(session);
   }
   @OnMessage
   public void onTextMessage(Session session,String data) throws IOException, ParseException{
      System.out.println(data);
       JSONParser jsonParser = new JSONParser();
         //JSON데이터를 넣어 JSON Object 로 만들어 준다.
         JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

         String uid = (String)jsonObject.get("uid");
         String msg = (String) jsonObject.get("msg");
      
         System.out.println(String.format("uid:%s,msg:%s\n",uid,msg));
      for(Session s : clients)
         s.getBasicRemote().sendText(data);
   }
   @OnClose
   public void onClose(Session session){
      clients.remove(session);
      System.out.println("접속끈겼나");
   }
}