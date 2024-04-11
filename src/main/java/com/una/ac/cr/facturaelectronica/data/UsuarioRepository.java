package com.una.ac.cr.facturaelectronica.data;

import com.una.ac.cr.facturaelectronica.logic.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {


    Optional<UsuarioEntity> findByContrasenaAndIdUsuario(String contrasena, String usuario);
    UsuarioEntity findByIdUsuario(String idUsuario);

    List<UsuarioEntity> findByRol(String rol);

}
