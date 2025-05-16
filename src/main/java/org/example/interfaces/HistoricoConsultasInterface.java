package org.example.interfaces;

import org.example.models.Consulta;
import org.example.models.Paciente;

import java.util.List;

public interface HistoricoConsultasInterface {

    void registrarConsulta(Consulta consulta);

    List<Consulta> getConsultaPorPaciente(Paciente paciente);
}
