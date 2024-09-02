package com.br.marketplace.service;

import com.br.marketplace.dto.UsuarioDetalhadosAutenticacaoDto;
import com.br.marketplace.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDetalhadoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = repository.findByEmail(username);
        return userInfo.map(UsuarioDetalhadosAutenticacaoDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
