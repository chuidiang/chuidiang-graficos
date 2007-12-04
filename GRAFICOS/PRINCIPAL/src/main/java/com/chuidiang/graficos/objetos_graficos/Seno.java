/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;

/**
 * Objeto gr�fico que dibuja la funci�n matem�tica sin(x), Heread de
 * FuncionAbstracta implementando el m�todo funcion():
 */
public class Seno extends FuncionAbstracta
{

    /**
     * Crea un objeto gr�fico Seno con la amplitud, frecuencia, desfase y color
     * que se le indican. La amplitud va en las mismas unidades de usuario que
     * el eje y del Lienzo donde se quiera dibujar esta funci�n. La frecuencia
     * es el n�mero de ciclos que deben dibujarse por cada unidad del eje x, en
     * unidades de usuario. El desfase en el �ngulo en radianes de desfase de la
     * funci�n seno. Si no se quieren cosas raras, un buen valor es 0.0 color es
     * en el que se dibujar� la funci�n. No se comprueban los valores que se
     * pasan.
     */
    public Seno(double amplitud, double frecuencia, double desfase, Color color)
    {
        super(color);
        this.amplitud = amplitud;
        this.frecuencia = frecuencia;
        this.desfase = desfase;
    }

    /**
     * Devuelve el valor de y para un valor concreto de x. Se utiliza la funci�n
     * sin() con todos los par�metros de frecuencia, amplitud y desfase.
     */
    protected double funcion(double x)
    {
        return amplitud * Math.sin(2.0 * Math.PI * frecuencia * x + desfase);
    }

    /** Amplitud de la funcion sin() en unidades de usuario del eje y */
    private double amplitud;

    /** Numero de ciclos por unidad de usuario del eje x */
    private double frecuencia;

    /** Desfase en radianes de la funci�n seno. */
    private double desfase;
}
