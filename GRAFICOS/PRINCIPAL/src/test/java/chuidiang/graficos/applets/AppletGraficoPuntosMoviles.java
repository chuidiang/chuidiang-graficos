/**
 * Javier Abellán. 6 Abril 2006
 *
 * Librería gráfica
 */
package chuidiang.graficos.applets;

import chuidiang.graficos.InterfaceEscalaGrafica;
import chuidiang.graficos.Lienzo;

import chuidiang.graficos.objetos_graficos.CursorHilo;
import chuidiang.graficos.objetos_graficos.CursorZoom;
import chuidiang.graficos.objetos_graficos.GraficoFuncionPorPuntos;
import chuidiang.graficos.objetos_graficos.Mano;
import chuidiang.graficos.objetos_graficos.RejillaFija;

import java.awt.Color;


/**
 * Ejemplo de un gráfico x/y en el que se dibuja una función por puntos. Los
 * valores de dichos puntos se hacen variar cada segundo, de forma que el
 * gráfico varíe.
 *
 * @author Chuidiang
 */
public class AppletGraficoPuntosMoviles extends AbstractAppletPruebaLienzo
{
    /**
     * serial uid
     */
    private static final long serialVersionUID = 3617858581588948528L;

    /** Puntos que se van a dibujar en el gráfico */
    double[] puntos;

    /** Gráfico de puntos */
    GraficoFuncionPorPuntos gp;

    /**
     * Método init() del applet. Inicializa el lienzo con todos sus objetos
     * gráficos y lo mete a él y a la botonería de zoom en el Applet.
     */
    public void init()
    {
        super.init();
        muevePuntos();
    }

    /**
     * Pone un grafico por puntos, cursores, mano y rejilla en el lienzo que se le pasa.
     *
     * @param linezo Lienzo al que añadir los objetos graficos.
     */
    protected void ponObjetosGraficos(Lienzo linezo)
    {
        puntos = new double[100];

        for (int i = 0; i < 100; i++)
        {
            puntos[i] = (Math.random() * 200) - 100;
        }

        gp = new GraficoFuncionPorPuntos(-100.0, 100.0, puntos);

        lienzo.tomaObjetoGrafico(gp);

        Mano mano = new Mano(eg, lienzo);
        lienzo.anhadeObservadorRaton(mano);

        CursorZoom cursor = new CursorZoom();
        lienzo.anhadeObservadorRaton(cursor);

        CursorHilo cursor2 = new CursorHilo(true, false);
        lienzo.anhadeObservadorRaton(cursor2);

        RejillaFija rej = new RejillaFija(10, 10);
        rej.tomaColores(Color.GRAY, Color.GREEN);
        lienzo.tomaObjetoGrafico(rej);
    }

    /**
     * Main para probar si no se quiere usar el applet
     *
     * @param args
     */
    public static void main(String[] args)
    {
        AppletGraficoPuntosMoviles applet = new AppletGraficoPuntosMoviles();
        applet.visualizaFrame();
        applet.muevePuntos();
    }

    /** Hace que los puntos del gráfico cambien una vez por segundo */
    public void muevePuntos()
    {
        Thread hilo = new Thread()
            {
                public void run()
                {
                    try
                    {
                        while (true)
                        {
                            for (int i = 0; i < 100; i++)
                            {
                                puntos[i] = (Math.random() * 200) - 100;
                            }

                            gp.setPuntos(puntos);
                            Thread.sleep(1000);
                        }
                    }
                    catch (Exception e)
                    {
                    }
                }
            };

        hilo.start();
    }

    /**
     * DOCUMENT ME!
     *
     * @param eg2 DOCUMENT ME!
     */
    @Override
    protected void ponExtremos(InterfaceEscalaGrafica eg2)
    {
        eg.tomaExtremos(-100.0, -100.0, 100.0, 100.0);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    protected boolean quieresBotoneria()
    {
        return true;
    }

    /**
     * DOCUMENT ME!
     *
     * @param botoneria DOCUMENT ME!
     */
    @Override
    protected void ponBotonesAdicionales(BotoneriaZoom botoneria)
    {
    }
}
