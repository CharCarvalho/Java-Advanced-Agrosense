	package com.agrosense.fiap.controller;
	
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.service.UsuarioService;
import com.agrosense.fiap.service.VegetaisService;
	
	@Controller
	@RequestMapping
	public class UsuarioController {

	    private final UsuarioService usuarioService;
	    private final VegetaisService vegetaisService;

	    public UsuarioController(UsuarioService usuarioService, VegetaisService vegetaisService) {
	        this.usuarioService = usuarioService;
	        this.vegetaisService = vegetaisService;
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

	    @PostMapping("/user_delete/{id_cliente}")
	    public String deleteUsuario(@PathVariable Long id_cliente) {
	    	System.out.println("Id_cliente" + id_cliente);
	    	vegetaisService.deleteAllVegetaisByClienteId(id_cliente);
	        usuarioService.deleteUsuario(id_cliente);
	        return "redirect:/login_page";
	    }
	}
