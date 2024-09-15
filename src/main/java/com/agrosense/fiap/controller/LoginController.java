package com.agrosense.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.service.UsuarioService;

@Controller
@RequestMapping
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "home_page";
    }

    @GetMapping("/login_page")
    public String getLoginPage() {
        return "login_page";
    }

    @PostMapping("/login_page/login")
    public ModelAndView handleLogin(@RequestParam String email, @RequestParam String senha) {
        UsuarioModel usuario = usuarioService.getAllUsuarios().stream()
            .filter(u -> email.equals(u.getNmEmail()))
            .findFirst()
            .orElse(null);

        if (usuario == null) {
            return new ModelAndView("redirect:/login_page?error=Email nao encontrado");
        }

        // Verifique a senha
        if (!senha.equals(usuario.getNm_senha())) {
            return new ModelAndView("redirect:/login_page?error=Senha incorreta");
        }

        // Redireciona para a página de imagens com o id_cliente
        return new ModelAndView("redirect:/images_page/" + usuario.getId_cliente());
    }
}
