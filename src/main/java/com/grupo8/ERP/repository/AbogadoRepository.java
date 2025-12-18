/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grupo8.ERP.repository;

import com.grupo8.ERP.model.Abogado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fidel
 */
public interface AbogadoRepository extends JpaRepository<Abogado, Long> {
    Optional<Abogado> findById(Long id);
}
