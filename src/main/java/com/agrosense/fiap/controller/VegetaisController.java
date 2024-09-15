package com.agrosense.fiap.controller;

import com.agrosense.fiap.model.VegetaisModel;
import com.agrosense.fiap.service.VegetaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VegetaisController {
    @Autowired
    private VegetaisService vegetaisService;

    @GetMapping("/images_page/{idCliente}")
    public String getAllVegetais(@PathVariable Long idCliente, Model model) {
        List<VegetaisModel> vegetais = vegetaisService.getVegetaisByClienteId(idCliente);
        model.addAttribute("vegetais", vegetais);
        model.addAttribute("idCliente", idCliente);
        return "images_page";
    }

    @GetMapping("/images_page/add")
    public String addVegetaisForm(@RequestParam(required = false) Long idCliente, Model model) {
        if (idCliente == null) {
            model.addAttribute("errorMessage", "O parâmetro idCliente é necessário para adicionar um novo vegetal.");
            return "error_page"; // Ou uma página de erro apropriada
        }
        model.addAttribute("idCliente", idCliente);
        return "images_add";
    }


    @PostMapping("/images_page/add")
    public String addVegetais(@RequestParam String nomeVegetais, @RequestParam Character statusVegetal,
                              @RequestParam String linkImagem, @RequestParam Long idCliente, Model model) {
        if (linkImagem.length() > 200) {
            model.addAttribute("errorMessage", "O link da imagem é muito longo. Por favor, insira um link com no máximo 200 caracteres.");
            model.addAttribute("idCliente", idCliente);
            return "images_add";
        }

        VegetaisModel newVegetal = new VegetaisModel();
        newVegetal.setNomeVegetais(nomeVegetais);
        newVegetal.setStatusVegetal(statusVegetal);
        newVegetal.setLinkImagem(linkImagem);
        newVegetal.setIdCliente(idCliente);
        vegetaisService.createVegetais(newVegetal);
        return "redirect:/images_page/" + idCliente;
    }

    @GetMapping("/images_page/edit/{id}")
    public String editVegetaisForm(@PathVariable Long id, Model model) {
        VegetaisModel vegetais = vegetaisService.getVegetaisById(id)
            .orElseThrow(() -> new RuntimeException("Vegetal não encontrado"));
        model.addAttribute("vegetais", vegetais);
        return "images_edit";
    }

    @PostMapping("/images_page/edit")
    public String updateVegetais(@ModelAttribute VegetaisModel vegetais) {
        vegetaisService.updateVegetais(vegetais.getIdVegetais(), vegetais);
        return "redirect:/images_page/" + vegetais.getIdCliente();
    }

    @PostMapping("/images_page/delete/{id}")
    public String deleteVegetais(@PathVariable Long id) {
        VegetaisModel vegetal = vegetaisService.getVegetaisById(id).orElseThrow(() -> new RuntimeException("Vegetal not found"));
        vegetaisService.deleteVegetais(id);
        return "redirect:/images_page/" + vegetal.getIdCliente();
    }
}

