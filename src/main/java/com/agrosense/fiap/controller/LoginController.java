package com.agrosense.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.security.TokenService;
import com.agrosense.fiap.service.UsuarioService;

@Controller
@RequestMapping
public class LoginController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder; 
    
    @Autowired
    private TokenService tokenService;

    public LoginController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder; 
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
        System.out.println("email: " + email);
        System.out.println("senha: " + senha);
        UsuarioModel usuario = usuarioService.getAllUsuarios().stream()
            .filter(u -> email.equals(u.getNmEmail()))
            .findFirst()
            .orElse(null);

        if (usuario == null) {
            System.out.println("Email não encontrado");
            return new ModelAndView("redirect:/login_page?error=Email não encontrado");
        }

        if (!passwordEncoder.matches(senha, usuario.getNm_senha())) {
            System.out.println("Senha incorreta");
            return new ModelAndView("redirect:/login_page?error=Senha incorreta");
        }

        // Redireciona para a página de imagens
        return new ModelAndView("redirect:/images_page/" + usuario.getId());
    }


    @GetMapping("/api/token")
    public String getToken(@RequestParam String email, @RequestParam String senha) {
        UsuarioModel usuario = usuarioService.getAllUsuarios().stream()
            .filter(u -> email.equals(u.getNmEmail()))
            .findFirst()
            .orElse(null);

        if (usuario != null && passwordEncoder.matches(senha, usuario.getNm_senha())) {
            // Gerar o token JWT
            String token = tokenService.generateToken(usuario);
            return token; // Retorna o token como resposta
        }

        return null; // Retorna null se não conseguir autenticar
    }
}
