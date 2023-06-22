package com.example.backendnotas.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo", length = 60, nullable = false)
    private String titulo;

    @Column(name = "descripcion", length = 500, nullable = false)
    private String descripcion;

}
