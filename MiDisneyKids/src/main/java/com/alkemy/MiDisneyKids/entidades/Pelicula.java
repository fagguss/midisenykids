
package com.alkemy.MiDisneyKids.entidades;

import com.alkemy.MiDisneyKids.enumeraciones.EnumCalificacion;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
@lombok.AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;
    
    private String titulo; 
    
    @OneToOne
    private Foto fotoPelicula; 
    
    private Date fechaCreacion; 
    
    private EnumCalificacion calificacion;  
    
    @OneToMany
    private List<Personaje> personajes; 
    
    
}
