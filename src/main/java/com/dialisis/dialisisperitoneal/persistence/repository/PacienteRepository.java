package com.dialisis.dialisisperitoneal.persistence.repository;

import com.dialisis.dialisisperitoneal.persistence.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, String> {

    public Paciente findByCedula(String cedula);

    @Query(value = "SELECT * from paciente where activo = true", nativeQuery = true)
    public List<Paciente> findPacientesActivos();

    @Query(value = "SELECT * from paciente where activo = false", nativeQuery = true)
    public List<Paciente> findPacientesInactivos(String cedula);
    @Query(value = "select p.cedula,p.altura,p.fecha_nacimiento,p.ocupacion,p.direccion as 'direccionPaciente',p.peso,p.peso_seco,p.tipo_sangre,p.rh,p.fecha_registro,u.celular as 'celularPaciente',u.nombre as 'nombrePaciente',u.correo as 'correoPaciente',u.tipo_documento,c.nombre as 'nombreCuidador',c.cedula_cuidador,c.telefono as 'telefonoCuidador',c.direccion as 'direccionCuidador', par.descripcion as 'parentesco' from usuario u RIGHT JOIN paciente p on p.cedula=u.cedula left join cuidador_paciente cp on cp.paciente=p.cedula and cp.activo=true left join cuidador c on cp.cuidador=c.cedula_cuidador left join parentesco par on par.id_parentesco=c.parentesco", nativeQuery = true)
    public List<Object[]> findAllPacientesDatos();

}


