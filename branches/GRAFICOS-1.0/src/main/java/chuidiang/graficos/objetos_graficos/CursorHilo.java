/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

import java.awt.Color;

import chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Cursor sobre un gráfico formado por una línea vertical. El cursor se mueve
 * con el ratón. Al hacer click con el botón izquierdo, se marca un cursor fijo
 * en la poisicón del ratón. Otro con el botón derecho.
 */
public class CursorHilo extends Cursor
{
    /**
     * Se le pasa si se quiere cursor vertical y si se quiere cursor horizontal
     * @param vertical true si se quiere cursor vertical.
     * @param horizontal true si se quiere cursor horizontal.
     */
    public CursorHilo(boolean vertical, boolean horizontal)
    {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    /**
     * Redibuja los cursores fijos y movil.
     */
    public void dibujate(InterfaceEscalaGrafica escala)
    {
        if (pintar)
        {
            dibujaLineaVertical(escala, x, y, this.colorMovil);
            dibujaLineaHorizontal(escala, x, y, this.colorMovil);
        }
        dibujaLineaVertical(escala, x1, y1, this.colorFijo);
        dibujaLineaHorizontal(escala, x1, y1, this.colorFijo);
        dibujaLineaVertical(escala, x2, y2, this.colorFijo);
        dibujaLineaHorizontal(escala, x2, y2, this.colorFijo);
    }

    /**
     * Dibuja una linea vertical que es el cursor.
     * 
     * @param escala
     *            La EscalaGrafica que hay que usar para dibujar el cursor.
     * @param x
     *            Posicion x en la que hay que dibujar el cursor
     * @param y
     *            Posicion y en la que hay que dibujar el cursor. Se ignora.
     * @param color
     *            Color del cursor.
     */
    private void dibujaLineaVertical(InterfaceEscalaGrafica escala, double x, double y,
            Color color)
    {
        if (!this.vertical)
            return;
        escala.pintaEjeY(x,color);
    }

    /**
     * Dibuja una linea horizontal que es el cursor.
     * 
     * @param escala
     *            La EscalaGrafica que hay que usar para dibujar el cursor.
     * @param x
     *            Posicion x en la que hay que dibujar el cursor. Se ignora
     * @param y
     *            Posicion y en la que hay que dibujar el cursor.
     * @param color
     *            Color del cursor.
     */
    private void dibujaLineaHorizontal(InterfaceEscalaGrafica escala, double x,
            double y, Color color)
    {
        if (!this.horizontal)
            return;
        escala.pintaEjeX(y,color);
    }

    /**
     * Indica si se quiere un hilo horizontal para el cursor.
     */
    private boolean horizontal;

    /**
     * Indica si se quiere un hilo vertical para el cursor.
     */
    private boolean vertical;
}
