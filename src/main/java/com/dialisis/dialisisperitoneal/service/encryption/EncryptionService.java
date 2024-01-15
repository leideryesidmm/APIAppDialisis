package com.dialisis.dialisisperitoneal.service.encryption;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EncryptionService {

    private final EncryptionServiceBackend encBackend;
    private  final  EncryptionServiceFrontend encFrontend;

    public EncryptionService(EncryptionServiceBackend encBackend, EncryptionServiceFrontend encFrontend) {
        this.encBackend = encBackend;
        this.encFrontend = encFrontend;
    }

}
