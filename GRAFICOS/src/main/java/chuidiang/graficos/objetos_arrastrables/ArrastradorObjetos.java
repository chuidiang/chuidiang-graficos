package chuidiang.graficos.objetos_arrastrables;

import java.awt.event.MouseEvent;
import chuidiang.graficos.objetos_graficos.ObservadorRaton;

/**
 * Clase que se encarga de tratar los eventos de ratón para decidir cuando
 * empiza o no un arrastre de ratón, de forma que:
 * - Le pregunta al objeto arrastrable si se puede o no empezar un arrastre.
 * - Avisa al objeto arrastrable del arrastre.
 * - Avisa al objeto arrastrable de cuando termina el arrastre.
 * 
 * @author Chuidiang
 *
 */

public class ArrastradorObjetos implements ObservadorRaton
{
	/**
	 * Objeto que se arrastrará con el movimiento del ratón.
	 */
	private IfzObjetoArrastrable objetoArrastrable;

	public ArrastradorObjetos(IfzObjetoArrastrable objetoArrastrable)
	{
		this.objetoArrastrable = objetoArrastrable;
	}

	/**
	 * Método al que se llama cuando se produce un evento de ratón en el
	 * pintado.
	 */
	public boolean eventoRaton(MouseEvent e, int tipoEvento, double x, double y)
	{
		if (!arrastrando)
		{
            preparaPosibleArrastreSiProcede(e, tipoEvento, x, y);
            return comienzaElArrastre(tipoEvento, x, y);
		} 
		else
        {
            return arrastraOTerminaElArrastre(e, tipoEvento, x, y);
        }
	}

    private boolean arrastraOTerminaElArrastre(MouseEvent e, int tipoEvento, double x, double y)
    {
        if (tipoEvento == ObservadorRaton.ARRASTRE)
        {
        	return objetoArrastrable.tomaNuevoOrigen(x, y);
        } 
        else
        {
        	boolean retorno = objetoArrastrable.finalizaElArrastre(e, x, y);
            posibleArrastre=false;
        	arrastrando = false;
            numeroObjetosArrastrandose--;
        	return retorno;
        }
    }

    /**
     * Todavía no se está arrastrando ningún objeto, pero llega un evento
     * de arrastre de ratón. Si todo estaba preparado para el arrastre, se
     * comienza a arrastrar el objeto.
     * @param tipoEvento Tipo de evento según ObservadorRaton
     * @param x x del evento en coordenadas de usuario
     * @param y y del evento en coordenadas de usuario
     * @return true si el objeto necesita repintado.
     */
    private boolean comienzaElArrastre(int tipoEvento, double x, double y)
    {
        if (posibleArrastre==true)
        {
        	if (tipoEvento == ObservadorRaton.ARRASTRE)
        	{
                posibleArrastre=false;
        		arrastrando = true;
        		return objetoArrastrable.tomaNuevoOrigen(x, y);
        	}
        	else if (tipoEvento != ObservadorRaton.PULSADO)
            {
                posibleArrastre=false;
                numeroObjetosArrastrandose--;
            }
        }
        return false;
    }

    /**
     * Si se acaba de pulsar el ratón sobre el gráfico y algún objeto 
     * arrastrable se siente "aludido" para arrastrarse, se preparan
     * internamente todas las variables para un posible arrastre de 
     * objeto gráfico.
     * @param e El evento de ratón.
     * @param tipoEvento El tipo de evento según ObservadorRaton.
     * @param x x del evento en coordendadas de usuario.
     * @param y y del evento en coordenadas de usuario.
     */
    private void preparaPosibleArrastreSiProcede(MouseEvent e, int tipoEvento, double x, double y)
    {
        if (tipoEvento == ObservadorRaton.PULSADO)
            if (numeroObjetosArrastrandose<numeroMaximoObjetosArrastradosSimultaneamente)
        		if (objetoArrastrable.puedeComenzarArrastre(e,x,y))
        		{
        			posibleArrastre=true;
                    numeroObjetosArrastrandose++;
        		}
    }

	/** Si el arrastre está en marcha. */
	private boolean arrastrando = false;
    
    /** Si se ha hecho click y el objeto arrastrable confirma que se puede
     * arrastrar, pero todavía no ha comenzado el arrastre */
    private boolean posibleArrastre=false;
    
    /** Numero máximo de objetos que se puede arrastrar simultaneamente */
    private static int numeroMaximoObjetosArrastradosSimultaneamente=1;
    
    /** Numero objetos arrastrandose en ese momento */
    private static int numeroObjetosArrastrandose=0;

    /**
     * Devuelve el número máximo de objetos que se pueden arrastrar a la vez.
     * 
     * @return número máximo de objetos arrastrables simultáneamente.
     */
	public static int getNumeroMaximoObjetosArrastradosSimultaneamente()
	{
		return numeroMaximoObjetosArrastradosSimultaneamente;
	}

    /**
     * Número máximo de objetos que se pueden arrastrar simultáneamente.
     * Por defecto es 1. Si se cambia este valor por encima de 1, es posible
     * que dos objetos que estén uno encima de otro sean imposibles de separar,
     * puesto que al arrastrar, ambos comenzarán a arrastrarse.
     * 
     * @param numeroMaximoObjetosArrastradosSimultaneamente
     */
	public static void setNumeroMaximoObjetosArrastradosSimultaneamente(
			int numeroMaximoObjetosArrastradosSimultaneamente)
	{
		ArrastradorObjetos.numeroMaximoObjetosArrastradosSimultaneamente = numeroMaximoObjetosArrastradosSimultaneamente;
	}
}
