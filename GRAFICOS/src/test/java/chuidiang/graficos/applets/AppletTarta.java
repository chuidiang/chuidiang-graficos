/*
 * Javier Abellán. 1 de Mayo de 2004
 *
 * Lienzo.java
 */
package chuidiang.graficos.applets;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JApplet;

import chuidiang.graficos.EscalaGraficaCartesiana;
import chuidiang.graficos.InterfaceEscalaGrafica;
import chuidiang.graficos.Lienzo;
import chuidiang.graficos.objetos_graficos.Tarta;

/**
 * Ejemplo para mostrar un gráfico de tarta.
 */
public class AppletTarta extends JApplet
{
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3690755081574233394L;

	/**
     * Instancia un lienzo, una tarta y una escala grafica y lo mezcla todo.
     */
    public void init ()
    {
        // el lienzo
        Lienzo lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        
        // Lo metemos en el applet
        this.setLayout(new BorderLayout());
        this.add (lienzo, BorderLayout.CENTER);
        
        // La escala grafica
        InterfaceEscalaGrafica escala = new EscalaGraficaCartesiana();
        escala.tomaExtremos (-100.0, -100.0, 100.0, 100.0);
        lienzo.tomaEscala (escala);

        // La tarta.
        double [] valores = {1.0, 2.0, 3.0, 2.0, 1.0};
        Color [] colores = {Color.RED, Color.GREEN, Color.CYAN};
        Tarta tarta = new Tarta (valores, colores);
        lienzo.tomaObjetoGrafico(tarta);
    }
    
}
