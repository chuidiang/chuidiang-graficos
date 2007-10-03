/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;

/**
 * Interface para observadores de necesidad de repintado de un objeto grafico.
 */
public interface ObservadorRepintado
{
    /**
     * Método al que se llama cuando el objeto necesita repintado
     */
    public void necesitoRepintado();
}
