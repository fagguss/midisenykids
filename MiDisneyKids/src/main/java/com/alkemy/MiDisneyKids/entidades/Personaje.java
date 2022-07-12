
package com.alkemy.MiDisneyKids.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
@lombok.AllArgsConstructor
public class Personaje {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy="uuid2")
    private String id; 
    
    private String nombre;
    
    @OneToOne
    private Foto fotoPersonaje; 
    
    private String edad; 
    
    private Double peso; 
    
    private String historia;
    
    @OneToMany
    private List<Pelicula> peliculas; 
    
    

    
}
