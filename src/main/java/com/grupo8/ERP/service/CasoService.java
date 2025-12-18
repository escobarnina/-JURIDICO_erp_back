/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.service;

import com.grupo8.ERP.DTOs.CasoDTO;
import com.grupo8.ERP.model.Caso;
import com.grupo8.ERP.repository.CasoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fidel
 */
@Service
public class CasoService {

    @Autowired
    private CasoRepository casoRepository;

    public List<Caso> getAll() {
        return casoRepository.findAll();
    }

    public Caso getById(Long id) {
        Caso caso = casoRepository.findById(id).orElse(null);
        if (caso == null) {
            return null;
        }
        return caso;
    }

    public Caso create(CasoDTO caso) {
        Caso casoM = new Caso();
        casoM.setDemandante(caso.getDemandante());
        casoM.setLugarAsignado(caso.getLugarAsignado());
        casoM.setMeteria(caso.getMeteria());
        casoM.setTipoProceso(caso.getTipoProceso());
        
        Caso casoNuevo = casoRepository.save(casoM);
        return casoNuevo;
    }

}
