 
package com.alkemy.MiDisneyKids.entidades;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@Data 
public class Foto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy =   "uuid2")
    private String id;
    
    private String nombre;
    
    private String mime; 
    
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] contenido; 
  
    
}
