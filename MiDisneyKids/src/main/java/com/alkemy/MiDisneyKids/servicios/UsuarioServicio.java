package com.alkemy.MiDisneyKids.servicios;

import com.alkemy.MiDisneyKids.errores.ErrorServicio;
import com.alkemy.MiDisneyKids.enumeraciones.EnumRol;
import com.alkemy.MiDisneyKids.entidades.Usuario;
import com.alkemy.MiDisneyKids.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public Usuario crear(String nombre, String clave, String email) throws ErrorServicio, Exception {

        validar(nombre, email, clave);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setAlta(Boolean.TRUE);
        usuario.setRol(EnumRol.USUARIO);

        String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(claveEncriptada);

        return usuarioRepositorio.save(usuario);

//        notificacionServicio.enviar("Bienvenido a BoxReview", "Te has registrado con éxito a BoxReview!!", usuario.getEmail());
    }
    
    

    public void iniciarSesion(String nombre, String clave) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        Optional<Usuario> respuesta = usuarioRepositorio.buscarPorNombre(nombre);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
        } else {
            throw new ErrorServicio("No se encontro el usuario deseado");
        }
    }
    
    @Transactional
    public void modificar(String idUsuario, String nombre, String email, 
            String clave) throws ErrorServicio, Exception {

        validar(nombre, email, clave);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setEmail(email);

            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(claveEncriptada);

            usuarioRepositorio.save(usuario);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuarioSession", usuario);
        } else {
            throw new ErrorServicio("No se encontro el usuario deseado");
        }
    }
 
//-----------------------------ROLES------------------------
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuarioSession", usuario);

            User user = new User(usuario.getEmail(), usuario.getClave(), permisos);
            
            return user;
        } else {
            throw new UsernameNotFoundException("El rol de usuario no ha sido implementado correctamente");
        }
    }

//------------------------VALIDACIONES-------------------------------------
    private void validar(String nombre, String email, String clave) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El email no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La contraseña no debe estar vacia y debe tener mas de 6 caracteres");
        }
    }
}
