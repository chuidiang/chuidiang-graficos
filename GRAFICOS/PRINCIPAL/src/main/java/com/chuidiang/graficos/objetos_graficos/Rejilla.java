/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */

package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import com.chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Rejilla para un gr�fico.
 */
public class Rejilla extends AbstractObjetoGrafico
{

    /** Dibuja la rejilla sobre escala que se le pasa */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        Rectangle2D dimensiones = escala.dameExtremos();
        double divisionesX[] = dameDivisiones(dimensiones.getMinX(),
                dimensiones.getMaxX());
        double divisionesY[] = dameDivisiones(dimensiones.getMinY(),
                dimensiones.getMaxY());

        Point2D eje[] = new Point2D[2];
        for (int i = 0; i < divisionesX.length; i++)
        {
        	
            eje[0] = new Point2D.Double(divisionesX[i], dimensiones.getMinY());
            eje[1] = new Point2D.Double(divisionesX[i], dimensiones.getMaxY());
            escala.pintaEjeY(divisionesX[i],Color.RED);
            escala.pintaTexto(eje[0], Double.toString(divisionesX[i]),
                    Color.GREEN);
        }
        for (int i = 0; i < divisionesY.length; i++)
        {
            eje[0] = new Point2D.Double(dimensiones.getMinX(), divisionesY[i]);
            eje[1] = new Point2D.Double(dimensiones.getMaxX(), divisionesY[i]);
            escala.pintaEjeX(divisionesY[i],Color.RED);
            escala.pintaTexto(eje[0], Double.toString(divisionesY[i]),
                    Color.GREEN);
        }
    }

    /**
     * Devuelve las divisiones sobre un eje, d�ndole el m�nimo y el m�ximo
     * 
     * @param min
     *            Valor m�nimo del eje
     * @param max
     *            Valor m�ximo del eje
     * @return Un array con las divisiones para la rejilla en ese eje.
     */
    protected double[] dameDivisiones(double min, double max)
    {
        // Diferencia entre maximo y minimo
        double rango = max - min;

        // Numero de digitos que tiene el rango.
        int digitos = (int) (Math.log(rango) / Math.log(10.0));

        // Incremento que se debe dar a la rejilla
        double incremento = Math.pow(10.0, digitos) / 10.0;

        // si hay m�s de 10 divisiones en la rejilla, se reducen.
        if ((max - min) / incremento > 10)
        {
            while ((max - min) / incremento > 10)
            {
                incremento = incremento * 5.0;
                if ((max - min) / incremento > 10)
                    incremento = incremento * 2.0;
            }
        }
        // Si hay menos de 5 divisiones, se incrementan.
        else if ((max - min) / incremento < 5)
        {
            while ((max - min) / incremento < 5)
            {
                incremento = incremento / 2.0;
                if ((max - min) / incremento < 5)
                    incremento = incremento / 5.0;
            }
        }

        // Valor para la primera division
        double divisionMinima = (Math.floor(min / incremento)) * incremento;

        // Valor para la �ltima division.
        double divisionMaxima = (Math.ceil(max / incremento)) * incremento;

        // N�mero de divisiones que se van a hacer.
        int numeroDivisiones = (int) Math
                .round((divisionMaxima - divisionMinima) / incremento);

        // Se rellena el array que se va a devolver.
        double[] resultado = new double[numeroDivisiones];
        for (int i = 0; i < numeroDivisiones; i++)
        {
            resultado[i] = divisionMinima + i * incremento;
        }
        return resultado;
    }
}
