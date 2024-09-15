package com.agrosense.fiap.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tg_vegetais")
public class VegetaisModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vegetais")
    private Long idVegetais;
    
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
    
    @Column(name = "nm_vegetais", nullable = false)
    private String nomeVegetais;
    
    @Column(name = "st_vegetal", nullable = false)
    private Character statusVegetal;
    
    @Column(name = "lk_imagem")
    private String linkImagem;
    
    @Column(name = "dt_imagem")
    private LocalDate dataImagem;
}
