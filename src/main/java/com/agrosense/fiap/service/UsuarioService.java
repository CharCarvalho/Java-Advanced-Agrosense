package com.agrosense.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isAdmin(Long userId) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(userId);
        return usuario.isPresent() && "ADMIN".equalsIgnoreCase(usuario.get().getRoles());
    }

    public long getUserCount() {
        return usuarioRepository.count();
    }

    public boolean userHasRole(Long userId, String roleName) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(userId);
        return usuario.isPresent() && roleName.equalsIgnoreCase(usuario.get().getRoles());
    }

    public UsuarioModel createUsuario(UsuarioModel usuario) throws Exception {
        logger.info("Verificando se o email já existe: {}", usuario.getNmEmail());
        Optional<UsuarioModel> existingUsuario = usuarioRepository.findByNmEmail(usuario.getNmEmail());
        if (existingUsuario.isPresent()) {
            logger.error("Email já cadastrado: {}", usuario.getNmEmail());
            throw new Exception("Este email já está cadastrado. Por favor, insira um email diferente.");
        }
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> createUsuarios(List<UsuarioModel> usuarios) {
        return usuarioRepository.saveAll(usuarios);
    }

    public List<UsuarioModel> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> updateUsuario(Long id, UsuarioModel usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNm_cliente(usuarioDetails.getNm_cliente());
            usuario.setTp_cliente(usuarioDetails.getTp_cliente());
            usuario.setDt_cadastro(usuarioDetails.getDt_cadastro());
            usuario.setNmEmail(usuarioDetails.getNmEmail());
            usuario.setNm_senha(usuarioDetails.getNm_senha());
            usuario.setRoles(usuarioDetails.getRoles());  // Atualiza o role
            return usuarioRepository.save(usuario);
        });
    }

    public Optional<UsuarioModel> patchUsuario(Long id, UsuarioModel usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (usuarioDetails.getNm_cliente() != null) {
                usuario.setNm_cliente(usuarioDetails.getNm_cliente());
            }
            if (usuarioDetails.getTp_cliente() != null) {
                usuario.setTp_cliente(usuarioDetails.getTp_cliente());
            }
            if (usuarioDetails.getDt_cadastro() != null) {
                usuario.setDt_cadastro(usuarioDetails.getDt_cadastro());
            }
            if (usuarioDetails.getNmEmail() != null) {
                usuario.setNmEmail(usuarioDetails.getNmEmail());
            }
            if (usuarioDetails.getNm_senha() != null) {
                usuario.setNm_senha(usuarioDetails.getNm_senha());
            }
            if (usuarioDetails.getRoles() != null) {  // Atualiza o role
                usuario.setRoles(usuarioDetails.getRoles());
            }
            return usuarioRepository.save(usuario);
        });
    }

    public boolean deleteUsuario(Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }
}
