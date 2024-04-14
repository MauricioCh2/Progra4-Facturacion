package com.una.ac.cr.facturaelectronica.data;
import com.una.ac.cr.facturaelectronica.logic.ProductoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<ProductoEntity,Integer> {
    Iterable<ProductoEntity> findAllByUsuarioId(String id);
    ProductoEntity findByCodigoAndUsuarioId(String codigo, String id);
}