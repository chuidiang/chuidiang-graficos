/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.applets;


import java.awt.geom.Point2D;

import chuidiang.graficos.EscalaGraficaPolar;
import chuidiang.graficos.InterfaceEscalaGrafica;
import chuidiang.graficos.Lienzo;
import chuidiang.graficos.objetos_graficos.CursorHilo;
import chuidiang.graficos.objetos_graficos.CursorZoom;
import chuidiang.graficos.objetos_graficos.GraficoFuncionPorPuntos;
import chuidiang.graficos.objetos_graficos.GraficoPuntos;
import chuidiang.graficos.objetos_graficos.Mano;
import chuidiang.graficos.objetos_graficos.Rejilla;



public class AppletLienzoPolares extends AbstractAppletPruebaLienzo
{
	/**
	 * serial uid
	 */
	private static final long serialVersionUID = 3545239132780443700L;
    
    /** Puntos a dibujar */
	private  Point2D [] puntos;
    
    /** Grafico de puntos para dibujar los puntos */
	private  GraficoPuntos gp;
	/**
	 * main para el programa de prueba, por si no se quiere emplear como applet.
     * @param args
     */
    public static void main(String[] args)
    {
        AppletLienzoPolares applet = new AppletLienzoPolares();
        applet.visualizaFrame();
        
    }

	protected InterfaceEscalaGrafica dameEscalaGrafica()
	{
		return new EscalaGraficaPolar();
	}



	@Override
	protected boolean quieresBotoneria()
	{
		
		return true;
	}

	@Override
	protected void ponObjetosGraficos(Lienzo lienzo)
	{
        puntos = new Point2D.Double[1000];
		for (int i=0;i<1000;i++)
        {
            puntos[i] = new Point2D.Double(Math.random()*5.0+25,i*2.0*Math.PI/1000.0);
        }
        
        gp = new GraficoPuntos(puntos);
        lienzo.tomaObjetoGrafico(gp);
        
        double [] puntos2 = new double[100];
        for (int i=0;i<100;i++)
            puntos2[i]=Math.random()*0.5-0.25;
        GraficoFuncionPorPuntos gp2 = new GraficoFuncionPorPuntos(0.0,100.0,puntos2);
        lienzo.tomaObjetoGrafico(gp2);
        
        Mano mano = new Mano(eg,lienzo);
        lienzo.anhadeObservadorRaton(mano);
        
        CursorHilo cursor = new CursorHilo(true,true);
        // lienzo.tomaObjetoGrafico(cursor);
        lienzo.anhadeObservadorRaton(cursor);
        
        CursorZoom cursorZoom = new CursorZoom();
        lienzo.anhadeObservadorRaton(cursorZoom);

        Rejilla rej=new Rejilla();
        lienzo.tomaObjetoGrafico(rej);
		
	}

	@Override
	protected void ponExtremos(InterfaceEscalaGrafica eg2)
	{
		eg2.tomaExtremos(-100.0,-100.0,100.0,100.0);
	}

	@Override
	protected void ponBotonesAdicionales(BotoneriaZoom botoneria)
	{
	}
}

