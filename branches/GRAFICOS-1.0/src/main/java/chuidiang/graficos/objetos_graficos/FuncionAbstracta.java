/*
 * Javier Abellán. 25 Mayo 2004
 * 
 * Objeto gráfico abstracto para pintar funciones matemáticas y = f(x)
 */
package chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Clase abstracta para dibujar funciones matemáticas y = f(x) sobre una clase
 * Lienzo. Debe heredarse de esta clase y redefinir el método funcion(). La
 * clase se encarga del dibujado de la función sobre el Lienzo.
 */
public abstract class FuncionAbstracta extends AbstractObjetoGrafico
{

    /**
     * Recoge el color que se le pasa y dibujará la función matemática con ese
     * color.
     */
    public FuncionAbstracta(Color color)
    {
        tomaColor(color);
    }

    /**
     * Recoge el color que se le pasa para dibujar la función con ese color. El
     * dibujo cambiará de color en el siguiente refresco del gráfico. Si el
     * color es null, la función dejará de dibujarse.
     */
    public void tomaColor(Color color)
    {
        this.color = color;
    }

    /**
     * Devuelve el color con el que se está dibujando la función. Devuelve null
     * si previamente se le pasó a la clase un color null.
     */
    public Color dameColor()
    {
        return color;
    }

    /**
     * Se le pasa el número de puntos a dibujar de la función. Se dibujarán esos
     * puntos entre la x minima y la x maxima de la escala que se pase en el
     * método dibujate. Si el número de puntos es menor de 2, se ignora el
     * parámetro se deja el número de puntos anterior.
     */
    public void tomaNumeroPuntos(int numeroPuntos)
    {
        if (numeroPuntos < 2)
            return;
        this.numeroPuntos = numeroPuntos;
    }

    /**
     * Devuelve el número de puntos que se está utilizando para dibujar la
     * función.
     */
    public int dameNumeroPuntos()
    {
        return numeroPuntos;
    }

    /**
     * Dibuja la función matemática sobre la escala. Obtiene la x minima y
     * maxima de la escala para hacer un bucle de 500 puntos. Calcula los 500
     * puntos de la función para todas esas x y dibuja segementos que unen
     * consecutivamente todos esos puntos, haciendo que la función quede
     * dibujada sobre una línea.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (color == null)
            return;

        Rectangle2D extremos = escala.dameExtremosCartesianos();
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
     * Función que se va a dibujar. Las clases hijas de esta deben redefinir
     * dicha función, devolviendo el valor de y correspondiente a la x que se le
     * pasa.
     */
    protected abstract double funcion(double x);

    /** Color con el que se dibuja la función */
    private Color color;

    /** Número de puntos que se van a dibujar de la función. */
    private int numeroPuntos = 500;
}
