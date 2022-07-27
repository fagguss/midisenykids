package com.alkemy.MiDisneyKids.controladores;

import com.alkemy.MiDisneyKids.servicios.GeneroServicio;
import com.alkemy.MiDisneyKids.servicios.PeliculaServicio;
import com.alkemy.MiDisneyKids.servicios.PersonajeServicio;
import com.alkemy.MiDisneyKids.entidades.Pelicula;
import com.alkemy.MiDisneyKids.entidades.Personaje;
import com.alkemy.MiDisneyKids.enumeraciones.EnumCalificacion;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("")
public class PortalControlador {

    @Autowired
    private PersonajeServicio personajeServicio;

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    private GeneroServicio generoServicio;

    @GetMapping("/")
    public String index() {
        return "inicio";
    }

//----------------PEROSONAJES-----------------------
    @GetMapping("/personajeForm")
    public String personajeForm(ModelMap model) {
        List<Pelicula> peliculas = peliculaServicio.mostrarPeliculas();
        model.put("peliculas", peliculas);
        return "personajeForm";
    }

    @PostMapping("/agregarPersonaje")
    public String agregarPersonaje(@RequestParam String nombre,
            @RequestParam MultipartFile fotoPersonaje, @RequestParam String edad,
            @RequestParam Double peso, @RequestParam String historia,@RequestParam List<Pelicula> peliculas) {

        try {
            personajeServicio.crear(nombre, edad, peso, historia, fotoPersonaje, peliculas);
            System.out.println("Personaje creado con exito");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear personaje nuevo");
        }

        return "personajeForm";
    }

//---------------------PELICULAS-----------------------------------
    @GetMapping("/peliculaForm")
    public String peliculaForm() {
        return "peliculaForm";
    }

    @PostMapping("/agregarPelicula")
    public String agregarPelicula(@RequestParam String titulo,
            @RequestParam MultipartFile archivo, @RequestParam Date fechaCreacion,
            @RequestParam EnumCalificacion calificacion) {

        try {
            peliculaServicio.crear(titulo, fechaCreacion, calificacion, archivo);
            System.out.println("Pelicula creada con exito");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear pelicula nueva");
        }

        return "peliculaForm";
    }

//---------------------GENEROS-----------------------------------
    @GetMapping("/generoForm")
    public String generoForm() {
        return "generoForm";
    }

    @PostMapping("/agregarGenero")
    public String agregarGenero(@RequestParam String nombre,
            @RequestParam MultipartFile archivo) {

        try {
            generoServicio.crear(nombre, archivo);
            System.out.println("Genero creada con exito");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear genero nuevo");
        }

        return "generoForm";
    }

//--------------------------Listas-------------------------------
    @GetMapping("/peliculas")
    public String peliculas(ModelMap model) {
        List<Pelicula> peliculas = peliculaServicio.mostrarPeliculas();
        model.addAttribute("peliculas", peliculas);
        return "peliculas.html";
    }

    @GetMapping("/personajes")
    public String personajes(ModelMap model) {
        List<Personaje> personajes = personajeServicio.mostrarPersonajes();
        model.addAttribute("personajes", personajes);
        return "personajes.html";
    }

//----------------------MODIFICAR/ELIMANAR---------------------
    @PostMapping("/modificarPelicula/{id}")
    public String modificarPelicula(ModelMap model) {
        
    }

    @PostMapping("/modificarPersonaje/{id}")
    public String modificarPersonaje(ModelMap model) {

    }
}
