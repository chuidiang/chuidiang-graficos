/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */


package chuidiang.graficos.objetos_graficos;

import java.awt.geom.*;
import java.awt.*;
import java.text.DecimalFormat;

import chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Rejilla para un gráfico con ejes. La rejilla contiene un número de divisiones
 * fijas y equiespaciadas. No admite un número de divisiones menor que dos.
 * Permite cambiar el color de las líneas de la rejilla y de los números que
 * aparecen en cada división. Si a los números o a los ejes se les pasa color
 * null, simplemente no aparecen.
 */
public class RejillaFija extends AbstractObjetoGrafico
{

    /**
     * Crea una rejilla con el número de filas y columnas que se le pasan como
     * paráemtro. Si el número de filas es menor que 2, se ponen dos filas Si el
     * número de columnas es menor que 2, se ponen dos columnas.
     */
    public RejillaFija(int numeroFilas, int numeroColumnas)
    {
        if (numeroFilas > 1)
            this.numeroFilas = numeroFilas;

        if (numeroColumnas > 1)
            this.numeroColumnas = numeroColumnas;
    }

    /**
     * Devuelve el número de filas de la rejilla
     */
    public int dameNumeroFilas()
    {
        return numeroFilas;
    }

    /**
     * Devuelve el número de columnas de la rejilla
     */
    public int dameNumeroColumnas()
    {
        return numeroColumnas;
    }

    /**
     * Se le pasa el número de divisiones que se desean para cada eje. Si alguno
     * de los números es menor que dos, se ignora y se mantiene el número de
     * divisiones anterior para ese eje.
     */
    public void tomaDivisiones(int numeroFilas, int numeroColumnas)
    {
        if (numeroFilas > 1)
            this.numeroFilas = numeroFilas;

        if (numeroColumnas > 1)
            this.numeroColumnas = numeroColumnas;
        
        this.setNecesitoRepintado(true);
    }

    /**
     * Recoge los colores para los ejes y para los números. Si un color es null,
     * desaparecerán los ejes o los números en el siguiente repintado del
     * gráfico.
     */
    public void tomaColores(Color colorRejilla, Color colorNumeros)
    {
        this.colorEje = colorRejilla;
        this.colorNumero = colorNumeros;
        this.setNecesitoRepintado(true);
    }

    /**
     * Devuelve el color que se está usando para dibujar los ejes. Devuelve null
     * si previamente se pasó un color null.
     */
    public Color dameColorRejilla()
    {
        return colorEje;
    }

    /**
     * Devuelve el color que se está usando para dibujar los números. Devuelve
     * null si previamente se pasó un color null
     */
    public Color dameColorNumeros()
    {
        return colorNumero;
    }

    /**
     * Dibuja la rejilla sobre la escala que se le pasa.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        this.escala = escala;
        rellenaXY();
        dibujaVerticales();
        dibujaHorizontales();
        dibujaNumerosVerticales();
        dibujaNumerosHorizontales();
    }

    /**
     * Dibuja los números de la vertical, es decir, las divisiones del eje y. Si
     * se le ha pasado un color de números null, no dibuja nada.
     */
    private void dibujaNumerosVerticales()
    {
        if (colorNumero == null)
            return;

        Point2D.Double aux = new Point2D.Double();
        DecimalFormat formato = new DecimalFormat("#.##");

        int inicio = 0;
        if (numeroFilas >= numeroColumnas)
            inicio = 1;
        for (int i = inicio; i < numeroFilas; i++)
        {
            aux.setLocation(extremos.getMinX(), y[i]);
            escala.pintaTexto(aux, formato.format(y[i]), colorNumero);
        }
    }

    /**
     * Dibuja los números de la horizontal, es decir, las divisiones del eje x.
     * Si se le ha pasado un color de números null, no dibuja nada.
     */
    private void dibujaNumerosHorizontales()
    {
        if (colorNumero == null)
            return;

        Point2D.Double aux = new Point2D.Double();
        DecimalFormat formato = new DecimalFormat("#.##");

        int inicio = 0;
        if (numeroFilas < numeroColumnas)
            inicio = 1;
        for (int i = inicio; i < numeroColumnas; i++)
        {
            aux.setLocation(x[i], extremos.getMinY());
            escala.pintaTexto(aux, formato.format(x[i]), colorNumero);
        }
    }

    /**
     * Dibuja los ejes verticales, es decir, las divisiones del eje x. Si se le
     * ha pasado un color de eje null, no dibuja nada.
     */
    private void dibujaVerticales()
    {
        if (colorEje == null)
            return;

        Point2D[] eje = new Point2D[2];
        eje[0] = new Point2D.Double();
        eje[1] = new Point2D.Double();
        for (int i = 0; i < numeroColumnas; i++)
        {
        	escala.pintaEjeY(x[i],colorEje);
        	/*
            eje[0].setLocation(x[i], extremos.getMinY());
            eje[1].setLocation(x[i], extremos.getMaxY());
            escala.pintaPoliLinea(eje, colorEje);
            */
        }
    }

    /**
     * Rellena un array de x y otro de y con los valores de x e y para las
     * divisiones en los ejes.
     */
    private void rellenaXY()
    {
        extremos = escala.dameExtremosCartesianos();

        x = new double[numeroColumnas];
        for (int i = 0; i < numeroColumnas; i++)
            x[i] = extremos.getMinX() + i * extremos.getWidth()
                    / numeroColumnas;

        y = new double[numeroFilas];
        for (int i = 0; i < numeroFilas; i++)
            y[i] = extremos.getMinY() + i * extremos.getHeight() / numeroFilas;
    }

    /**
     * Dibuja las divisiones horizontales, es decir, las divisiones del eje y Si
     * se le ha pasado un color de eje null, no dibuja nada.
     */
    private void dibujaHorizontales()
    {
        if (colorEje == null)
            return;

        Point2D[] eje = new Point2D[2];
        eje[0] = new Point2D.Double();
        eje[1] = new Point2D.Double();
        for (int i = 0; i < numeroFilas; i++)
        {
        	escala.pintaEjeX(y[i],colorEje);
        	/*
            eje[0].setLocation(extremos.getMinX(), y[i]);
            eje[1].setLocation(extremos.getMaxX(), y[i]);
            escala.pintaPoliLinea(eje, Color.RED);
            */
        }
    }

    /** Numero de divisiones para el eje y. Por defecto 2 */
    private int numeroFilas = 2;

    /** Numero de divisiones para el eje x. Por defecto 2 */
    private int numeroColumnas = 2;

    /** Escala sobre la que se dibuja la rejilla */
    private InterfaceEscalaGrafica escala;

    /** Limites de la escala sobre la que se dibuja la rejilla */
    private Rectangle2D extremos;

    /**
     * Array de divisiones en el eje x. Se recalcula cada vez que hay que
     * redibujar
     */
    private double[] x;

    /**
     * Array de divisiones sobre el eje y. Se recalcula cada vez que hay que
     * redibujar.
     */
    private double[] y;

    /**
     * Color para las divisiones. Por defecto rojo.
     */
    private Color colorEje = Color.RED;

    /**
     * Color para los números de las divisiones. Por defecto cyan.
     */
    private Color colorNumero = Color.CYAN;
}
