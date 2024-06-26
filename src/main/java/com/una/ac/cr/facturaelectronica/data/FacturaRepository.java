package com.una.ac.cr.facturaelectronica.data;

import com.una.ac.cr.facturaelectronica.logic.FacturaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface FacturaRepository extends JpaRepository<FacturaEntity,Integer> {


//    FacturaEntity findByUsuarioId(String idUsuario);
    Iterable<FacturaEntity> findAllByProveedor(String idproveedor);
}