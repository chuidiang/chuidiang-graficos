/*
 * 
 */
package com.chuidiang.matematicas.funciones;

/**
 * Funcion sin(x).
 * Permite modificar amplitud y frecuencia, de forma que
 * y = amplitud*sin(frecuencia*x)
 * @author chuidiang
 *
 */
public class Seno implements IfzFuncionMatematica {
    /**
     * Amplitud
     */
    double amplitud = 1.0;
    /**
     * Frecuencia
     */
    double frecuencia = 1.0;

    /**
     * Devuelve la amplitud
     * @return
     */
    public double getAmplitud() {
        return amplitud;
    }

    /**
     * Fija la amplitud
     * @param amplitud
     */
    public void setAmplitud(double amplitud) {
        this.amplitud = amplitud;
    }

    /**
     * Devuelve la frecuencia
     * @return
     */
    public double getFrecuencia() {
        return frecuencia;
    }

    /**
     * Fija la frecuencia
     * @param frecuencia
     */
    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    /**
     * Devuelve amplitud*sin(frecuencia*x) con la x en radianes.
     */
    public double funcion(double x) {
        return amplitud * Math.sin(frecuencia * x);
    }

}
