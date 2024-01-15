package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.persistence.entity.*;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionService {

    private final EncryptionServiceBackend encBackend;
    private  final EncryptionServiceFrontend encFrontend;

    public EncryptionService(EncryptionServiceBackend encBackend, EncryptionServiceFrontend encFrontend) {
        this.encBackend = encBackend;
        this.encFrontend = encFrontend;
    }
    public static void main (String [] args){
        Paciente p=new Paciente();
        p.setCedula("1193098419");
        p.setNombre("Leider");
        p.setPeso("80");
        p.setRh("+");

        Cuidador c=new Cuidador();
        c.setCedulaCuidador("101056652");
        c.setTelefono("26526");
        c.setNombre("matilde la negra esclava");

        CuidadorPaciente cp=new CuidadorPaciente();
        cp.setPaciente(p);
        cp.setCuidador(c);
        cp.setIdCuidadorPaciente(1);

        EncryptionServiceBackend backend=new EncryptionServiceBackend();
        CuidadorPaciente aenc=backend.getCuidadorPaciente().encriptar(cp);
        System.out.println("Cuidador_paciente encriptado");
        System.out.println(aenc);
        CuidadorPaciente ades=backend.getCuidadorPaciente().desencriptar(aenc);
        System.out.println("cuidador_paciente desencriptado");
        System.out.println(ades);

    }
}
