package com.dialisis.dialisisperitoneal.service.dto.uniones;

import com.dialisis.dialisisperitoneal.persistence.entity.Cita;
import lombok.Data;

import java.util.List;

@Data
public class UnionCitaPrescripcionDias {
    private Cita cita;
    private List<UnionPrescripcionDiasRecambios> unionPrescripcionDiasRecambios;
}
