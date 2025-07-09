package org.example.a25_07_esgi_rennes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {
    private Long id;
    private String login;
    private String password;
}