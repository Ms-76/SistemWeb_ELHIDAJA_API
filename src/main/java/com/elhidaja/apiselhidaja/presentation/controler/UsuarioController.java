package com.elhidaja.apiselhidaja.presentation.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elhidaja.apiselhidaja.presentation.dto.usuario.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.usuario.Response.*;
import com.elhidaja.apiselhidaja.service.implementation.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/getall")
    public ResponseEntity<ResponseUsuarioAllDTO> getUsuarios(
            @Valid @RequestBody RequestUsuarioOptionDTO option) {
        return ResponseEntity.ok(usuarioService.getAllSer(option));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<ResponseDetalleUsuarioDTO> getByIdUsuario(
            @Valid @RequestBody RequestUsuarioIdDTO id) {
        return ResponseEntity.ok(usuarioService.getByIdSer(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseUsuarioMensajeDTO> insertarUsuario(
            @Valid @RequestBody RequestUsuarioInsertDTO dto) {
        return ResponseEntity.ok(usuarioService.insertSer(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUsuarioMensajeDTO> actualizarUsuario(
            @Valid @RequestBody RequestUsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuarioService.updateSer(dto));
    }

    @PutMapping("/activate")
    public ResponseEntity<ResponseUsuarioMensajeDTO> activarUsuario(
            @Valid @RequestBody RequestUsuarioIdDTO id) {
        return ResponseEntity.ok(usuarioService.activateSer(id));
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<ResponseUsuarioMensajeDTO> desactivarUsuario(
            @Valid @RequestBody RequestUsuarioIdDTO id) {
        return ResponseEntity.ok(usuarioService.desactivateSer(id));
    }
}
