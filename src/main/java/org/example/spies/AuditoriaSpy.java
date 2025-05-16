package org.example.spies;

import org.example.interfaces.AuditoriaInterface;
import org.example.models.Consulta;

public class AuditoriaSpy implements AuditoriaInterface {

    private boolean foiChamada = false;
    private Consulta consultaArmazenada = null;

    @Override
    public void registrarConsulta(Consulta consulta) {
        foiChamada = true;
        consultaArmazenada = consulta;
    }

    public boolean getFoiChamada() {
        return foiChamada;
    }

    public Consulta getConsultaArmazenada() {
        return consultaArmazenada;
    }
}
