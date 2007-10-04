/*
 * Javier Abellán, 30 de mayo de 2004
 * ZoomPorcentaje.java
 */

package chuidiang.graficos.botones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

import chuidiang.graficos.InterfaceEscalaGrafica;
import chuidiang.matematicas.UtilRectangulo;


/**
 * Botón de zoom gráfico para acercar o alejar el gráfico. Se crea el botón
 * pasándole la EscalaGráfica sobre la que debe actuar y el porcentaje de
 * acercamiento o alejamiento que se quiere cada vez que se pulse el botón.
 */
public class ZoomPorcentaje extends JButton
{
    /**
	 * Serial uid
	 */
	private static final long serialVersionUID = -9044127362988998605L;

	/**
     * Constructor del botón de zoom de acercar o alejar. Se le pasa la escala
     * gráfica sobre la que se quiere que actue el botón. El porcentaje de
     * acercamiento o alejamiento va entre -100 y 100, excluidos ambos valores.
     * Un porcentaje positivo hace que el gráfico se acerque. Un valor negativo
     * hace que se aleje.
     */
    public ZoomPorcentaje(InterfaceEscalaGrafica escala, int porcentaje)
    {
        this.escala = escala;
        tomaPorcentaje(porcentaje);

        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                efectuarZoom();
            }
        });
    }

    /**
     * Se le pasa el porcentaje de acercamiento del gráfico cada vez que se
     * pulse el botón. El rango válido para porcentaje es de -99 a 99. Cualquier
     * otro valor se ignora. Un porcentaje negativo hará que el zoom sea de
     * alejar y uno positivo de acercar.
     */
    public void tomaPorcentaje(int porcentaje)
    {
        if ((porcentaje > -100) && (porcentaje < 100))
            this.porcentaje = porcentaje;
    }

    /**
     * Devuelve el porcentaje de acercamiento del botón
     */
    public int damePorcentaje()
    {
        return porcentaje;
    }

    /**
     * Realiza el zoom con el porcentaje interno de la clase. El porcentaje va
     * de -99 a 99. Un porcentaje positivo acerca, uno negativo aleja.
     */
    protected void efectuarZoom()
    {
        Rectangle2D extremos = escala.dameExtremosCartesianos(  );

        Rectangle2D nuevo = UtilRectangulo.agrandar( 
              extremos,
              porcentaje );
        escala.tomaExtremos( 
           nuevo.getMinX(  ),
           nuevo.getMinY(  ),
           nuevo.getMinX(  ) + nuevo.getWidth(  ),
           nuevo.getMinY(  ) + nuevo.getHeight(  ) );
    }

    /** Escala gráfica sobre la que actua el botón de acercar. */
    protected InterfaceEscalaGrafica escala;

    /** Porcentaje de acercamiento cada vez que se pulsa el botón */
    protected int porcentaje = 10;

}
