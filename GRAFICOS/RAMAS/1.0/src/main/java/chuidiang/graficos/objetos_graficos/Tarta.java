/**
 * Javier Abellán, 25 Mayo 2004
 *
 * Objeto gráfico de tarta.
 */

package chuidiang.graficos.objetos_graficos;

import java.awt.geom.*;
import java.awt.Color;

import chuidiang.graficos.InterfaceEscalaGrafica;



/**
 * Gráfico de tarta.
 * Se instancia pasándole un array de valores double. Supone que la suma de
 * todos ellos el el 100% de la tarta y calcula para cada valor su porción
 * correspondiente de tarta.
 * Dibuja un circulo con sectores rellenos, en el que cada sector corresponde
 * a uno de los numeros del array.
 */
public class Tarta extends AbstractObjetoGrafico
{
    /**
     * Se le pasa el array de valores para el gráfico de tarta y los colores
     * de los sectores.
     * Si el array de colores no es del mismo tamaño que el de valores, cuando
     * se terminen los colores para cada valor, volverán a repetirse.
     * Los valores no es necesario que estén en porcentaje. La clase calcula
     * los porcentajes correspondientes a cada valor para asignarles el tamaño
     * de su trozo de tarta.
     */
    public Tarta(double [] valores, Color [] colores) 
    {
        tomaValores (valores);
        this.colores = colores;
    }
    
    /**
     * Se le pasan los valores que debe representar en el gráfico de tarta.
     * Tira los valores anteriores. La tarta cambiará en el próximo refresco
     * del Lienzo.
     */
    public void tomaValores (double [] valores)
    {
        calculaPorcentajes (valores);
    }
    
    /**
     * Devuelve los porcentajes correspondientes a los valores que se le
     * pasaron. La suma de estos porcentajes dará 1.0
     */
    public double [] damePorcentajes ()
    {
        return porcentajes;
    }
    
    /**
     * Calcula las proporciones de los valores que se le pasan y los guarda.
     * Recoge todos los valores y los suma. Luego divide cada uno de ellos
     * por esta suma y los guarda en un array separado. No modifica los
     * valores recibidos.
     * Si valores es null, se ignora y no se hace nada.
     */
    private void calculaPorcentajes (double [] valores)
    {
        if (valores == null)
            return;
        
        if (valores.length == 0)
            return;
        
        porcentajes = new double [valores.length];
        
        double suma = 0;
        for (int i=0; i<valores.length; i++)
            suma += valores[i];
        
        for (int i=0; i<valores.length; i++)
            porcentajes[i] = valores[i]/suma;
        
        this.setNecesitoRepintado(true);
    }
    
    /**
     * Dibuja el gráfico de tarta sobre la escala que se le pasa.
     * Hace que el circulo de la tarta ocupe el 90% del Lienzo por el lado
     * del eje más pequeño en unidades de usuario (no en pixels).
     */
    public void dibujate(InterfaceEscalaGrafica escala) 
    {
        if (porcentajes == null) return;
        if (colores == null) return;
        if (porcentajes.length == 0) return;
        if (colores.length == 0) return;
        
        extremos = escala.dameExtremosCartesianos();
        double radio = dameRadio();
        Point2D centro = dameCentro();
        
        double arcoInicial = 0.0;
        int indiceColor = 0;
        
        for (int i=0; i<porcentajes.length; i++)
        {
            escala.pintaArcoRelleno (centro, radio, radio, arcoInicial, 
                dameAngulo(porcentajes[i]), colores[indiceColor]);
            arcoInicial = arcoInicial + dameAngulo(porcentajes[i]);
            
            // Se incrementa el indice de color, volviendo al principio si
            // nos salimos del array.
            indiceColor++;
            if (indiceColor >= colores.length)
                indiceColor = 0;
        }
    }

    /**
     * Devuelve el radio de la tarta, haciendo que sea el 90% del eje más
     * pequeño.
     */
    protected double dameRadio ()
    {
        double minimo = Math.min (extremos.getWidth(), extremos.getHeight());
        return minimo*0.9;
    }
    
    /**
     * Devuelve las coordenadas del centro del Lienzo en unidades de usuario.
     * Este centro será el centro de la tarta.
     */
    protected Point2D dameCentro()
    {
        Point2D centro = new Point2D.Double();
        centro.setLocation (   
            extremos.getMinX() + extremos.getWidth()*0.05,
            extremos.getMinY() + extremos.getHeight()*0.05);
        return centro;
    }
    
    /**
     * Devuelve el ángulo en grados que corresponde a un porcentaje determinado.
     * El porcentaje se pasa en tanto por 1 (de 0.0 a 1.0). Devuelve un ángulo
     * entreo 0 y 360.
     */
    protected double dameAngulo (double porcentaje)
    {
        double angulo;
        angulo = porcentaje*360;
        return angulo;
    }
    
    
    /** Extremos del lienzo, coordenadas máximas y mínimas de cada eje */
    private Rectangle2D extremos;
    
    /** Colores para cada uno de los sectores de la tarta */
    private Color [] colores;
    
    /** Ancho de cada uno de los sectores de la tarta, en tanto por uno. */
    private double [] porcentajes;
}
