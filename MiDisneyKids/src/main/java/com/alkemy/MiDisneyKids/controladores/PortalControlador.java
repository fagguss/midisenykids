
package com.alkemy.MiDisneyKids.controladores;

import com.alkemy.MiDisneyKids.servicios.GeneroServicio;
import com.alkemy.MiDisneyKids.servicios.PeliculaServicio;
import com.alkemy.MiDisneyKids.servicios.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PortalControlador {
    
    @Autowired
    private PersonajeServicio personajeServicio; 

    @Autowired
    private PeliculaServicio peliculaServicio; 

    @Autowired
    private GeneroServicio generoServicio;
    

}
