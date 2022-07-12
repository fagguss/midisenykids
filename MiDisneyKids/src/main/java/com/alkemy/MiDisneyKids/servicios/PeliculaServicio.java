
package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.repositorios.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
}
