/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.service;

import com.grupo8.ERP.model.Abogado;
import com.grupo8.ERP.repository.AbogadoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fidel
 */

@Service
public class AbogadoService {

    @Autowired
    private AbogadoRepository abogadoRepository;

    public List<Abogado> getAllAbogados() {
        return abogadoRepository.findAll();
    }

    public Optional<Abogado> getAbogadoById(Long id) {
        return abogadoRepository.findById(id);
    }

    public Abogado createAbogado(Abogado abogado) {
        Abogado saved = abogadoRepository.save(abogado);
        return saved;
    }

}
