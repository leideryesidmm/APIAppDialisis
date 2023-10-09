package com.Dialisis.DialisisPeritoneal.persistence.repository;


import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findAllBycedula(String cedula);
    @Modifying
    @Query(value = "Update usuario set contrasenia=:contrasenia where cedula=:cedula", nativeQuery = true)
    public void cambiarcontraseña(@Param("cedula") String cedula,
                                  @Param("contrasenia") String contrasenia);

    @Modifying
    @Query(value = "Update usuario set contrasenia=:contrasenia where cedula=:cedula", nativeQuery = true)
    public void cambiocontraseñaPrimeraVez(@Param("cedula") String cedula,
                                  @Param("contrasenia") String contrasenia);

    @Modifying
    @Query(value = "Update paciente set cambio_contrasenia=true where cedula=:cedula", nativeQuery = true)
    public void marcarCambiada(@Param("cedula") String cedula);


    @Modifying
    @Query(value = "Update usuario set celular=:celular where cedula=:cedula", nativeQuery = true)
    public void cambiarCelular(@Param("cedula") String cedula,
                               @Param("celular") String celular);


    public Usuario findBytipoUsuario(String tipo_usuario);


    @Modifying
    @Query(value = "Update usuario set activo=false where cedula=:cedula", nativeQuery = true)
    public void inactivarUsuario(@Param("cedula") String cedula);


    @Modifying
    @Query(value = "Update usuario set activo=true where cedula=:cedula", nativeQuery = true)
    public void activarUsuario(@Param("cedula") String cedula);

    @Modifying
    @Query(value = "Update usuario set contrasenia=:cedula where cedula=:cedula", nativeQuery = true)
    public void restaurarContrasenia(@Param("cedula") String cedula);

}
