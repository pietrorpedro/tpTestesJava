package org.example.utils;


public class MargemHelper {

    private static final double MARGEM_PADRAO = 0.01;

    public static boolean comparar(double esperado, double atual) {
        return comparar(esperado, atual, MARGEM_PADRAO);
    }

    public static boolean comparar(double esperado, double atual, double margem) {
        return Math.abs(esperado - atual) <= margem;
    }
}
