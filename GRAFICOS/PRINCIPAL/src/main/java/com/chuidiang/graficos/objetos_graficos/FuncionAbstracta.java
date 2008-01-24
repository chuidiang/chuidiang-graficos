/*
 * Javier Abell�n. 25 Mayo 2004
 * 
 * Objeto gr�fico abstracto para pintar funciones matem�ticas y = f(x)
 */
package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Clase abstracta para dibujar funciones matem�ticas y = f(x) sobre una clase
 * Lienzo. Debe heredarse de esta clase y redefinir el m�todo funcion(). La
 * clase se encarga del dibujado de la funci�n sobre el Lienzo.
 */
public abstract class FuncionAbstracta extends AbstractObjetoGrafico
{

    /**
     * Recoge el color que se le pasa y dibujar� la funci�n matem�tica con ese
     * color.
     */
    public FuncionAbstracta(Color color)
    {
        tomaColor(color);
    }

    /**
     * Recoge el color que se le pasa para dibujar la funci�n con ese color. El
     * dibujo cambiar� de color en el siguiente refresco del gr�fico. Si el
     * color es null, la funci�n dejar� de dibujarse.
     */
    public void tomaColor(Color color)
    {
        this.color = color;
    }

    /**
     * Devuelve el color con el que se est� dibujando la funci�n. Devuelve null
     * si previamente se le pas� a la clase un color null.
     */
    public Color dameColor()
    {
        return color;
    }

    /**
     * Se le pasa el n�mero de puntos a dibujar de la funci�n. Se dibujar�n esos
     * puntos entre la x minima y la x maxima de la escala que se pase en el
     * m�todo dibujate. Si el n�mero de puntos es menor de 2, se ignora el
     * par�metro se deja el n�mero de puntos anterior.
     */
    public void tomaNumeroPuntos(int numeroPuntos)
    {
        if (numeroPuntos < 2)
            return;
        this.numeroPuntos = numeroPuntos;
    }

    /**
     * Devuelve el n�mero de puntos que se est� utilizando para dibujar la
     * funci�n.
     */
    public int dameNumeroPuntos()
    {
        return numeroPuntos;
    }

    /**
     * Dibuja la funci�n matem�tica sobre la escala. Obtiene la x minima y
     * maxima de la escala para hacer un bucle de 500 puntos. Calcula los 500
     * puntos de la funci�n para todas esas x y dibuja segementos que unen
     * consecutivamente todos esos puntos, haciendo que la funci�n quede
     * dibujada sobre una l�nea.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (color == null)
            return;

        Rectangle2D extremos = escala.getExtremosCartesianos();
        Point2D puntos[] = new Point2D[numeroPuntos];
        double x;

        for (int i = 0; i < numeroPuntos; i++)
        {
            x = extremos.getMinX() + i * extremos.getWidth() / numeroPuntos;
            puntos[i] = new Point2D.Double(x, funcion(x));
        }

        escala.pintaPoliLinea(puntos, color);
    }

    /**
     * Funci�n que se va a dibujar. Las clases hijas de esta deben redefinir
     * dicha funci�n, devolviendo el valor de y correspondiente a la x que se le
     * pasa.
     */
    protected abstract double funcion(double x);

    /** Color con el que se dibuja la funci�n */
    private Color color;

    /** N�mero de puntos que se van a dibujar de la funci�n. */
    private int numeroPuntos = 500;
}
