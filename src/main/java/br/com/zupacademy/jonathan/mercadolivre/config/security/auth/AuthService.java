package br.com.zupacademy.jonathan.mercadolivre.config.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.jonathan.mercadolivre.usuario.Usuario;
import br.com.zupacademy.jonathan.mercadolivre.usuario.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if(usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Dados inválidos");
    }
}