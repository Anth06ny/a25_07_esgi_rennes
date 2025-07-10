package org.example.a25_07_esgi_rennes.services;

import org.example.a25_07_esgi_rennes.model.MessageBean;
import org.example.a25_07_esgi_rennes.model.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
//@Transactional
public class MessageServiceTest {

    @Mock
    MessageRepository messageRepository; // = Mockito.mock(MessageRepository.class);

    @InjectMocks
    private MessageService messageService;

    @Test
    public void testCreateMockMessage() {
        // Préparation des données
        MessageBean messageBean = new MessageBean(0, "Alice", "Hello World");

        // Exécution de la méthode à tester
        messageService.addMessage(messageBean);

        //On vérifie que la méthode save du repository est bien appelée 1 fois avec le bon argument
        verify(messageRepository, times(1)).save(messageBean);
    }


    @Test
    public void testAddMessage(){
        // Préparation des données
        MessageBean messageBean = new MessageBean(0, "Alice", "Hello World");

        //On vient réécrire save pour quelle modifie l'id de l'objet reçu
        when(messageRepository.save(Mockito.any(MessageBean.class))).thenAnswer(invocation -> {
            MessageBean message = invocation.getArgument(0);
            // Simuler l'attribution d'un ID
            message.setId(1L);
            return message;
        });

        // Exécution de la méthode à tester
        messageService.addMessage(messageBean);

        assertEquals(1L, messageBean.getId(), "L'id n'a pas été modifié");

        // Exécution de la méthode à tester
        messageService.addMessage(messageBean);

        assertEquals(messageBean.getId(), 1L, "Id non ajouté");
        assertTrue(messageBean.getId() > 0, "L'id n'a pas été modifié");
        verify(messageRepository, times(1)).save(messageBean);

        when(messageRepository.findById(1L)).thenReturn(java.util.Optional.of(new MessageBean(1l, messageBean.getPseudo(), messageBean.getMessage())));

        MessageBean inDatabase = messageService.findById(messageBean.getId());

        assertNotNull(inDatabase, "Message non retrouvé en base");
        assertEquals(messageBean, inDatabase, "Les attributs sont différents");
        assertNotSame(messageBean, inDatabase, "C'est la même instance de message");
    }

    @Test
    public void getLast10Messages() {

        ArrayList<MessageBean> messages10 = new ArrayList<>();

        // Préparation des données
        for (int i = 1; i <= 15; i++) {
            MessageBean message = new MessageBean(0, "User" + i, "Message " + i);
            messageService.addMessage(message);
            messages10.add(message);
        }

        when(messageRepository.findTop10ByOrderByIdDesc()).thenReturn(messages10.subList(5, 15));

        // Exécution de la méthode à tester
        List<MessageBean> messages = messageService.getLast10Messages();

        // Vérifications
        assertEquals(10, messages.size());
        assertEquals("Message 15",  messages.getLast().getMessage());
    }
}