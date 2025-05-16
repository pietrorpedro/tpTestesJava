package org.example.services;

import org.example.interfaces.HistoricoConsultasInterface;
import org.example.models.Consulta;
import org.example.models.Paciente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricoConsultas implements HistoricoConsultasInterface {

    private List<Consulta> consultas = new ArrayList<Consulta>();

    @Override
    public void registrarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    @Override
    public List<Consulta> getConsultaPorPaciente(Paciente paciente) {
        // separa, filtra e retorna as consultas que o paciente é igual ao paciente parâmetro
        return consultas.stream().filter(c -> c.getPaciente().equals(paciente)).collect(Collectors.toList());
    }
}
