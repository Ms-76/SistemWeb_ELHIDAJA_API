package com.elhidaja.apiselhidaja.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.elhidaja.apiselhidaja.persistence.repository.UsuarioRepository;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;

@Service
@Validated
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Transactional
    public ResponseUsuarioMensajeDTO insertSer(RequestUsuarioInsertDTO objUsuario) {
        return usuarioRepo.insertD(objUsuario);
    }

    @Transactional
    public ResponseUsuarioMensajeDTO updateSer(RequestUsuarioUpdateDTO objUsuario) {
        return usuarioRepo.updateD(objUsuario);
    }

    @Transactional
    public ResponseUsuarioAllDTO getAllSer(RequestUsuarioOptionDTO option) {
        return usuarioRepo.getAllD(option);
    }

    @Transactional
    public ResponseDetalleUsuarioDTO getByIdSer(RequestUsuarioFilterDTO id) {
        return usuarioRepo.getByIdD(id);
    }

    @Transactional
    public ResponseUsuarioMensajeDTO activateSer(RequestUsuarioIdDTO id) {
        return usuarioRepo.activateD(id);
    }

    @Transactional
    public ResponseUsuarioMensajeDTO desactivateSer(RequestUsuarioIdDTO id) {
        return usuarioRepo.desactivateD(id);
    }
}
