package org.example.a25_07_esgi_rennes.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBean {
    @Size(min = 3, message = "Il faut au moins 3 caractères")
    private String pseudo;
    @NotBlank(message = "Le message est vide")
    private String message;
}
