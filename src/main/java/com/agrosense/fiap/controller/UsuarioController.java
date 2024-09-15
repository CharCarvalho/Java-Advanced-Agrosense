package com.agrosense.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.service.UsuarioService;

@Controller
@RequestMapping
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/user_edit")
    public String getUserEditPage(@RequestParam("idCliente") Long idCliente, Model model) {
        UsuarioModel usuario = usuarioService.getUsuarioById(idCliente)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        model.addAttribute("usuario", usuario);
        return "user_edit";
    }

    @PostMapping("/user_edit")
    public String handleUserEdit(@ModelAttribute UsuarioModel usuario, Model model) {
        try {
            usuarioService.updateUsuario(usuario.getId_cliente(), usuario);
            return "redirect:/images_page/" + usuario.getId_cliente();
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user_edit";
        }
    }
}
