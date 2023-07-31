package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.persistence.entity.Cita;
import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import lombok.Data;

import java.util.List;

@Data
public class UnionCitaPrescripcionDias {
    private Cita cita;
    private List<UnionPrescripcionDiasRecambios> unionPrescripcionDiasRecambios;
}
