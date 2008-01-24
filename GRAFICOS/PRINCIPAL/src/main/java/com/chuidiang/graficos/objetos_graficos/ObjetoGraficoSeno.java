/**
 * Javier Abellï¿½n. 6 Abril 2006
 * 
 * Librerï¿½a grï¿½fica
 */


package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;

import com.chuidiang.matematicas.funciones.Seno;

/**
 * Objeto grï¿½fico que dibuja la funciï¿½n matemï¿½tica sin(x), Heread de
 * FuncionAbstracta implementando el mï¿½todo funcion():
 */
public class ObjetoGraficoSeno extends FuncionAbstracta
{

    /**
     * Crea un objeto grï¿½fico Seno con la amplitud, frecuencia, desfase y color
     * que se le indican. La amplitud va en las mismas unidades de usuario que
     * el eje y del Lienzo donde se quiera dibujar esta funciï¿½n. La frecuencia
     * es el nï¿½mero de ciclos que deben dibujarse por cada unidad del eje x, en
     * unidades de usuario. El desfase en el ï¿½ngulo en radianes de desfase de la
     * funciï¿½n seno. Si no se quieren cosas raras, un buen valor es 0.0 color es
     * en el que se dibujarï¿½ la funciï¿½n. No se comprueban los valores que se
     * pasan.
     */
    public ObjetoGraficoSeno(double amplitud, double frecuencia, double desfase, Color color)
    {
        super(color);
        seno = new Seno();
        seno.setAmplitud(amplitud);
        seno.setFrecuencia(frecuencia);
        seno.setDesfase(desfase);
    }

    /** Función para cálculo del sin(x) */
    private Seno seno;
    
    /**
     * Devuelve el valor de y para un valor concreto de x. Se utiliza la funciï¿½n
     * sin() con todos los parametros de frecuencia, amplitud y desfase.
     */
    public double getY(double x)
    {
        return seno.getY(x);
    }
    
	public double getAmplitud() {
		return seno.getAmplitud();
	}
	public double getDesfase() {
		return seno.getDesfase();
	}
	public double getFrecuencia() {
		return seno.getFrecuencia();
	}
	public void setAmplitud(double amplitud) {
		seno.setAmplitud(amplitud);
	}
	public void setDesfase(double desfase) {
		seno.setDesfase(desfase);
	}
	public void setFrecuencia(double frecuencia) {
		seno.setFrecuencia(frecuencia);
	}
}