/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */


package chuidiang.graficos.objetos_graficos;

import java.awt.Color;

/**
 * Objeto gráfico que dibuja la función matemática sin(x), Heread de
 * FuncionAbstracta implementando el método funcion():
 */
public class Seno extends FuncionAbstracta
{

    /**
     * Crea un objeto gráfico Seno con la amplitud, frecuencia, desfase y color
     * que se le indican. La amplitud va en las mismas unidades de usuario que
     * el eje y del Lienzo donde se quiera dibujar esta función. La frecuencia
     * es el número de ciclos que deben dibujarse por cada unidad del eje x, en
     * unidades de usuario. El desfase en el ángulo en radianes de desfase de la
     * función seno. Si no se quieren cosas raras, un buen valor es 0.0 color es
     * en el que se dibujará la función. No se comprueban los valores que se
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
     * Devuelve el valor de y para un valor concreto de x. Se utiliza la función
     * sin() con todos los parámetros de frecuencia, amplitud y desfase.
     */
    protected double funcion(double x)
    {
        return amplitud * Math.sin(2.0 * Math.PI * frecuencia * x + desfase);
    }

    /** Amplitud de la funcion sin() en unidades de usuario del eje y */
    private double amplitud;

    /** Numero de ciclos por unidad de usuario del eje x */
    private double frecuencia;

    /** Desfase en radianes de la función seno. */
    private double desfase;
}
