
package com.alkemy.MiDisneyKids.repositorios;

import com.alkemy.MiDisneyKids.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, String>  {
    
    @Query("SELECT p FROM Personaje p WHERE p.nombre=:nombre")
    public Personaje buscarPersonajePorNombre(@Param("nombre") String nombre); 
}
