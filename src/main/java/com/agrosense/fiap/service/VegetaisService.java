package com.agrosense.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrosense.fiap.model.VegetaisModel;
import com.agrosense.fiap.repository.VegetaisRepository;

@Service
public class VegetaisService {

    @Autowired
    private VegetaisRepository vegetaisRepository;

    public VegetaisModel createVegetais(VegetaisModel vegetais) {
        return vegetaisRepository.save(vegetais);
    }
    
    public List<VegetaisModel> getVegetaisByClienteId(Long idCliente) {
        return vegetaisRepository.findByIdCliente(idCliente);
    }

    public List<VegetaisModel> createVegetais(List<VegetaisModel> vegetaisList){
        return vegetaisRepository.saveAll(vegetaisList);
    }

    public List<VegetaisModel> getAllVegetais(){
        return vegetaisRepository.findAll();
    }

    public Optional<VegetaisModel> getVegetaisById(Long id){
        return vegetaisRepository.findById(id);
    }

    public Optional<VegetaisModel> updateVegetais(Long id, VegetaisModel vegetaisDetails){
        return vegetaisRepository.findById(id).map(vegetais -> {
            vegetais.setIdCliente(vegetaisDetails.getIdCliente());
            vegetais.setNomeVegetais(vegetaisDetails.getNomeVegetais());
            vegetais.setStatusVegetal(vegetaisDetails.getStatusVegetal());
            vegetais.setLinkImagem(vegetaisDetails.getLinkImagem());
            vegetais.setDataImagem(vegetaisDetails.getDataImagem());
            return vegetaisRepository.save(vegetais);
        });
    }

    public Optional<VegetaisModel> patchVegetais(Long id, VegetaisModel vegetaisDetails){
        return vegetaisRepository.findById(id).map(vegetais -> {
            if (vegetaisDetails.getIdCliente() != null) {
                vegetais.setIdCliente(vegetaisDetails.getIdCliente());
            }
            if (vegetaisDetails.getNomeVegetais() != null) {
                vegetais.setNomeVegetais(vegetaisDetails.getNomeVegetais());
            }
            if (vegetaisDetails.getStatusVegetal() != null) {
                vegetais.setStatusVegetal(vegetaisDetails.getStatusVegetal());
            }
            if (vegetaisDetails.getLinkImagem() != null) {
                vegetais.setLinkImagem(vegetaisDetails.getLinkImagem());
            }
            if (vegetaisDetails.getDataImagem() != null) {
                vegetais.setDataImagem(vegetaisDetails.getDataImagem());
            }
            return vegetaisRepository.save(vegetais);
        });
    }

    public boolean deleteVegetais(Long id) {
        return vegetaisRepository.findById(id).map(vegetais -> {
            vegetaisRepository.delete(vegetais);
            return true;
        }).orElse(false);
    }
}
