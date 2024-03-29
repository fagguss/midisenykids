package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.entidades.Personaje;
import com.alkemy.MiDisneyKids.entidades.Foto;
import com.alkemy.MiDisneyKids.entidades.Pelicula;
import com.alkemy.MiDisneyKids.errores.ErrorServicio;
import com.alkemy.MiDisneyKids.repositorios.PersonajeRepositorio;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PersonajeServicio {

    @Autowired
    private PersonajeRepositorio personajeRepo;
    @Autowired
    private FotoServicio fotoServicio;

//-----------------------CRUD------------------
    @Transactional
    public void crear(String nombre, String edad, Double peso,
            String historia, MultipartFile fotoPersonaje, List<Pelicula> peliculas) throws ErrorServicio, IOException {

        validar(nombre, edad, peso, historia);

        Personaje personaje = new Personaje();

        personaje.setNombre(nombre);
        personaje.setEdad(edad);
        personaje.setPeso(peso);
        personaje.setHistoria(historia);
        personaje.setPeliculas(peliculas);


        Foto foto = fotoServicio.guardar(fotoPersonaje);
        personaje.setFotoPersonaje(foto);

        personajeRepo.save(personaje);

    }
    
//    @Transactional
//    public void crear() throws ErrorServicio, IOException {
//
////        validar(nombre, edad, peso, historia);
//
//        Personaje personaje = new Personaje();
//        Pelicula pelicula=new Pelicula(); 
//
//        personaje.setNombre("Dumbo");
//        personaje.setEdad("5");
//        personaje.setPeso(3.4);
//        personaje.setHistoria("un elefante pequeño que es cruelmente apodado Dumbo (en inglés, dumb significa tanto «mudo» como, despectivamente, «tonto»), y que es ridiculizado por sus grandes orejas, aunque descubre que puede volar usándolas como alas.");
//        personaje.setPeliculas((List<Pelicula>) pelicula);
//        String fotoPersonaje="/image.png";
//
//        
//        Foto foto = fotoServicio.guardar(fotoPersonaje);
//        personaje.setFotoPersonaje(foto);
//
//        personajeRepo.save(personaje);
//
//    }

    @Transactional
    public Personaje buscarPersonajePorId(String id) throws ErrorServicio {

        Optional<Personaje> respuesta = personajeRepo.findById(id);

        if (respuesta.isPresent()) {
            Personaje personaje = respuesta.get();
            return personaje;
        } else {
            throw new ErrorServicio("No se encuentra personaje");
        }

    }

    @Transactional
    public List<Personaje> mostrarPersonajes() {

        List<Personaje> personajes = personajeRepo.findAll();
        return personajes;

    }

    @Transactional
    public Personaje modificar(String id, String nombre, String edad,
            String historia, Double peso, MultipartFile archivo) throws ErrorServicio, IOException {

        Optional<Personaje> respuesta = personajeRepo.findById(id);

        if (respuesta.isPresent()) {

            validar(nombre, edad, peso, historia);

            Personaje personaje = respuesta.get();

            personaje.setNombre(nombre);
            personaje.setEdad(edad);
            personaje.setHistoria(historia);
            personaje.setPeso(peso);

            Foto foto = fotoServicio.guardar(archivo);
            personaje.setFotoPersonaje(foto);

            personajeRepo.save(personaje);

            System.out.println("Personaje " + personaje + " editado en la DB");

            return personaje;

        } else {
            throw new ErrorServicio("El personaje que quiere modificar"
                    + " no se encuentra");
        }

    }

    @Transactional
    public void eliminar(String id) throws ErrorServicio {

        Optional<Personaje> respuesta = personajeRepo.findById(id);
        if (respuesta.isPresent()) {
            Personaje personaje = respuesta.get();
            personajeRepo.delete(personaje);

        } else {
            throw new ErrorServicio("El personaje no se enccuentra en la DB");
        }

    }

    //--------------VALIDACIONES-------------------
    public void validar(String nombre, String edad, Double peso,
            String historia) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del personaje no puede ser nulo");
        }
        if (edad == null || edad.isEmpty()) {
            throw new ErrorServicio("La edad del personaje no puede ser nulo");
        }
        if (peso == null) {
            throw new ErrorServicio("El peso del personaje no puede ser nulo");
        }
        if (historia == null || nombre.isEmpty()) {
            throw new ErrorServicio("La historia del personaje no puede ser nulo");
        }
//        if (pelicula == null || nombre.isEmpty()) {
//            throw new ErrorServicio("El personaje debe tener una pelicula asignada");
//        }

    }
}
