
package com.alkemy.MiDisneyKids.repositorios;

import com.alkemy.MiDisneyKids.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String> {
    
}
