/**
 * Javier Abellán, 11 Junio 2006
 * 
 * Parte de la librería gráfica
 */
package chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;

/**
 * Interface que deben implementar aquellos objetos gráficos que sean
 * arrastables por el ratón.
 * La clase ArrastradorObjetos admite objetos con esta interface y se encarga
 * de indicarles cómo moverse en función del arrastre del ratón.
 * 
 * @author Chuidiang
 */
public interface IfzObjetoArrastrable 
{
    /**
     * El objeto gráfico recibe la posición en la que debe dibujarse.
     * @param x La nueva x a partir de la que debe dibujarse
     * @param y La nueva y a partri de la que debe dibujarse
     * @return si el objeto necesita repintado
     */
    public boolean tomaNuevoOrigen(double x, double y);
    
    /**
     * El objeto gráfico devuelve true si puede empezar a arrastrarse.
     * A esta clase se le pasará el evento de ratón que ha tenido lugar y la
     * clase indica si comienza a arrastrarse. Por ejemplo, porque se comienza
     * un arrastre en la posición x,y en la que está ella.
     * @param event evento del ratón
     * @param x La x del ratón en coordenadas de usuario
     * @param y la y del ratón en coordenadas de usuario
     * @return Si se debe comenzar el arrastre.
     */
    public boolean puedeComenzarArrastre(MouseEvent event, double x, double y);

    /**
     * Se avisa al objeto gráfico arrastrable de que ha finalizado el arrastre
     * del ratón.
     * @param e El evento de ratón.
     * @param x La x donde finaliza el arrastre (coordenadas de usuario)
     * @param y La y donde finaliza el arrastre (coordenadas de usuario).
     * @return true si el objeto necesita repintado, false en caso contrario.
     */
	public boolean finalizaElArrastre(MouseEvent e, double x, double y);
}
