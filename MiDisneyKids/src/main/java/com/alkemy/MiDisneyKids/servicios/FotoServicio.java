
package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.repositorios.FotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepo; 
}
