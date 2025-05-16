package org.example;

import org.example.models.Consulta;
import org.example.models.Paciente;
import org.example.spies.AuditoriaSpy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class AuditoriaSpyTest {

    @Test
    public void deveRegistrarAuditoriaQuandoConsultaForRegistrada() {
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
        Paciente paciente = new Paciente("Joana da Silva");
        Consulta consulta = new Consulta(paciente, LocalDate.now(), 100.0);

        auditoriaSpy.registrarConsulta(consulta);

        assertTrue(auditoriaSpy.getFoiChamada());
        assertEquals(consulta, auditoriaSpy.getConsultaArmazenada());
    }
}
