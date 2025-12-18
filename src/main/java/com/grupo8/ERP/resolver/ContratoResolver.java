/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.resolver;

import com.grupo8.ERP.DTOs.ContratoDTO;
import com.grupo8.ERP.model.Contrato;
import com.grupo8.ERP.repository.ClienteRepository;
import com.grupo8.ERP.service.ContratoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fidel
 */
@Controller
public class ContratoResolver {

    @Autowired
    private ContratoService contratoService;
    
    @Autowired
    private ClienteRepository clienteRep;

    @QueryMapping
    public List<Contrato> allContratos() {
        List<Contrato> consulta = contratoService.getAllContratos();
        System.out.println(consulta);
        return consulta;
    }
   
    @QueryMapping
    public Contrato contratoById(@Argument Long id) {
        return contratoService.getContratoById(id);
    }

    @MutationMapping
    public Contrato crearContrato(
            @Argument String detalles ,
            @Argument String hashAddress ,
            @Argument String precioETH ,
            @Argument Double precioBS ,
            @Argument String fecha ,
            @Argument Long clienteId ,
            @Argument Long abogadoId ,
            @Argument Long casoId) {
        
        ContratoDTO contratoDTO = new ContratoDTO();
            
        contratoDTO.setDetalles(detalles);
        contratoDTO.setHashAddress(hashAddress);
        contratoDTO.setPrecioETH(precioETH);
        contratoDTO.setPrecioBS(precioBS);
        contratoDTO.setFecha(fecha);
        contratoDTO.setAbogado(abogadoId);
        contratoDTO.setCliente(clienteId);
        contratoDTO.setCaso(casoId);
        
        return contratoService.crearContrato(contratoDTO);
    }
}
