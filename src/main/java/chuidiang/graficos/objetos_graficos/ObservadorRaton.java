/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

import java.awt.event.MouseEvent;

/**
 * Interface común para todos los observadores de evento del ratón en un Lienzo.
 */
public interface ObservadorRaton
{
    /**
     * Método al que se llamará cuando se produzca un evento de ratón.
     * 
     * @param e
     *            El evento
     * @param tipoEvento
     *            Uno de los valores definidos en esta clase. Indica el tipo de
     *            evento.
     * @param x
     *            Posicion x en coordenadas de usuario donde se ha producido el
     *            evento de ratón.
     * @param y
     *            Posicion y en coordenadas de usuario donde se ha producido el
     *            evento de ratón.
     * @return true si el gráfico necesita repintado después de tratar el evento
     */
    public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y);

    /**
     * El ratón se mueve sobre el gráfico.
     */
    public final int MOVIMIENTO = 0;

    /**
     * Se ratón se mueve sobre el gráfico con el botón apretado.
     */
    public final int ARRASTRE = 1;

    /**
     * Se ha hecho click sobre el grafico.
     */
    public final int CLICK = 2;

    /**
     * Se ha pulsado el botón del ratón con el gráfico.
     */
    public final int PULSADO = 3;

    /**
     * Se ha soltado el botón del ratón en el gráfico.
     */
    public final int SOLTADO = 4;

    /**
     * El ratón entra en el Lienzo.
     */
    public final int ENTRA = 5;

    /**
     * El ratón sale del Lienzo
     */
    public final int SALE = 6;
}
