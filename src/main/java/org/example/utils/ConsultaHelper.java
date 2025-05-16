package org.example.utils;

import org.example.models.Consulta;
import org.example.models.Paciente;

import java.time.LocalDate;

public class ConsultaHelper {

    public static Consulta criarConsultaPadrao() {
        Paciente paciente = new Paciente("Fulano");
        double valor = 100.0;
        LocalDate data = LocalDate.now();

        return new Consulta(paciente, data, valor);
    }

    public static Consulta criarConsulta(String nome, double valor) {
        Paciente paciente = new Paciente(nome);
        LocalDate data = LocalDate.now();
        return new Consulta(paciente, data, valor);
    }

}
