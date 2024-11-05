package com.agrosense.fiap.controller;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.service.UsuarioService;
import com.agrosense.fiap.service.VegetaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private VegetaisService vegetaisService;

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        long userCount = userService.getUserCount();
        long vegetaisCount = vegetaisService.getAllVegetais().size();
        List<UsuarioModel> usuarios = userService.getAllUsuarios();

        model.addAttribute("userCount", userCount);
        model.addAttribute("vegetaisCount", vegetaisCount);
        model.addAttribute("usuarios", usuarios);
        
        return "admin_dashboard";
    }

    @PostMapping("/assign_role")
    public String assignRole(Long userId, String roleName) {
        userService.getUsuarioById(userId).ifPresent(usuario -> {
            usuario.setRoles(roleName);
            userService.updateUsuario(userId, usuario);
        });
        return "redirect:/admin_dashboard"; // Redireciona para o dashboard após atribuir o papel
    }
}
