/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo8.ERP.repository;

import com.grupo8.ERP.model.Contrato;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fidel
 */
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    @Query("SELECT c FROM Contrato c WHERE c.cliente.id = :clienteId")
    List<Contrato> findByClienteId(@Param("clienteId") Long clienteId);
    
    @Query("SELECT c FROM Contrato c WHERE c.abogado.id = :abogadoId")
    List<Contrato> findByAbogadoId(@Param("abogadoId") Long abogadoId);
    
}