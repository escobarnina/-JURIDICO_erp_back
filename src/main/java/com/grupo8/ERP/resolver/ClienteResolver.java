/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.resolver;

import com.grupo8.ERP.DTOs.ClienteDTO;
import com.grupo8.ERP.model.Cliente;
import com.grupo8.ERP.service.ClienteService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fidel
 */
@Controller
public class ClienteResolver {

    private final ClienteService clienteService;

    public ClienteResolver(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @QueryMapping
    public List<Cliente> allClientes() {
        return clienteService.getAllClientes();
    }

    @QueryMapping
    public Cliente clientePorId(@Argument Long id) {
        return clienteService.getClienteById(id).orElse(null);
    }

    @MutationMapping
    public Cliente crearCliente(
            @Argument String nombre, 
            @Argument String apellido, 
            @Argument String ci, 
            @Argument String telefono, 
            @Argument String correo, 
            @Argument String fechaNac, 
            @Argument String cuentaETH) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCi(ci);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setFechaNac(fechaNac);
        cliente.setCuentaETH(cuentaETH);
        return clienteService.createCliente(cliente);
    }
}
