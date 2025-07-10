package org.example.projetreactif;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("movie") // Nom de la table dans la base de données
public class Movie {
    @Id // Indique la clé primaire
    private Long id;
    private String title;
    private Integer length;

    //GETTER Setter
}
