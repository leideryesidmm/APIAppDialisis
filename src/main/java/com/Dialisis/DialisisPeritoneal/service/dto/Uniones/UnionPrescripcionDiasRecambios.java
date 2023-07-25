package com.Dialisis.DialisisPeritoneal.service.dto.Uniones;

import com.Dialisis.DialisisPeritoneal.persistence.entity.PrescripcionDia;
import com.Dialisis.DialisisPeritoneal.persistence.entity.Recambio;
import lombok.Data;

import java.util.List;

@Data
public class UnionPrescripcionDiasRecambios {
    private PrescripcionDia prescripcionDia;
    private List<Recambio> recambios;
}
