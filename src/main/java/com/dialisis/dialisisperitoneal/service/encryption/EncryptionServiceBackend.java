package com.dialisis.dialisisperitoneal.service.encryption;

import com.dialisis.dialisisperitoneal.service.encryption.encryptionServicesBackend.EncryptServiceAdminBack;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionServiceBackend {

    private final EncryptServiceAdminBack admin;

    public EncryptionServiceBackend(EncryptServiceAdminBack admin) {
        this.admin = admin;
    }
}
