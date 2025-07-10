package org.example.a25_07_esgi_rennes.restcontroller;

import jakarta.validation.Valid;
import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.example.a25_07_esgi_rennes.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tchat")
public class TchatRestController {

    private MessageService messageService;

    public TchatRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    //http://localhost:8080/tchat/saveMessage
    @PostMapping("/saveMessage")
    public void saveMessage(@Valid @RequestBody MessageBean message) {
        System.out.println("/saveMessage : " + message);
        messageService.addMessage(message);
    }
    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    public List<MessageBean> allMessages() {
        System.out.println("/allMessages");

        return messageService.getLast10Messages();
    }
}
