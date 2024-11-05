package com.agrosense.fiap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tg_clientes")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    @Column
    private String nm_cliente;

    @Column
    private String tp_cliente;

    @Column
    private LocalDate dt_cadastro;

    @Column(name = "nm_email", unique = true)
    private String nmEmail;

    @Column
    private String nm_senha;

    @Column
    private String roles;
}
