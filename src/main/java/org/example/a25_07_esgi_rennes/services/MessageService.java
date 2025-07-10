package org.example.a25_07_esgi_rennes.services;

import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.example.a25_07_esgi_rennes.model.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(MessageBean message) {
        message.setId(0);
        messageRepository.save(message);
    }

    public MessageBean findById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<MessageBean> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<MessageBean> getLast10Messages() {
        return messageRepository.findTop10ByOrderByIdDesc();
    }


}
