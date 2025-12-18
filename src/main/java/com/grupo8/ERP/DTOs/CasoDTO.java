/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author fidel
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CasoDTO {
    private String demandante;
    private String meteria;
    private String tipoProceso;
    private String lugarAsignado;
}