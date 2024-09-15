package com.agrosense.fiap.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.service.UsuarioService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping
public class RegisterController {

    private final UsuarioService usuarioService;

    public RegisterController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/register_page")
    public String getRegisterPage(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "register_page";
    }

    @PostMapping("/register_page")
    public String handleRegister(@ModelAttribute UsuarioModel usuario, Model model) {
    	System.out.println("Usuario Email "+ usuario.getNmEmail());
        try {
            // Defina a data de cadastro automaticamente
            usuario.setDt_cadastro(LocalDate.now());
            usuarioService.createUsuario(usuario);            
            return "redirect:/login_page";
        } catch (Exception e) {
            // Exibe a mensagem de erro
            model.addAttribute("errorMessage", e.getMessage());
            // Mantém os dados preenchidos
            model.addAttribute("usuario", usuario);
            return "register_page";
        }
    }


}
