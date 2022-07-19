
package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.repositorios.FotoRepositorio;
import com.alkemy.MiDisneyKids.entidades.Foto;
import com.alkemy.MiDisneyKids.errores.ErrorServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    
    @Autowired
    private FotoRepositorio fotoRepo; 
    
    public Foto guardar(MultipartFile archivo) throws IOException, ErrorServicio{
        if (archivo!=null){
            Foto foto=new Foto(); 
            
            foto.setMime(archivo.getContentType()); 
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes()); 
            
            return fotoRepo.save(foto); 
        }else{
            throw new ErrorServicio("No se pudo guardar la foto");
        }
            
    }
    
    public Foto actualizar(String idFoto, MultipartFile archivo) throws Exception{
       if (archivo != null) {
            try {
                Foto foto = new Foto();

                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepo.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }

                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepo.save(foto);
            } catch (Exception e) {
                throw new Exception(".");
            }
        }
        return null;
    }
}
