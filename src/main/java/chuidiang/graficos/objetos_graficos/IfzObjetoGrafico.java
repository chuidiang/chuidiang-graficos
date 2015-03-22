/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

import chuidiang.graficos.InterfaceEscalaGrafica;

/**
 * Interface para todos los objetos gráficos para que se puedan meter en una
 * clase Lienzo.
 */
public interface IfzObjetoGrafico
{
    /**
     * Método al que se llamará cuando el objeto gráfico tenga que dibujarse. Se
     * le pasa la escala (El Graphics con unidades de usuario en vez de pixels)
     * sobre el que tiene que dibujarse el objeto gráfico.
     */
    public void dibujate(InterfaceEscalaGrafica escala);

    /** Devuelve si el objeto a modificado sus datos internos y por tanto
     * necesita que lo repinten
     * @return true si necesita repintado
     */
    public boolean necesitasRepintado();

    /**
     * Añade observador para avisarle de cuando se necesita repintado de este
     * objeto grafico
     * @param o El observador
     */
    public void addObservadorRepintado(ObservadorRepintado o);

    /**
     * Borra el observador
     * @param o El observador
     */
    public void removeObservadorRepintado(ObservadorRepintado o);
}
