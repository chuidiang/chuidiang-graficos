/**
 * Javier Abellán, 3 Oct 2006
 * 
 * Objeto gráfico arrastrable abstracto.
 */
package chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;

import chuidiang.graficos.objetos_graficos.AbstractObjetoGrafico;
import chuidiang.graficos.objetos_graficos.ObservadorRaton;

/**
 * Es un objeto gráfico abstracto con el código necesario para que se pueda
 * arrastar con el ratón.
 * Las clases hijas deben definir los métodos:
 * 
 * public boolean tomaNuevoOrigen(double x, double y)
 * 
 * En este método se le pasan las nuevas coordenadas donde debe dibujarse el
 * objeto gráfico. La clase hija únicamente debería en este método guardarse
 * las nuevas coordenadas, en espera que se llame a su método dibujate(). 
 * Debe devolver true si despés de la llamada a este método el objeto gráfico
 * necesita repintarse. Lo habitual es que devuelva false.
 * 
 * public boolean puedeComenzarArrastre(MouseEvent event, double x, double y)
 * 
 * Método al que se llama cuando se produce una pulsación del botón de ratón
 * sobre el gráfico. El objeto gráfico hijo debe decidir si esas coordenadas
 * caen sobre él para devolver true en caso de que considere que sí y que
 * puede comenzar a arrastrarse. false si considera que esas coordenadas
 * no caen sobre él y no debe arrastrarse.
 * 
 * public boolean finalizaElArrastre(MouseEvent e, double x, double y)
 * 
 * Método al que se llama cuando finaliza el arrastre. Normalmente los objetos
 * gráficos hijos no necesitan hacer nada en este método. Los objetos gráficos
 * hijos deben devolver true si necesitan repintado después de llamar a este
 * método. Lo habitual es que devuelvan false.
 * 
 * @author Chuidiang
 */
public abstract class AbstractObjetoGraficoArrastrable extends
		AbstractObjetoGrafico implements ObservadorRaton, IfzObjetoArrastrable
{
    /**
     * Clase con el código de arrastre de objetos.
     */
	private ArrastradorObjetos arrastradorObjetos;

    
    /**
     * Construye una instancia de esta clase.
     */
	public AbstractObjetoGraficoArrastrable()
	{
		arrastradorObjetos = new ArrastradorObjetos(this);
	}

    /**
     * Trata los eventos de ratón, decidiendo si el evento de ratón es de
     * inicio de arrastre, un arrastre o fin de arrastre, para llamar a los
     * métodos abstractos correspondientes.
     */
	public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
	{
		return arrastradorObjetos.eventoRaton(e, tipoEvento, x, y);
	}
}
