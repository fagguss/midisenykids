package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.entidades.Pelicula;
import com.alkemy.MiDisneyKids.entidades.Foto;
import com.alkemy.MiDisneyKids.entidades.Personaje;
import com.alkemy.MiDisneyKids.enumeraciones.EnumCalificacion;
import com.alkemy.MiDisneyKids.errores.ErrorServicio;
import com.alkemy.MiDisneyKids.repositorios.PeliculaRepositorio;
import com.alkemy.MiDisneyKids.repositorios.PersonajeRepositorio;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepo;
    @Autowired
    private FotoServicio fotoServicio;
    @Autowired
    private PersonajeRepositorio personajeRepo;

    //-----------------------CRUD------------------
    
    @Transactional
    public void crear(String titulo, Date fechaCreacion,
            EnumCalificacion calificacion, MultipartFile archivo, List<Personaje> personajes) throws ErrorServicio, IOException {

        validar(titulo, fechaCreacion, calificacion);

        Pelicula pelicula = new Pelicula();

        pelicula.setTitulo(titulo);
        pelicula.setFechaCreacion(fechaCreacion);
        pelicula.setCalificacion(calificacion);
        pelicula.setPersonajes(personajes);
        
        Foto foto=fotoServicio.guardar(archivo); 
        pelicula.setFotoPelicula(foto);

        peliculaRepo.save(pelicula);

    }

    @Transactional
    public List<Pelicula> mostrarPeliculas() {

        List<Pelicula> peliculas = peliculaRepo.findAll();
        return peliculas;

    }

    @Transactional
        public Pelicula modificar(String id, String titulo, Date fechaCreacion,
            EnumCalificacion calificacion, MultipartFile archivo) throws ErrorServicio, IOException {

        Optional<Pelicula> respuesta = peliculaRepo.findById(id);

        if (respuesta.isPresent()) {

            validar(titulo, fechaCreacion, calificacion);

            Pelicula pelicula = respuesta.get();

            pelicula.setTitulo(titulo);
            pelicula.setFechaCreacion(fechaCreacion);
            pelicula.setCalificacion(calificacion);
            
            Foto foto=fotoServicio.guardar(archivo); 
            pelicula.setFotoPelicula(foto);

            peliculaRepo.save(pelicula);

            System.out.println("Pelicula " + pelicula + " editada en la DB");
            
            return pelicula; 

        } else {
            throw new ErrorServicio("La pelicula que quiere modificar"
                    + " no se encuentra");
        }

    }

    @Transactional
    public void eliminar(String id) throws ErrorServicio {

        Optional<Pelicula> respuesta = peliculaRepo.findById(id);
        if (respuesta.isPresent()) {
            Pelicula pelicula = respuesta.get();
            peliculaRepo.delete(pelicula);

        } else {
            throw new ErrorServicio("La pelicula no se enccuentra en la DB");
        }

    }

    
    //--------------VALIDACIONES-------------------
    
    public void validar(String titulo, Date fechaCreacion,
            EnumCalificacion calificacion) throws ErrorServicio {

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El titulo de la pelicula no puede ser nulo");
        }
        if (fechaCreacion == null) {
            throw new ErrorServicio("La fecha de la pelicula no puede ser nulo");
        }
        if (calificacion == null) {
            throw new ErrorServicio("La calificacion de la pelicula no puede ser nulo");
        }

    }

}
