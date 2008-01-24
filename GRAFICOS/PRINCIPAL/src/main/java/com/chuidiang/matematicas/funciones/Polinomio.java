/**
 * Funcion que representa un polinomio.
 */
package com.chuidiang.matematicas.funciones;

/**
 * Un polinomio.
 * @author chuidiang
 *
 */
public class Polinomio implements IfzFuncionMatematica {
    /**
     * Coeficientes del polinomio, de forma que
     * y = sumatorio coeficientes[i] * x ^ y
     * es decir, el indice cero corresponde al termino independiente.
     */
    double[] coeficientes = null;

    /**
     * Construye un polinomio con los coeficientes que se le pasan
     * @param coeficientes
     */
    public Polinomio(double[] coeficientes) {
        this.coeficientes = coeficientes;
    }

    /**
     * Devuelve el valor de y para la x que se le pasa, segun la
     * formula
     * y = sumatorio coeficientes[i] * x ^ y
     */
    public double getY(double x) {
        double y = 0.0;
        for (int i = 0; i < coeficientes.length; i++) {
            y = y + (coeficientes[i] * Math.pow(x, i));
        }
        return y;
    }
}
