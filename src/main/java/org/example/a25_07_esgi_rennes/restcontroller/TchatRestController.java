package org.example.a25_07_esgi_rennes.restcontroller;

import jakarta.validation.Valid;
import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/tchat")
public class TchatRestController {

    private ArrayList<MessageBean> list = new ArrayList<>();

    //http://localhost:8080/tchat/saveMessage
    @PostMapping("/saveMessage")
    public void saveMessage(@Valid @RequestBody MessageBean message) {
        System.out.println("/saveMessage : " + message);
        list.add(message);
    }
    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    public ArrayList<MessageBean> allMessages() {
        System.out.println("/allMessages");

        //pour ne retourner que les 10 derniers
        ArrayList<MessageBean> toReturn = new ArrayList<>();
        for (int i = Math.max(list.size() - 10, 0); i < list.size(); i++) {
            toReturn.add(list.get(i));
        }

        return toReturn;
    }
}
