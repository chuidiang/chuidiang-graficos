/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 * Clase padre para cursores sobre los gráficos. Permite un cursor que se mueve
 * con el ratón y otros dos cursores fijos o marcas, que se fijan con el botón
 * izquierdo y el derecho del ratón.
 */
public abstract class Cursor extends AbstractObjetoGrafico implements
        ObservadorRaton
{
    /**
     * Método al que se llama cuando se produce un evento de ratón. Se encarga
     * de recalcular las posiciones de los cursores.
     */
    public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
    {
        // Si el ratón entra en el Lienzo, hay que pintar el cursor movil.
        if (tipoEvento == ObservadorRaton.ENTRA)
            pintar = true;
        // Si el ratón se ha salido, no hay que pintar el cursor movil.
        if (tipoEvento == ObservadorRaton.SALE)
            pintar = false;

        // Se actualizan las posiciones del cursor movil.
        this.x = x;
        this.y = y;

        // Si se ha hecho click
        if (tipoEvento == ObservadorRaton.PULSADO)
        {
            // Si el botón es el izquierdo
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                // Se actualizan las posiciones del cursor fijo 1
                x1 = x;
                y1 = y;
                ordenaCursoresFijos();
            }
            // si el botón es el derecho
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                // Se actualizan las posiciones del cursor fijo 2
                x2 = x;
                y2 = y;
                ordenaCursoresFijos();
            }
        }

        this.setNecesitoRepintado(true);
        return true;
    }

    /**
     * Se asegura que el cursor fijo 1 sea el de x1 e y1 menor, dándoles la
     * vuelta si es necesario.
     */
    private void ordenaCursoresFijos()
    {
        double aux;
        if (x1 > x2)
        {
            aux = x1;
            x1 = x2;
            x2 = aux;
        }
        if (y2 > y1)
        {
            aux = y1;
            y1 = y2;
            y2 = aux;
        }
    }

    /** Coordenada x del cursor movil */
    protected double x = 0.0;

    /** Coordenada y del cursor movil */
    protected double y = 0.0;

    /** Coordenada x del cursor fijo 1 */
    protected double x1 = -Double.MAX_VALUE;

    /** Coordenada x del cursor fijo 2 */
    protected double x2 = Double.MAX_VALUE;

    /** Coordenada y del cursor fijo 1 */
    protected double y1 = Double.MAX_VALUE;

    /** Coordenada y del cursor fijo 2 */
    protected double y2 = -Double.MAX_VALUE;

    /**
     * Si debe o no pintarse el cursor movil. Se pintará mientras el ratón esté
     * dentro del lienzo. No se pintará cuando el ratón esté fuera.
     */
    protected boolean pintar = false;

    /** Color para el cursor movil */
    protected Color colorMovil = Color.YELLOW;

    /** Color para el cursor fijo 1 y 2 */
    protected Color colorFijo = Color.WHITE;

    /**
     * Permite obtener el color del cursor fijo.
     * 
     * @return color del cursor fijo.
     */
    public Color getColorFijo()
    {
        return colorFijo;
    }

    /**
     * Permite indicar el color para el cursor fijo
     * 
     * @param colorFijo
     *            Color para cursor fijo.
     */
    public void setColorFijo(Color colorFijo)
    {
        this.colorFijo = colorFijo;
    }

    /**
     * Permite obtener el color del cursor movil.
     * 
     * @return color para el cursor movil.
     */
    public Color getColorMovil()
    {
        return colorMovil;
    }

    /**
     * Permite indicar el color para el cursor movil.
     * 
     * @param colorMovil
     *            color para el cursor movil.
     */
    public void setColorMovil(Color colorMovil)
    {
        this.colorMovil = colorMovil;
    }
}
