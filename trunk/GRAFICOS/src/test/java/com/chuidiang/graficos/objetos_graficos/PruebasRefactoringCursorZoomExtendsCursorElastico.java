/*==============================================================================
*
* $RCSfile: cvsmkhd,v $
*
* $Revision: 1.2 $ $Date: 2003/09/22 07:05:39 $
*
* $State: Exp $
*
* AUTOR: sutruk
*
==============================================================================*/


package com.chuidiang.graficos.objetos_graficos;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.chuidiang.graficos.EscalaGraficaCartesiana;
import com.chuidiang.graficos.Lienzo;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorElastico;
import com.chuidiang.graficos.objetos_arrastrables.cursores.CursorZoom;


/**
 *  .<br>
 *
  */
public class PruebasRefactoringCursorZoomExtendsCursorElastico
{
   //~ Metodos -----------------------------------------------------------------

   /**
    * Main para probar el cursor elastico y el cursor de zoom
    *
    * @param args
    */
   public static void main( String[] args )
   {
      //Lienzo
      Lienzo lienzo = new Lienzo(  );
      lienzo.setBackground( Color.BLACK );

      //Escala grafica
      EscalaGraficaCartesiana escalaGraficaCartesiana =
         new EscalaGraficaCartesiana(  );
      escalaGraficaCartesiana.tomaExtremos( 
         0,
         0,
         500,
         500 );

      lienzo.tomaEscala( escalaGraficaCartesiana );

      //Objetos graficos de interes
      CursorElastico cursorElastico = new CursorZoom(  );
      cursorElastico.setColorFijo(Color.GREEN);

      lienzo.anhadeObservadorRaton( cursorElastico );

      RejillaFija rej = new RejillaFija( 10,10 );
      rej.tomaColores( 
         Color.GRAY,
         Color.GREEN );
      lienzo.tomaObjetoGrafico( rej );

      Mano mano = new Mano( escalaGraficaCartesiana,lienzo );
      lienzo.anhadeObservadorRaton( mano );

      //Contenedor del lienzo
      JFrame jframe = new JFrame(  );

      jframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      jframe.getContentPane(  ).add( lienzo );

      jframe.setSize( new Dimension( 
            400,
            400 ) );
      jframe.setVisible( true );


   }
}
