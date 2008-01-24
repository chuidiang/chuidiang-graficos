/**
 * Javier Abell�n. 6 Abril 2006
 * 
 * Librer�a gr�fica
 */


package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;

import com.chuidiang.matematicas.funciones.Seno;

/**
 * Objeto gr�fico que dibuja la funci�n matem�tica sin(x), Heread de
 * FuncionAbstracta implementando el m�todo funcion():
 */
public class ObjetoGraficoSeno extends FuncionAbstracta
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
    public ObjetoGraficoSeno(double amplitud, double frecuencia, double desfase, Color color)
    {
        super(color);
        seno = new Seno();
        seno.setAmplitud(amplitud);
        seno.setFrecuencia(frecuencia);
        seno.setDesfase(desfase);
    }

    /** Funci�n para c�lculo del sin(x) */
    private Seno seno;
    
    /**
     * Devuelve el valor de y para un valor concreto de x. Se utiliza la funci�n
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