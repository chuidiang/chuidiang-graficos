/**
 * Javier Abellán. 6 Abril 2006
 * 
 * Librería gráfica
 */


package chuidiang.graficos.objetos_graficos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import chuidiang.graficos.InterfaceEscalaGrafica;



/**
 * Pulsando el tercer botón del ratón y arrastrando, se puede mover el gráfico.
 */
public class Mano
        implements ObservadorRaton
{
   //~ Variables de instancia --------------------------------------------------

   /** Lienzo de dibujo */
   private Component lienzo;

   /** Escala gráfica sobre la que se va a arrastrar el gráfico. */
   private InterfaceEscalaGrafica escala;

   /** Ultima posicion x,y del ratón, en coordenadas de usuario. */
   private double x;

   /** Ultima posicion x,y del ratón, en coordenadas de usuario. */
   private double y;

   //~ Constructores -----------------------------------------------------------

   /**
    * Construye una intancia de Mano.
    *
    * @param escala La escala sobre la que se debe arrastrar.
    * @param lienzo Lienzo de dibujo.<br>
    */
   public Mano( 
      InterfaceEscalaGrafica escala,
      Component lienzo )
   {
      this.escala = escala;
      this.lienzo = lienzo;
   }

   //~ Metodos -----------------------------------------------------------------

   /**
    * Método al que se llama cuando se producen eventos de ratón. Modifica los
    * extremos de la escala gráfica según se mueve el ratón con el tercer
    * botón pulsado.
    *
    * @param e Evento del ratón.<br>
    * @param tipoEvento Tipo de evento del raton según ObservadorRaton.<br>
    * @param x Valor x del evento de raton en coordenadas usuario.<br>
    * @param y Valor y del evento de raton en coordenadas usuario.<br>
    *
    * @return true si se ha producido arraste del ratón, para que se redibuje
    * el gráfico.<br>
    */
   public boolean eventoRaton( 
      MouseEvent e,
      int tipoEvento,
      double x,
      double y )
   {
      // Si se pulsa el botón 3, se inicia todo y se pone un cursor de mano
      if( ( tipoEvento == ObservadorRaton.PULSADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON3 ) )
      {
         this.x = e.getX(  );
         this.y = e.getY(  );
         this.lienzo.setCursor( new java.awt.Cursor( Cursor.HAND_CURSOR ) );

         return false;
      }

      // Mientras se arrastra, se mueve el gráfico.
      if( ( tipoEvento == ObservadorRaton.ARRASTRE ) &&
         ( ( e.getModifiersEx(  ) & InputEvent.BUTTON3_DOWN_MASK ) == InputEvent.BUTTON3_DOWN_MASK ) )
      {
         Point2D origen =
            this.escala.dameCoordenadaUsuario( 
               new Point2D.Double( 
                  this.x,
                  this.y ) );
         origen=this.escala.dameCoordenadasCartesianas(origen);
         Point2D destino =
            this.escala.dameCoordenadaUsuario( 
               new Point2D.Double( 
                  e.getX(  ),
                  e.getY(  ) ) );
		 destino = this.escala.dameCoordenadasCartesianas(destino);
         
         Rectangle2D extremos = escala.dameExtremosCartesianos(  );

         escala.tomaExtremos( 
            extremos.getMinX(  ) - ( destino.getX() - origen.getX() ),
            extremos.getMinY(  ) - ( destino.getY() - origen.getY() ),
            ( extremos.getMinX(  ) + extremos.getWidth(  ) ) - ( destino.getX() - origen.getX() ),
            ( extremos.getMinY(  ) + extremos.getHeight(  ) ) - ( destino.getY() - origen.getY() ) );
         
         this.x = e.getX(  );
         this.y = e.getY(  );

         return true;
      }

      // Al soltar se quita el cursor de mano.
      if( ( tipoEvento == ObservadorRaton.SOLTADO ) &&
         ( e.getButton(  ) == MouseEvent.BUTTON3 ) )
      {
         this.lienzo.setCursor( new java.awt.Cursor( Cursor.DEFAULT_CURSOR ) );

         return false;
      }

      return false;
   }
}
