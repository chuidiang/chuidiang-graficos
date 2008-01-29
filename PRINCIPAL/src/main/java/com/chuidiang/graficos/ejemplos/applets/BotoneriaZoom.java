/*
 * BotoneriaZoom.java
 *
 * Created on 1 de mayo de 2004, 18:31
 */

package com.chuidiang.graficos.ejemplos.applets;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.chuidiang.graficos.InterfaceEscalaGrafica;
import com.chuidiang.graficos.botones.ZoomPorcentaje;
/**
 *
 * @author  chuidiang
 */
public class BotoneriaZoom extends JPanel
{
    
    /**
	 * serial uid
	 */
	private static final long serialVersionUID = 3618134559091275056L;

	/** Creates a new instance of BotoneriaZoom */
    public BotoneriaZoom(InterfaceEscalaGrafica escala) 
    {
        super (new FlowLayout());
        ZoomPorcentaje acercar = new ZoomPorcentaje (escala, 10);
        acercar.setText ("+");
        ZoomPorcentaje alejar = new ZoomPorcentaje (escala, -10);
        alejar.setText ("-");
        
        add (acercar);
        add (alejar);
    }
    
}
