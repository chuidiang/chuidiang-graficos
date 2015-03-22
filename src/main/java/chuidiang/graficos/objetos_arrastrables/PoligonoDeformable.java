/**
 * Javier Abellán, 3 Junio 2006
 * 
 * Poligono para pintar en un lienzo gráfico en el que se pueden arrastrar los
 * vértices con el ratón.
 */
package chuidiang.graficos.objetos_arrastrables;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import chuidiang.graficos.InterfaceEscalaGrafica;

/**
 * Poligono para pintar en un lienzo gráfico en el que se pueden arrastrar los
 * vértices con el ratón.
 * 
 * @author Chuidiang
 *
 */
public class PoligonoDeformable extends AbstractObjetoGraficoArrastrable
{
    /**
     * Construye un poligono abierto cuyos vértices son los puntos que se le
     * pasan. Los puntos no pueden ser null y debe haber al menos 1, si no
     * saltarán excepciones.
     * @param puntos Los vértices
     */
	public PoligonoDeformable(Point2D[] puntos)
	{
		setPuntos(puntos);
	}

    /**
     * Construye un polígono abierto cuyos vértices son las x,y que se pasan.
     * Los arrays no pueden ser null, deben tener al menos un elemento y deben
     * ser del mismo tamaño. Si no es así, saltarán excepciones.
     * @param x Las x de los vértices
     * @param y Las y de los vértices.
     */
	public PoligonoDeformable(double[] x, double[] y)
	{
		setPuntos(x, y);
	}

    /** Indice del punto que se está arrastrando */
	private int indicePuntoArrastrado = 0;

    /**
     * Devuelve una pseudo-distancia entre una x,y del ratón y el punto de posicion
     * indicePunto en el array.
     * @param x del ratón
     * @param y del ratón.
     * @param indicePunto Indice en el array de puntos
     * @return Una pseudodistancia
     */
	private double dameDistancia(double x, double y, int indicePunto)
	{
		return (Math.abs(x - puntos[indicePunto].getX()) + Math.abs(y
				- puntos[indicePunto].getY()));
	}

    /**
     * Devuelve el indice del vértice más cercano a la x,y que se le pasa.
     * @param x La x,
     * @param y la y
     * @return El indice en el array del vértice más cercano.
     */
	private int dameIndicePuntoMasCercano(double x, double y)
	{
		double distancia = dameDistancia(x, y, 0);
		int indiceMasCercano = 0;
		for (int i = 1; i < puntos.length; i++)
		{
			double distanciaAux = dameDistancia(x, y, i);
			if (distanciaAux < distancia)
			{
				distancia = distanciaAux;
				indiceMasCercano = i;
			}
		}
		return indiceMasCercano;
	}

    /**
     * Dibuja el polígono.
     */
	public void dibujate(InterfaceEscalaGrafica escala)
	{
		escala.pintaPoliLinea(puntos, color);
		if (cerrado)
		{
			Point2D[] aux = new Point2D[2];
			aux[0] = puntos[0];
			aux[1] = puntos[puntos.length - 1];
			escala.pintaPoliLinea(aux, color);
		}

	}
    
	/**
	 * Devuelve el array de vértices.
	 * @return vértices
	 */
	public Point2D[] getPuntos()
	{
		return puntos;
	}

    /**
     * Guarda los puntos que se le pasan y marca que necesita repintado
     * @param puntos vértices del polígono.
     */
	public void setPuntos(Point2D[] puntos)
	{
		this.puntos = puntos;
        this.setNecesitoRepintado(true);
	}

	/**
	 * Reemplaza los puntos guardados por los que se le pasan. Los arrays no
	 * pueden ser null y deben ser del mismo tamaño.
	 * 
	 * @param x Las x de los vértices
	 * @param y Las y de los vértices.
	 */
	public void setPuntos(double[] x, double[] y)
	{
		Point2D[] aux = new Point2D[x.length];
		for (int i = 0; i < x.length; i++)
			aux[i] = new Point2D.Double(x[i], y[i]);
		setPuntos(aux);
	}

    /**
     * Devuelve si el polígono es cerrado.
     * @return true si el polígono es cerrado.
     */
	public boolean isCerrado()
	{
		return cerrado;
	}

    /**
     * Hace que se cierre el poligono. A la hora de dibujarlo, se dibujará una
     * línea que una el último vértice con el primero.
     * @param cerrado
     */
	public void setCerrado(boolean cerrado)
	{
		this.cerrado = cerrado;
	}

    /**
     * Devuelve el color del polígono
     * @return color del polígono
     */
	public Color getColor()
	{
		return color;
	}

    /**
     * Fija el color del polígono
     * @param color
     */
	public void setColor(Color color)
	{
		this.color = color;
        setNecesitoRepintado(true);
	}

    /**
     * Devuelve si el polígono se puede deformar arrastrando los vértices con
     * el ratón.
     * @return Si se puede deformar arrastrando.
     */
	public boolean isDeformable()
	{
		return deformable;
	}

    /**
     * hace que el polígono se pueda deformar arrastrando los vértices con el
     * ratón.
     * @param deformable true si se quiere que sea deformable.
     */
	public void setDeformable(boolean deformable)
	{
		this.deformable = deformable;
	}

    /**
     * Devuelve la pseudo-distancia mínima para considerar que el ratón pincha
     * sobre un vértice.
     * @return La pseudo-distancia mínima.
     */
	public double getDistanciaMinimaArrastre()
	{
		return distanciaMinimaArrastre;
	}

    /**
     * Fija la pseudo-distancia mínima para considerar que el ratón pincha sobre
     * un vértice.
     * @param distanciaMinimaArrastre Distancia mínima.
     */
	public void setDistanciaMinimaArrastre(double distanciaMinimaArrastre)
	{
		this.distanciaMinimaArrastre = distanciaMinimaArrastre;
	}

    /** Si el polígono es cerrado */
	private boolean cerrado = false;

    /** Si se puede deformar con el ratón */
	private boolean deformable = false;

    /** Color del polígono */
	private Color color = Color.YELLOW;

    /** Vértices del polígono */
	private Point2D[] puntos = null;

    /** Pseudo-distancia para considerar que el ratón pincha sobre el vértice */
	private double distanciaMinimaArrastre = 0.5;

	public boolean tomaNuevoOrigen(double x, double y)
	{
        puntos[indicePuntoArrastrado].setLocation(x,y);
		return true;
	}

	public boolean puedeComenzarArrastre(MouseEvent event, double x, double y)
	{
		indicePuntoArrastrado = dameIndicePuntoMasCercano(x, y);
		double distancia = dameDistancia(x, y, indicePuntoArrastrado);
		if (distancia < distanciaMinimaArrastre)
        {
            return true;
        }
		return false;
	}

	public boolean finalizaElArrastre(MouseEvent e, double x, double y)
	{
        return false;
	}
}
