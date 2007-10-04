/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */
package chuidiang.graficos;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import java.util.LinkedList;

import javax.swing.JComponent;

import chuidiang.graficos.objetos_graficos.IfzObjetoGrafico;
import chuidiang.graficos.objetos_graficos.ObservadorRaton;

/**
 * Capa de dibujo para el cursor.<br>
 * Va situada sobre la capa de dibujo y es la que captura eventos de raton
 */
public class CapaCursor extends JComponent
{
	// ~ Variables/Inicializadores estaticos
	// -------------------------------------

	/** serial uid */
	private static final long serialVersionUID = -492814228896208561L;

	// ~ Variables de instancia
	// --------------------------------------------------

	/** Escala de dibujo */
	private InterfaceEscalaGrafica escala = null;

	/** Lista de observadores de ratón. */
	private LinkedList<ObservadorRaton> observadoresRaton = new LinkedList<ObservadorRaton>();

	// ~ Constructores
	// -----------------------------------------------------------

	/**
	 * Crea un nuevo objeto de la clase CapaCursor.<br>
	 */
	public CapaCursor()
	{
		// Se suscribe a movimiento de ratón.
		this.addMouseMotionListener(new MouseMotionListener()
		{
			public void mouseDragged(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.ARRASTRE);
			}

			public void mouseMoved(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.MOVIMIENTO);
			}
		});

		// Se suscribe a pulsaciones de ratón.
		this.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.CLICK);
			}

			public void mouseEntered(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.ENTRA);
			}

			public void mouseExited(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.SALE);
			}

			public void mousePressed(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.PULSADO);
			}

			public void mouseReleased(MouseEvent arg0)
			{
				avisaSuscriptoresRaton(arg0, ObservadorRaton.SOLTADO);
			}
		});
	}

	// ~ Metodos
	// -----------------------------------------------------------------

	/**
	 * Se le pasa y guarda la escala gráfica.<br>
	 * 
	 * @param escala
	 *            La escala de dibujo.<br>
	 */
	public void setEscala(InterfaceEscalaGrafica escala)
	{
		this.escala = escala;
	}

	/**
	 * Devuelve la escala de dibujo que está usando.<br>
	 * 
	 * @return La escala.<br>
	 */
	public InterfaceEscalaGrafica getEscala()
	{
		return escala;
	}

	/**
	 * Añade el observador a la lista de observadores de evento de ratón.
	 * 
	 * @param observador
	 *            El observador a añadir en la lista.
	 */
	public void anhadeObservadorRaton(ObservadorRaton observador)
	{
		this.observadoresRaton.add(observador);
	}

	/**
	 * Elimina al observador de la lista de observadores de evento de ratón.
	 * 
	 * @param observador
	 *            El observador a eliminar de la lista.
	 */
	public void borraObservadorRaton(ObservadorRaton observador)
	{
		this.observadoresRaton.remove(observador);
	}

	/**
	 * Comprueba si los observadores de raton implementan la interface objeto
	 * gráfico y llama a sus dibujate() si es así, dando posibilidad de dibujar
	 * cursores en la capa de raton.<br>
	 * 
	 * @param g
	 *            El graphics de este componente.<br>
	 */
	public void paint(Graphics g)
	{
		this.escala.tomaGraphics(g, getWidth(), getHeight());

		for (int i = 0; i < this.observadoresRaton.size(); i++)
		{
			ObservadorRaton obs = (ObservadorRaton) this.observadoresRaton
					.get(i);

			if (obs instanceof IfzObjetoGrafico)
			{
				((IfzObjetoGrafico) obs).dibujate(this.escala);
			}
		}
	}

	/**
	 * Avisa a los suscriptores de eventos con el ratón.
	 * 
	 * @param e
	 *            El evento del ratón
	 * @param tipoEvento
	 *            Un entero para indicar el tipo de evento. Puede valer
	 *            cualquiera de los valores definidos en ObservadorRaton
	 */
	private void avisaSuscriptoresRaton(MouseEvent e, int tipoEvento)
	{
		// Se transforma la x,y en pixels del evento de ratón en x,y de
		// coordenadas de usuario
		Point2D coordenadasRaton = new Point2D.Double();
		coordenadasRaton.setLocation(e.getX(), e.getY());

		Point2D coordenadasUsuario = this.escala
				.dameCoordenadaUsuario(coordenadasRaton);

		boolean repintadoGlobal = false;

		// Se avisa a los suscriptores.
		for (int i = 0; i < this.observadoresRaton.size(); i++)
		{
			repintadoGlobal = repintadoGlobal
					| ((ObservadorRaton) this.observadoresRaton.get(i))
							.eventoRaton(e, tipoEvento, coordenadasUsuario
									.getX(), coordenadasUsuario.getY());
		}

		if (repintadoGlobal)
		{
			this.repaint();
		}
	}
}
