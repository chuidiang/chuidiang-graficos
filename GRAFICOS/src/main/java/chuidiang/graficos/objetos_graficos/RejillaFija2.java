

/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */

package chuidiang.graficos.objetos_graficos;


import java.awt.*;
import java.awt.geom.*;

import java.text.DecimalFormat;

import chuidiang.graficos.InterfaceEscalaGrafica;


/**
 * Rejilla para un gráfico con ejes. La rejilla contiene un número de
 * divisiones fijas y equiespaciadas. No admite un número de divisiones menor
 * que dos. Permite cambiar el color de las líneas de la rejilla y de los
 * números que aparecen en cada división. Si a los números o a los ejes se les
 * pasa color null, simplemente no aparecen.
 */
public class RejillaFija2
        extends AbstractObjetoGrafico
{
   //~ Variables de instancia --------------------------------------------------

   /** Color para las divisiones. Por defecto rojo. */
   private Color colorEje = Color.RED;

   /** Color para los números de las divisiones. Por defecto cyan. */
   private Color colorNumero = Color.CYAN;

   /** Escala sobre la que se dibuja la rejilla */
   private InterfaceEscalaGrafica escala;

   /**
    * Array de divisiones en el eje x. Se recalcula cada vez que hay que
    * redibujar
    */
   private double[] x;

   /**
    * Array de divisiones sobre el eje y. Se recalcula cada vez que hay que
    * redibujar.
    */
   private double[] y;

   //~ Constructores -----------------------------------------------------------

   /**
    * Crea una rejilla con el número de filas y columnas que se le pasan como
    * paráemtro. Si el número de filas es menor que 2, se ponen dos filas Si
    * el número de columnas es menor que 2, se ponen dos columnas.
    *
    * @param x Array de divisiones en el eje y.<br>
    * @param y Array de divisiones en el eje x.<br>
    */
   public RejillaFija2( 
      double[] x,
      double[] y )
   {
      this.x = x;
      this.y = y;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Devuelve el color que se está usando para dibujar los números. Devuelve
    * null si previamente se pasó un color null
    *
    * @return Devuelve el color que se usa para dibujar números.<br>
    */
   public Color dameColorNumeros(  )
   {
      return colorNumero;
   }

   /**
    * Devuelve el color que se está usando para dibujar los ejes. Devuelve null
    * si previamente se pasó un color null.
    *
    * @return Devuelve el color que se usa para la rejilla.<br>
    */
   public Color dameColorRejilla(  )
   {
      return colorEje;
   }

   /**
    * Dibuja la rejilla sobre la escala que se le pasa.
    *
    * @param escala Dibuja la rejilla.<br>
    */
   public void dibujate( InterfaceEscalaGrafica escala )
   {
      this.escala = escala;
      dibujaVerticales(  );
      dibujaHorizontales(  );
      dibujaNumerosVerticales(  );
      dibujaNumerosHorizontales(  );
   }

   /**
    * Recoge los colores para los ejes y para los números. Si un color es null,
    * desaparecerán los ejes o los números en el siguiente repintado del
    * gráfico.
    *
    * @param colorRejilla Color para las líneas de la rejilla.<br>
    * @param colorNumeros Color para las etiquetas de la rejilla.<br>
    */
   public void tomaColores( 
      Color colorRejilla,
      Color colorNumeros )
   {
      this.colorEje = colorRejilla;
      this.colorNumero = colorNumeros;
      this.setNecesitoRepintado( true );
   }

   /**
    * Se le pasa el número de divisiones que se desean para cada eje. Si alguno
    * de los números es menor que dos, se ignora y se mantiene el número de
    * divisiones anterior para ese eje.
    *
    * @param x Divisiones en el eje x.<br>
    * @param y Divisiones en el eje y.<br>
    */
   public void tomaDivisiones( 
      double[] x,
      double[] y )
   {
      this.x = x;
      this.y = y;
      this.setNecesitoRepintado( true );
   }

   /**
    * Dibuja las divisiones horizontales, es decir, las divisiones del eje y Si
    * se le ha pasado un color de eje null, no dibuja nada.
    */
   private void dibujaHorizontales(  )
   {
      if( colorEje == null )
      {
         return;
      }

      Point2D[] eje = new Point2D[2];
      eje[0] = new Point2D.Double(  );
      eje[1] = new Point2D.Double(  );

      for( int i = 0;i < y.length;i++ )
      {
         escala.pintaEjeX( 
            y[i],
            colorEje );
      }
   }

   /**
    * Dibuja los números de la horizontal, es decir, las divisiones del eje x.
    * Si se le ha pasado un color de números null, no dibuja nada.
    */
   private void dibujaNumerosHorizontales(  )
   {
      if( colorNumero == null )
      {
         return;
      }

      Point2D.Double aux = new Point2D.Double(  );
      DecimalFormat formato = new DecimalFormat( "#.##" );

      int inicio = 0;

      if( y.length < x.length )
      {
         inicio = 1;
      }

      for( int i = inicio;i < x.length;i++ )
      {
         aux.setLocation( 
            x[i],
            y[0] );
         escala.pintaTexto( 
            aux,
            formato.format( x[i] ),
            colorNumero );
      }
   }

   /**
    * Dibuja los números de la vertical, es decir, las divisiones del eje y. Si
    * se le ha pasado un color de números null, no dibuja nada.
    */
   private void dibujaNumerosVerticales(  )
   {
      if( colorNumero == null )
      {
         return;
      }

      Point2D.Double aux = new Point2D.Double(  );
      DecimalFormat formato = new DecimalFormat( "#.##" );

      int inicio = 0;

      if( y.length >= x.length )
      {
         inicio = 1;
      }

      for( int i = inicio;i < y.length;i++ )
      {
         aux.setLocation( 
            x[0],
            y[i] );
         escala.pintaTexto( 
            aux,
            formato.format( y[i] ),
            colorNumero );
      }
   }

   /**
    * Dibuja los ejes verticales, es decir, las divisiones del eje x. Si se le
    * ha pasado un color de eje null, no dibuja nada.
    */
   private void dibujaVerticales(  )
   {
      if( colorEje == null )
      {
         return;
      }

      Point2D[] eje = new Point2D[2];
      eje[0] = new Point2D.Double(  );
      eje[1] = new Point2D.Double(  );

      for( int i = 0;i < x.length;i++ )
      {
         escala.pintaEjeY( 
            x[i],
            colorEje );
      }
   }
}
