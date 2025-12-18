/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fidel
 */
@Setter
@Getter
@Entity
@Table(name = "casos")
public class Caso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "demandante")
    private String demandante;
    
    @Column(name = "materia")
    private String meteria;
    
    @Column(name = "tipo_proceso")
    private String tipoProceso;
    
    @Column(name = "lugar_asignado")
    private String lugarAsignado;
}
