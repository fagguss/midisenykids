package com.alkemy.MiDisneyKids.entidades;

import com.alkemy.MiDisneyKids.enumeraciones.EnumRol;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String clave;
    @Enumerated(EnumType.STRING)
    private EnumRol rol;
    private Boolean alta;

}
