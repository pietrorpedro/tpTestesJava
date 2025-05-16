package org.example.services;

import org.example.interfaces.AutorizadorReembolso;
import org.example.interfaces.PlanoDeSaudeInterface;
import org.example.models.Consulta;
import org.example.models.Paciente;

public class CalculadoraReembolso {

    private static final double TETO_REEMBOLSO = 150.0;

    public double calcularReembolso(
            Paciente paciente,
            double valor,
            double percentual
    ) {
        double reembolso = valor * percentual;
        return Math.min(reembolso, TETO_REEMBOLSO);
    }

    public double calcularReembolsoComPlano(
            Paciente paciente,
            double valor,
            PlanoDeSaudeInterface plano
    ) {
        double percentual = plano.getPercentualDeCobertura();
        double reembolso = valor * percentual;
        return Math.min(reembolso, TETO_REEMBOLSO);
    }

    public double calcularReembolsoSeAutorizado(
            Consulta consulta,
            PlanoDeSaudeInterface plano,
            AutorizadorReembolso autorizador
    ) {
        if (!autorizador.foiAutorizado(consulta)) {
            throw new IllegalArgumentException("Consulta não autorizada");
        }

        double percentual = plano.getPercentualDeCobertura();
        double reembolso = consulta.getValor() * percentual;
        return Math.min(reembolso, TETO_REEMBOLSO);
    }
}
