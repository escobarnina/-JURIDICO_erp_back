/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.resolver;

import com.grupo8.ERP.DTOs.AbogadoDTO;
import com.grupo8.ERP.model.Abogado;
import com.grupo8.ERP.service.AbogadoService;
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
public class AbogadoResolver {

    private final AbogadoService abogadoService;

    public AbogadoResolver(AbogadoService abogadoService) {
        this.abogadoService = abogadoService;
    }

    @QueryMapping
    public List<Abogado> allAbogados() {
        return abogadoService.getAllAbogados();
    }

    @QueryMapping
    public Abogado abogadoPorId(@Argument Long id) {
        return abogadoService.getAbogadoById(id).orElse(null);
    }

    @MutationMapping
    public Abogado crearAbogado(
        @Argument String nombre,
        @Argument String apellido,
        @Argument String ci,
        @Argument String telefono,
        @Argument String correo,
        @Argument String cuentaETH,
        @Argument String registroAbogado
    ) {
        Abogado abogado = new Abogado();
        abogado.setNombre(nombre);
        abogado.setApellido(apellido);
        abogado.setCi(ci);
        abogado.setTelefono(telefono);
        abogado.setCorreo(correo);
        abogado.setCuentaETH(cuentaETH);
        abogado.setRegistroAbogado(registroAbogado);
        return abogadoService.createAbogado(abogado);
    }

}
