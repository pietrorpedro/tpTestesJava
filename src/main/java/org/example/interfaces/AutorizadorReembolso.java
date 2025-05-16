package org.example.interfaces;

import org.example.models.Consulta;

public interface AutorizadorReembolso {
    boolean foiAutorizado(Consulta consulta);
}
