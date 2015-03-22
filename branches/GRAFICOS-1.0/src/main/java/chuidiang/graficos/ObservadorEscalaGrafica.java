/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */


package chuidiang.graficos;

/**
 * Observador de cambio de coordenadas de usuario en una EscalaGrafica.
 */
public interface ObservadorEscalaGrafica
{
    
    /**
     * Se llamará a este método cuando cambien las coordenadas de usuario de una
     * EscalaGrafica.
     * 
     * @param xMin x minima en coordenadas de usuario para el gráfico
     * @param yMin la y mínima
     * @param xMax la x máxima
     * @param yMax la y máxima
     */
    public void tomaNuevosExtremos(double xMin, double yMin, double xMax,
            double yMax);
}
