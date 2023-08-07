package com.Dialisis.DialisisPeritoneal.persistence.repository;


import com.Dialisis.DialisisPeritoneal.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findAllBycedula(long cedula);
    @Modifying
    @Query(value = "Update usuario set contrasenia=:contrasenia where cedula=:cedula", nativeQuery = true)
    public void cambiarcontraseña(@Param("cedula") String cedula,
                                  @Param("contrasenia") String contrasenia);
    @Modifying
    @Query(value = "Update usuario set celular=:celular where cedula=:cedula", nativeQuery = true)
    public void cambiarCelular(@Param("cedula") long cedula,
                               @Param("celular") long celular);

}
