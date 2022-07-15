package com.alkemy.MiDisneyKids.repositorios;

import com.alkemy.MiDisneyKids.entidades.Genero;
import com.alkemy.MiDisneyKids.entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, String> {

    @Query("SELECT g FROM genero g WHERE g.id=:id")
    public Personaje buscarPersonajePorId(@Param("Id") String id);
}
