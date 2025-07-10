package org.example.a25_07_esgi_rennes.controller;

import org.example.a25_07_esgi_rennes.config.WebSocketConfig;
import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.example.a25_07_esgi_rennes.services.MessageService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

    private MessageService messageService;
    private SimpMessagingTemplate messagingTemplate;

    public WebSocketController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    //Acceder à la page  http://localhost:8080/ws/websocket
    @GetMapping("/websocket")
    public String showWebSocketPage() {
        return "TestWebSocket";
    }

    @MessageMapping("/chat") // Chemin complet : /ws/chat
    public void receiveMessage(MessageBean message) {
        System.out.println("/ws/chat " + message.getPseudo() + " : " + message.getMessage());
        messageService.addMessage(message);

        // Envoyer la liste des messages sur le channel
        messagingTemplate.convertAndSend(WebSocketConfig.CHANNEL_NAME , messageService.getLast10Messages());
    }

    //A mettre dans le controller
    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if (WebSocketConfig.CHANNEL_NAME.equals(headerAccessor.getDestination())) {
            messagingTemplate.convertAndSend(WebSocketConfig.CHANNEL_NAME , messageService.getLast10Messages());
        }
    }

}
