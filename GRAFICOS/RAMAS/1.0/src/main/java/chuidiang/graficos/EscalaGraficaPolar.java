/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import java.util.LinkedList;

import chuidiang.matematicas.CartesianasPolares;

/**
 * Clase encargada de transformar coordenadas de usuario en pixels y viceversa
 * en gráficos con coordenadas polares.<br>
 */
public class EscalaGraficaPolar implements InterfaceEscalaGrafica
{
	// ~ Variables de instancia
	// --------------------------------------------------

	/** Los polares se convierten a cartesiana y se delega en esta clase */
	protected EscalaGraficaCartesiana escala = new EscalaGraficaCartesiana();

	/** Observadores de cambios en los extremos del gráfico */
	private LinkedList<ObservadorEscalaGrafica> observadores = new LinkedList<ObservadorEscalaGrafica>();

	// ~ Constructores
	// -----------------------------------------------------------

	/**
	 * Añade un observador a la lista d eobservadores para avisarle cuando haya
	 * cambio en los extremos del gráfico.<br>
	 * 
	 * @param unObservador
	 *            El observador.<br>
	 */
	public void anhadeObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.add(unObservador);
	}

	/**
	 * Devuelve las coordenadas de usuario de un punto expresado en pixels.<br>
	 * Ütil para transformar coordenadas de un evento de ratón en coordenadas de
	 * usuario.
	 * 
	 * @param punto
	 *            Punto con coordenadas en pixels.<br>
	 * @return Punto con coordenadas de usuario.<br>
	 */
	public Point2D dameCoordenadaUsuario(Point2D punto)
	{
		Point2D pUsuario = escala.dameCoordenadaUsuario(punto);
		Point2D pUsuarioPolar = new Point2D.Double();
		pUsuarioPolar.setLocation(CartesianasPolares.dameModulo(
				pUsuario.getX(), pUsuario.getY()), CartesianasPolares
				.dameAngulo(pUsuario.getX(), pUsuario.getY()));

		return pUsuarioPolar;
	}

	/**
	 * Devuelve el area de dibujo en coordenadas polares de usuario.<br>
	 * 
	 * @return Rectangulo de dibujo.<br>
	 */
	public Rectangle2D dameExtremosCartesianos()
	{
		return escala.dameExtremosCartesianos();
	}

	/**
	 * Elimina el observador que se le pasa de la lista de observadores.<br>
	 * Ya no le avisará de cambios en los límites del dibujo
	 * 
	 * @param unObservador
	 *            El observador a eliminar.<br>
	 */
	public void eliminaObservador(ObservadorEscalaGrafica unObservador)
	{
		observadores.remove(unObservador);
	}

	/**
	 * Dibuja un arco.<br>
	 * El arco pertenece a una circunferencia que va inscrita en un rectángulo
	 * @param esquina
	 *            Esquina del rectángulo.<br>
	 * @param ancho
	 *            Ancho del rectángulo.<br>
	 * @param alto
	 *            Alto del rectángulo.<br>
	 * @param arcoInicial
	 *            Angulo inicial del arco, en radianes.<br>
	 * @param arcoFinal
	 *            Angulo final del arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArco(Point2D centro, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{

		Point2D centroCartesiano = new Point2D.Double();
		centroCartesiano.setLocation(CartesianasPolares.dameX(centro.getX(),
				centro.getY()), CartesianasPolares.dameY(centro.getX(), centro
				.getY()));
		this.escala.pintaArco(centroCartesiano, ancho, alto, arcoInicial,
				arcoFinal, color);
	}

	/**
	 * Dibuja un arco relleno.<br>
	 * El arco pertenece a una circunferencia que va inscrita en un rectángulo
	 * @param esquina
	 *            Esquina del rectángulo.<br>
	 * @param ancho
	 *            Ancho del rectángulo.<br>
	 * @param alto
	 *            Alto del rectángulo.<br>
	 * @param arcoInicial
	 *            Angulo inicial del arco, en radianes.<br>
	 * @param arcoFinal
	 *            Angulo final del arco, en radianes.<br>
	 * @param color
	 *            Color del arco.<br>
	 */
	public void pintaArcoRelleno(Point2D centro, double ancho, double alto,
			double arcoInicial, double arcoFinal, Color color)
	{
		Point2D centroCartesiano = new Point2D.Double();
		centroCartesiano.setLocation(CartesianasPolares.dameX(centro.getX(),
				centro.getY()), CartesianasPolares.dameY(centro.getX(), centro
				.getY()));
		this.escala.pintaArcoRelleno(centroCartesiano, ancho, alto,
				arcoInicial, arcoFinal, color);
	}

	/**
	 * Dibuja un eje de las x (radio) en la coordenada y (con angulo determinado).<br>
	 * 
	 * @param y
	 *            Coordenada y para dibujar el eje.<br>
	 * @param color
	 *            Color del eje.<br>
	 */
	public void pintaEjeX(double y, Color color)
	{
		Rectangle2D extremos = dameExtremosCartesianos();
		Point2D.Double[] eje = new Point2D.Double[2];
		eje[0] = new Point2D.Double(0, y);
		eje[1] = new Point2D.Double(extremos.getWidth(), y);
		pintaPoliLinea(eje, color);
	}

	/**
	 * Pinta el eje de los angulos, es decir un circulo del radio x del centro
	 * que se pasa.<br>
	 * El color
	 * 
	 * @param x
	 *            Radio para dibujar el círculo.<br>
	 * @param color
	 *            Color del círculo.<br>
	 */
	public void pintaEjeY(double x, Color color)
	{
		Point2D centro = new Point2D.Double(
				CartesianasPolares.dameModulo(x, x), 5 * Math.PI / 4.0);
		pintaArco(centro, 2 * x, 2 * x, 0.0, 360.0, color);
	}

	/**
	 * Dibuja una polígono abierto, uniendo consecutivamente los puntos de
	 * puntos.<br>
	 * 
	 * @param puntos
	 *            Puntos del polígono.<br>
	 * @param color
	 *            Color para el polígono.<br>
	 */
	public void pintaPoliLinea(Point2D[] puntos, Color color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPoliLinea(pCartesianos, color);
	}

	/**
	 * Dibuja los puntos que se le pasan.<br>
	 * 
	 * @param puntos
	 *            Los puntos.<br>
	 * @param color
	 *            Color de cada uno de los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color[] color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPuntos(pCartesianos, color);
	}

	/**
	 * Pinta puntos de un mismo color.<br>
	 * 
	 * @param puntos
	 *            Los puntos.<br>
	 * @param color
	 *            Color para los puntos.<br>
	 */
	public void pintaPuntos(Point2D[] puntos, Color color)
	{
		Point2D[] pCartesianos = new Point2D.Double[puntos.length];

		for (int i = 0; i < puntos.length; i++)
		{
			pCartesianos[i] = new Point2D.Double(CartesianasPolares.dameX(
					puntos[i].getX(), puntos[i].getY()), CartesianasPolares
					.dameY(puntos[i].getX(), puntos[i].getY()));
		}

		escala.pintaPuntos(pCartesianos, color);
	}

	/**
	 * Pinta un rectángulo relleno.<br>
	 * 
	 * @param esquina
	 *            esquina inferior izquierda del rectángulo.<br>
	 * @param ancho
	 *            Ancho del rectángulo.<br>
	 * @param alto
	 *            Alto del rectángulo.<br>
	 * @param color
	 *            Color del rectángulo.<br>
	 */
	public void pintaRectanguloRelleno(Point2D esquina, double ancho,
			double alto, Color color)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Pinta un rectángulo relleno en 3D.<br>
	 * 
	 * @param esquina
	 *            Esquina inferior izquierda del rectángulo.<br>
	 * @param ancho
	 *            Ancho del rectángulo.<br>
	 * @param alto
	 *            Alto del rectángulo.<br>
	 * @param color
	 *            Color del rectángulo.<br>
	 * @param elevado
	 *            COMENTARIO.<br>
	 */
	public void pintaRectanguloRelleno3D(Point2D esquina, double ancho,
			double alto, Color color, boolean elevado)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * Dibuja un texto en la posición indicada.<br>
	 * 
	 * @param posicion
	 *            Posición del texto.<br>
	 * @param texto
	 *            El texto.<br>
	 * @param color
	 *            Color.<br>
	 */
	public void pintaTexto(Point2D posicion, String texto, Color color)
	{
		Point2D.Double posicionTexto = new Point2D.Double();
		posicionTexto.setLocation(CartesianasPolares.dameX(posicion.getX(),
				posicion.getY()), CartesianasPolares.dameY(posicion.getX(),
				posicion.getY()));
		escala.pintaTexto(posicionTexto, texto, color);
	}

	/**
	 * Se le pasan los límites para el gráfico polar.<br>
	 * 
	 * @param rMin
	 *            Radio mínimo que se quiere dibujar.<br>
	 * @param tMin
	 *            Angulo mínimo en radianes.<br>
	 * @param rMax
	 *            Radio máximo.<br>
	 * @param tMax
	 *            Angulo máximo en radianes.<br>
	 */
	public void tomaExtremos(double xMin, double yMin, double xMax, double yMax)
	{
		this.escala.tomaExtremos(xMin, yMin, xMax, yMax);
		this.avisaObseradores(xMin, yMin, xMax, yMax);
	}

	/**
	 * Se le pasa el Graphics sobre el que va a dibujar, así como el
	 * alto y ancho en pixels del área de dibujo.<br>
	 * 
	 * @param g
	 *            Graphics para dibujar.<br>
	 * @param ancho
	 *            Ancho en pixels del área de dibujo.<br>
	 * @param alto
	 *            Alto en pixels del área de dibujo.<br>
	 */
	public void tomaGraphics(Graphics g, int ancho, int alto)
	{
		escala.tomaGraphics(g, ancho, alto);
	}

	/**
	 * Avisa a los observadores de cambio en los límites del gráfico de que
	 * se ha producido un cambio en dichos límites.<br>
	 * 
	 * @param xMin
	 *            Nueva x min.<br>
	 * @param yMin
	 *            Nueva y min.<br>
	 * @param xMax
	 *            Nueva x max.<br>
	 * @param yMax
	 *            Nueva y max.<br>
	 */
	private void avisaObseradores(double xMin, double yMin, double xMax,
			double yMax)
	{
		for (int i = 0; i < observadores.size(); i++)
		{
			((ObservadorEscalaGrafica) observadores.get(i)).tomaNuevosExtremos(
					xMin, yMin, xMax, yMax);
		}
	}

    /**
     * Dibuja la imagen que se le pasa en la posición indicada.
     * 
     * @param posicion La posición para dibujar la imágen
     * @param imagen La imagen a dibujar
     */
	public void pintaImagen(Point2D posicion, Image imagen)
	{
		if (posicion == null)
			return;
		Point2D.Double posicionImagen = new Point2D.Double();
		posicionImagen.setLocation(CartesianasPolares.dameX(posicion.getX(),
				posicion.getY()), CartesianasPolares.dameY(posicion.getX(),
				posicion.getY()));
		this.escala.pintaImagen(posicionImagen, imagen);
	}

    /**
     * Se le pasan unas coordenadas polares y devuelve las cartesianas adecudas
     */
	public Point2D dameCoordenadasCartesianas(Point2D puntoPolar)
	{
        Point2D puntoCartesiano = new Point2D.Double();
        puntoCartesiano.setLocation(
                CartesianasPolares.dameX(puntoPolar.getX(),puntoPolar.getY()),
                CartesianasPolares.dameY(puntoPolar.getX(),puntoPolar.getY()));
        return puntoCartesiano;
	}

    /**
     * Devuelve los extremos polares del dibujo.
     * La xmin es el radio más pequeño (que puede ser cero o más). La ymin
     * siempre es 0.0 y la más grande 2*PI.
     */
	public Rectangle2D dameExtremos()
	{
		Rectangle2D.Double resultado = new Rectangle2D.Double();
		Rectangle2D cartesianos = this.dameExtremosCartesianos();
		if ((cartesianos.getX()<0) && 
				((cartesianos.getX()+cartesianos.getWidth())>0) &&
				(cartesianos.getY()<0) &&
				((cartesianos.getY()+cartesianos.getHeight()>0)))
		{
			// El centro está dentro del gráfico
			resultado.x=0.0;
			resultado.width=Math.max(cartesianos.getWidth(),cartesianos.getHeight());
			resultado.y=0.0;
			resultado.height=2.0*Math.PI;
		}
		else
		{
			// Esta fuera
			double min = Math.abs(cartesianos.getX());
			min = Math.min(min, Math.abs(cartesianos.getX()+cartesianos.getWidth()));
			min = Math.min(min, Math.abs(cartesianos.getY()));
			min = Math.min(min, Math.abs(cartesianos.getY()+cartesianos.getHeight()));
			double max = Math.abs(cartesianos.getX());
			max = Math.max(max, Math.abs(cartesianos.getX()+cartesianos.getWidth()));
			max = Math.max(max, Math.abs(cartesianos.getY()));
			max = Math.max(max, Math.abs(cartesianos.getY()+cartesianos.getHeight()));
			resultado.x=0.0;
			resultado.width=max-min;
			resultado.y=0.0;
			resultado.height=2*0*Math.PI;
		}
		return resultado;	}
}
