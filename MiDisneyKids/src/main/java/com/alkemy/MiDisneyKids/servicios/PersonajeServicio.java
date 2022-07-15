package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.entidades.Pelicula;
import com.alkemy.MiDisneyKids.entidades.Personaje;
import com.alkemy.MiDisneyKids.repositorios.PersonajeRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepo;

//-----------------------CRUD------------------
    
    public void crear(String id, String nombre, String edad, Double peso, String historia, List<Pelicula> peliculas) {

        Personaje personaje = new Personaje();

        personaje.setId(id);
        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);

        personajeRepo.save(personaje);

    }
    
    public void eliminar(String id, String nombre){
        
        personajeRepo.buscarPersonajePorId(id); 
        
        
        personajeRepo.delete(personaje);
    }
}
