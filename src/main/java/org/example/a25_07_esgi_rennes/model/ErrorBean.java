package org.example.a25_07_esgi_rennes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//Quel format d'erreur, on souhaite
@Data
@AllArgsConstructor //-constructeurs plein
@NoArgsConstructor  //-constructeurs vide
public class ErrorBean {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> details;
}