/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.botones;

import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import chuidiang.graficos.objetos_graficos.ObservadorRaton;


/**
 * JTextField que se puede suscribir al movimiento de ratón en un lienzo y
 * dibuja una de sus coordenadas, la x o la y.
 */
public class VisorRaton extends JTextField implements ObservadorRaton
{
    /**
	 * Serial uid
	 */
	private static final long serialVersionUID = 7672591351320293136L;

	/**
     * Crea una instancia de VisorRaton. tipoVisor puede ser VISOR_X o VISOR_Y,
     * según se quiera que se muestre la coordenada x o y del ratón.
     * 
     * @param tipoVisor
     *            VISOR_X o VISOR_Y
     */
    public VisorRaton(int tipoVisor)
    {
        this.tipoVisor = tipoVisor;
    }

    /**
     * Método al que se llama cada vez que se mueve el ratón dentro del lienzo.
     * Pinta la coordenada x o la y en el JTextField.
     */
    public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
    {
        if (this.tipoVisor == VISOR_X)
            this.setText(Double.toString(x));
        else
            this.setText(Double.toString(y));
        return false;
    }

    /** Para indicar que se quiere la coordenada x en el visor */
    public final static int VISOR_X = 0;

    /** Para indicar que se quiere la coordenada y en el visor */
    public final static int VISOR_Y = 1;

    /** Coordenada que se quiere visualizar. Puede valer VISOR_X o VISOR_Y */
    private int tipoVisor;
}
