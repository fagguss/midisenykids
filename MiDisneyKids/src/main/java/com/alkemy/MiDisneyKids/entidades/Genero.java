
package com.alkemy.MiDisneyKids.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Data 
public class Genero {
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id; 
    
    private String nombre; 
    
    @OneToOne
    private Foto foto; 
    
    @OneToMany
    private List<Pelicula> peliculas; 
    
}
