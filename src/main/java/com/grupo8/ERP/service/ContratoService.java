/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.service;

import com.grupo8.ERP.DTOs.ContratoDTO;
import com.grupo8.ERP.model.Abogado;
import com.grupo8.ERP.model.Caso;
import com.grupo8.ERP.model.Cliente;
import com.grupo8.ERP.model.Contrato;
import com.grupo8.ERP.repository.AbogadoRepository;
import com.grupo8.ERP.repository.CasoRepository;
import com.grupo8.ERP.repository.ClienteRepository;
import com.grupo8.ERP.repository.ContratoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fidel
 */
@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AbogadoRepository abogadoRepository;
    
    @Autowired
    private CasoRepository casoRepository;

    public List<Contrato> getAllContratos() {
        List<Contrato> contratos = contratoRepository.findAll();
        System.out.println("ASDASDNASONASDASOASDAODNADSAONDS");
        System.out.println(contratos.size());

        return contratos;
    }

    public Contrato getContratoById(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        return contrato;
    }

    public Contrato crearContrato(ContratoDTO contrato) {

        Optional<Cliente> cliente = clienteRepository.findById(contrato.getCliente());
        Optional<Abogado> abogado = abogadoRepository.findById(contrato.getAbogado());
        Optional<Caso> caso = casoRepository.findById(contrato.getCaso());
        

        System.out.println(cliente.get().getCorreo() + abogado.get().getCorreo());

        Contrato contratoModel = new Contrato();

        contratoModel.setAbogado(abogado.get());
        contratoModel.setCliente(cliente.get());
        contratoModel.setDetalles(contrato.getDetalles());
        contratoModel.setHashAddress(contrato.getHashAddress());
        contratoModel.setPrecioETH(contrato.getPrecioETH());
        contratoModel.setPrecioBS(contrato.getPrecioBS());
        contratoModel.setFecha(contrato.getFecha());
        contratoModel.setCaso(caso.get());

        contratoModel = contratoRepository.save(contratoModel);

        return contratoModel;
    }
}
