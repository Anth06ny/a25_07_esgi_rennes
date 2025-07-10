package org.example.a25_07_esgi_rennes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //c'est l'id est il est auto incrément
    @Size(min = 3, message = "Il faut au moins 3 caractères")
    private String pseudo;
    @NotBlank(message = "Le message est vide")
    private String message;
}
