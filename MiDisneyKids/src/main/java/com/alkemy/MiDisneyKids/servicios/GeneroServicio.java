
package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.entidades.Genero;
import com.alkemy.MiDisneyKids.errores.ErrorServicio;
import com.alkemy.MiDisneyKids.repositorios.GeneroRepositorio;
import com.alkemy.MiDisneyKids.repositorios.PeliculaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicio {
    
    @Autowired
    private GeneroRepositorio generoRepo; 
    @Autowired
    private PeliculaRepositorio peliculaRepo;

    //-----------------------CRUD------------------
    
    @Transactional
    public void crear(String nombre) throws ErrorServicio {

        validar(nombre);

        Genero genero = new Genero();

        genero.setNombre(nombre);
        
        generoRepo.save(genero);

    }

    @Transactional
    public List<Genero> mostrarGeneros() {

        List<Genero> generos = generoRepo.findAll();
        return generos;

    }

    @Transactional
    public void modificar(String id, String nombre) throws ErrorServicio {

        Optional<Genero> respuesta = generoRepo.findById(id);

        if (respuesta.isPresent()) {

            validar(nombre);

            Genero genero = respuesta.get();

            genero.setNombre(nombre);
            
            generoRepo.save(genero);

            System.out.println("Genero " + genero + " editado en la DB");

        } else {
            throw new ErrorServicio("El genero que quiere modificar"
                    + " no se encuentra");
        }

    }

    @Transactional
    public void eliminar(String id, String nombre) throws ErrorServicio {

        Optional<Genero> respuesta = generoRepo.findById(id);
        if (respuesta.isPresent()) {
            Genero genero = respuesta.get();
            generoRepo.delete(genero);

        } else {
            throw new ErrorServicio("El genero no se enccuentra en la DB");
        }

    }

    //--------------VALIDACIONES-------------------
    
    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del genero no puede ser nulo");
        }

    }
}
