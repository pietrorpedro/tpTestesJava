package org.example;

import org.example.interfaces.AutorizadorReembolso;
import org.example.interfaces.PlanoDeSaudeInterface;
import org.example.models.Consulta;
import org.example.models.Paciente;
import org.example.services.CalculadoraReembolso;
import org.example.utils.ConsultaHelper;
import org.example.utils.MargemHelper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReembolsoTest {

    Paciente dummy = new Paciente("Fulano Souza");

    // 1
    // usando o helper (9)
    @Test
    public void deveCalcularReembolsoComBaseEmValorFixo() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();
        Paciente dummy = ConsultaHelper.criarConsultaPadrao().getPaciente();
        double valor = 200.0;
        double percentual = 0.7;

        double reembolsado = reembolso.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(140.0, reembolsado));
    }

    // 2
    @Test
    public void deveRetornarZeroSeValorForZero() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();
        Paciente dummy = ConsultaHelper.criarConsultaPadrao().getPaciente();
        double valor = 0.0;
        double percentual = 0.5;

        double reembolsado = reembolso.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(0, reembolsado));
    }

    @Test
    public void deveRetornarZeroSePercentualForZero() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();
        Paciente dummy = ConsultaHelper.criarConsultaPadrao().getPaciente();
        double valor = 200.0;
        double percentual = 0.0;

        double reembolsado = reembolso.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(0, reembolsado));
    }

    @Test
    public void deveRetornarZeroSeValorEPercentualForZero() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();
        double valor = 0.0;
        double percentual = 0.0;

        double reembolsado = reembolso.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(0, reembolsado));
    }

    @Test
    public void deveRetornarCompletoSePercentualForCem() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();
        double valor = 200.0;
        double percentual = 1;

        double reembolsado = reembolso.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(150.0, reembolsado));
    }

    // 6
    @Test
    public void deveCalcularReembolsoComPlano() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();

        PlanoDeSaudeInterface plano = new PlanoDeSaudeInterface() {
            @Override
            public double getPercentualDeCobertura() {
                return 0.5;
            }
        };

        double valorConsulta = 200.0;

        double reembolsado = reembolso.calcularReembolsoComPlano(dummy, valorConsulta, plano);

        assertTrue(MargemHelper.comparar(100.0, reembolsado));
    }

    @Test
    public void deveCalcularReembolsoComPlano2() {
        CalculadoraReembolso reembolso = new CalculadoraReembolso();

        PlanoDeSaudeInterface plano = new PlanoDeSaudeInterface() {
            @Override
            public double getPercentualDeCobertura() {
                return 0.8;
            }
        };

        double valorConsulta = 200.0;

        double reembolsado = reembolso.calcularReembolsoComPlano(dummy, valorConsulta, plano);

        assertTrue(MargemHelper.comparar(150.0, reembolsado));
    }

    // 8
    @Test
    public void deveCalcularReembolsoQuandoConsultaForAutorizada() {
        CalculadoraReembolso calculadora =  new CalculadoraReembolso();
        Paciente paciente = new Paciente("Jorge");
        Consulta consulta = new Consulta(paciente, LocalDate.now(), 100.0);

        // em vez de instanciar a interface
        PlanoDeSaudeInterface plano = () -> 0.5;

        AutorizadorReembolso autorizadorReembolso = Mockito.mock(AutorizadorReembolso.class);
        // foi autorizado
        Mockito.when(autorizadorReembolso.foiAutorizado(consulta)).thenReturn(true);

        double reembolso = calculadora.calcularReembolsoSeAutorizado(consulta, plano, autorizadorReembolso);

        assertTrue(MargemHelper.comparar(50.0, reembolso));
    }
    @Test
    public void deveLancarExcecaoQuandoConsultaNaoAutorizada() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        Paciente paciente = new Paciente("Jorge");
        Consulta consulta = new Consulta(paciente, LocalDate.now(), 100.0);

        // em vez de instanciar a interface
        PlanoDeSaudeInterface plano = () -> 0.5;

        AutorizadorReembolso autorizadorReembolso = Mockito.mock(AutorizadorReembolso.class);
        // mockito nao autorizado
        Mockito.when(autorizadorReembolso.foiAutorizado(consulta)).thenReturn(false);

        // pega o texto da exceção para comparar no assert
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculadora.calcularReembolsoSeAutorizado(consulta, plano, autorizadorReembolso)
        );

        assertEquals("Consulta não autorizada", ex.getMessage());
    }

    @Test
    public void naoDeveUltrapassarReembolsoMaximo() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        double valor = 500;
        double percentual = 0.5;

        double reembolso = calculadora.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(150.0, reembolso));
    }

    @Test
    public void deveReembolsarAbaixoDoMaximo() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        double valor = 100;
        double percentual = 0.5;

        double reembolso = calculadora.calcularReembolso(dummy, valor, percentual);

        assertTrue(MargemHelper.comparar(50.0, reembolso));
    }

    // 12 teste completasso
    @Test
    public void deveCalcularReembolsoComPlanoEAutorizadoEHelper() {
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        Consulta consulta = ConsultaHelper.criarConsultaPadrao();
        PlanoDeSaudeInterface plano = () -> 0.5;

        AutorizadorReembolso mock = Mockito.mock(AutorizadorReembolso.class);
        Mockito.when(mock.foiAutorizado(consulta)).thenReturn(true);

        double reembolso = calculadora.calcularReembolsoSeAutorizado(consulta, plano, mock);

        double esperado = Math.min(consulta.getValor() * 0.5, 150);

        assertTrue(MargemHelper.comparar(esperado, reembolso));
    }
}
