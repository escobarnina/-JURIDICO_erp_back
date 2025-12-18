/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo8.ERP.resolver;

import com.grupo8.ERP.DTOs.CasoDTO;
import com.grupo8.ERP.model.Caso;
import com.grupo8.ERP.service.CasoService;
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
public class CasoResolver {

    private final CasoService casoService;

    public CasoResolver(CasoService casoService) {
        this.casoService = casoService;
    }

    @QueryMapping
    public List<Caso> allCasos() {
        List<Caso> casos = casoService.getAll();
        return casos;
    }

    @QueryMapping
    public Caso casoById(@Argument Long id) {
        Caso caso = casoService.getById(id);
        return caso;
    }

    @MutationMapping
    public Caso crearCaso(@Argument String demandante,
            @Argument String meteria,
            @Argument String tipoProceso,
            @Argument String lugarAsignado) {
        CasoDTO casoDTO = new CasoDTO(demandante, meteria, tipoProceso, lugarAsignado);
        Caso casoCreado = casoService.create(casoDTO);
        return casoCreado;
    }
}
