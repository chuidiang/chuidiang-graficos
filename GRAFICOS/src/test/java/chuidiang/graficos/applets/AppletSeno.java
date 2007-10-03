/*
 * Javier Abellán. 1 de Mayo de 2004
 *
 * Ejemplo de funcion seno.
 */
package chuidiang.graficos.applets;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JApplet;

import chuidiang.graficos.EscalaGraficaCartesiana;
import chuidiang.graficos.InterfaceEscalaGrafica;
import chuidiang.graficos.Lienzo;
import chuidiang.graficos.botones.VisorRaton;
import chuidiang.graficos.objetos_graficos.CursorZoom;
import chuidiang.graficos.objetos_graficos.Mano;
import chuidiang.graficos.objetos_graficos.RejillaFija;
import chuidiang.graficos.objetos_graficos.Seno;

/**
 * Ejemplo de uso de los Objetos gráficos Seno y RejillaFija.
 */
public class AppletSeno extends JApplet
{
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3258129141797499953L;

	/**
     * Instancia un Lienzo y mete dentro un Seno y una RejillaFija con la
     * escala adecuada. Pone también una botonería con zoom de acercar y 
     * alejar
     */
    public void init ()
    {
        // El lienzo
        Lienzo lienzo = new Lienzo();
        lienzo.setBackground(Color.black);
        
        // La escala grafica
        InterfaceEscalaGrafica escala = new EscalaGraficaCartesiana();
        
        // Una botoneria de zoom
        BotoneriaZoom zoom = new BotoneriaZoom (escala);
        
        // Lo metemos todo en el applet
        this.setLayout (new BorderLayout());
        this.add(lienzo, BorderLayout.CENTER);
        this.add(zoom, BorderLayout.SOUTH);
        
        // La escala grafica para inicializar el lienzo.
        escala.tomaExtremos (0.0, -1.0, 1.0, 1.0);
        lienzo.tomaEscala (escala);
        
        // Una rejill para el Lienzo
        RejillaFija rejilla = new RejillaFija (5, 10);
        lienzo.tomaObjetoGrafico(rejilla);
        
        // La función Seno para el lienzo.
        Seno seno = new Seno(1.0, 1.0, 0.0, Color.GREEN);
        lienzo.tomaObjetoGrafico(seno);
        
        // Un editor de coordenada x y otro de y
        VisorRaton visorX = new VisorRaton(VisorRaton.VISOR_X);
        visorX.setColumns(20);
        zoom.add(visorX);
        lienzo.anhadeObservadorRaton(visorX);
        VisorRaton visorY = new VisorRaton(VisorRaton.VISOR_Y);
        visorY.setColumns(20);
        zoom.add(visorY);
        lienzo.anhadeObservadorRaton(visorY);
        
        // Un zoom con cursor de goma
        CursorZoom cursorZoom=new CursorZoom();
        lienzo.anhadeObservadorRaton(cursorZoom);
        lienzo.tomaObjetoGrafico(cursorZoom);
        
        // Una mano para poder arrastrar el gráfico.
        Mano mano = new Mano(escala,lienzo);
        lienzo.anhadeObservadorRaton(mano);
        
        // Y finalmente un cursor vertical y horizontal
        /* 
         * Comentado para no liar con el uso del ratón para la mano
         * y para el zoom de goma
         * 
        CursorHilo cursor = new CursorHilo(true,true);
        lienzo.anhadeObservadorRaton(cursor);
        lienzo.tomaObjetoGrafico(cursor);
        */

    }
    
}
