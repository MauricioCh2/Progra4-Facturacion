package com.una.ac.cr.facturaelectronica.data;

import com.una.ac.cr.facturaelectronica.logic.ClienteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<ClienteEntity,String> {
    Iterable<ClienteEntity> findAllByUsuarioId(String id);
}