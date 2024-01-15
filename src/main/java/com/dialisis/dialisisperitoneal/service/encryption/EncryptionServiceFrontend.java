package com.dialisis.dialisisperitoneal.service.encryption;
import com.dialisis.dialisisperitoneal.service.encryption.encryptionServicesFrontend.EncryptServiceAdminFront;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionServiceFrontend {

private final EncryptServiceAdminFront admin;

    public EncryptionServiceFrontend(EncryptServiceAdminFront admin) {
        this.admin = admin;
    }
}
