/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Cursor para hacer zoom. Se hace click y se arrastra el ratón. Sale un
 * rectángulo que va creciendo con el ratón. Al sotar, se hace zoom.
 */
public class CursorZoom extends AbstractObjetoGrafico implements
        ObservadorRaton
{
    /**
     * Método al que se llama con los eventos de ratón. Al pulsar el ratón
     * comienza el proceso. Mientras se arrastra, se va redibujando el
     * rectángulo. al sotar se hace el zoom.
     */
    public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
    {
        // Si se arrastra el ratón, se va rehaciendo el rectángulo.
        if (tipoEvento == ObservadorRaton.ARRASTRE)
        {
            if (!pintar)
                return false;
            this.x2 = x;
            this.y2 = y;
            return true;
        }

        // Al pulsar, comienza el proceso y se inicializan las esquinas del
        // rectangulo a la posicion actual del raton.
        if ((tipoEvento == ObservadorRaton.PULSADO)
                && (e.getButton() == MouseEvent.BUTTON1))
        {
            pintar = true;
            this.x1 = x;
            this.y1 = y;
            this.x2 = x;
            this.y2 = y;
            return true;
        }

        // Al soltar, se hace el zoom.
        if ((tipoEvento == ObservadorRaton.SOLTADO)
                && (e.getButton() == MouseEvent.BUTTON1))
        {
            if (this.escala != null)
            {
                double xMin = Math.min(x1, x2);
                double xMax = Math.max(x1, x2);
                double yMin = Math.min(y1, y2);
                double yMax = Math.max(y1, y2);

                // Antes de hacer el zoom, se comprueba que el rectangulo no
                // es demasiado pequeño
                Rectangle2D extremos = escala.dameExtremosCartesianos();
                if (((xMax - xMin) > extremos.getWidth() / 20.0)
                        && ((yMax - yMin) > extremos.getHeight() / 20.0))
                    escala.tomaExtremos(xMin, yMin, xMax, yMax);
            }
            pintar = false;
        }
        return false;
    }

    /**
     * Dibuja el rectangulo según se va moviendo el ratón. Se guarda la escala
     * que se le pasa para poder hacerle zoom
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        this.escala = escala;
        if (!pintar)
            return;
        Point2D[] rectangulo = new Point2D.Double[5];
        rectangulo[0] = new Point2D.Double(x1, y1);
        rectangulo[1] = new Point2D.Double(x1, y2);
        rectangulo[2] = new Point2D.Double(x2, y2);
        rectangulo[3] = new Point2D.Double(x2, y1);
        rectangulo[4] = new Point2D.Double(x1, y1);
        escala.pintaPoliLinea(rectangulo, Color.WHITE);
    }

    /** Posicion de una esquina del rectangulo */
    private double x1, x2;

    /** Posicion de otra esquina del rectangulo */
    private double y1, y2;

    /** Si hay que pintar o no el rectangulo */
    private boolean pintar = false;

    /** Escala sobre la que se va a hacer zoom. */
    private InterfaceEscalaGrafica escala = null;
}
