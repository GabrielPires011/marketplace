package com.br.marketplace.service;

import com.br.marketplace.dto.dados.detalhados.UsuarioDetalhadosAutenticacaoDto;
import com.br.marketplace.dto.salvar.SalvarUsuarioDto;
import com.br.marketplace.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public void salvar(SalvarUsuarioDto dto) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repository.findByEmail(username).orElse(null);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UsuarioDetalhadosAutenticacaoDto(usuario);
    }
}
