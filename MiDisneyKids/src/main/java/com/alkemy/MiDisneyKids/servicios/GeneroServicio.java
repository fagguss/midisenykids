
package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.repositorios.GeneroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicio {
    
    @Autowired
    private GeneroRepositorio generoRepo; 
}
