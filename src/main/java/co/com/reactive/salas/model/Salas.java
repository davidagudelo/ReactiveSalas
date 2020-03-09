package co.com.reactive.salas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salas {

    @Id
    private String id;
    private String bloque;
    private int piso;
    private int capacidad;
    private String nombre;
    private int horasDiponible;
    private boolean disponible;
}

