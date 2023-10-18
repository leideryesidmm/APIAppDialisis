package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.persistence.entity.PrescripcionDia;
import com.dialisis.dialisisperitoneal.persistence.entity.Recambio;
import lombok.Data;

import java.util.List;

@Data
public class UnionPrescripcionDiasRecambios {
    private PrescripcionDia prescripcionDia;
    private List<Recambio> recambios;
}
