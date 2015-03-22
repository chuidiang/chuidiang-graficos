# chuidiang-graficos

Una librería de gráficos en java.

La base de la librería es un Lienzo gráfico con dos capas. Una de fondo en la que dibujar gráficos y otra superior, transparente, para dibujar cursores, objetos arrastrables, de forma que no sea necesario realizar un repintado de todo el gráfico al mover uno de estos objetos.

Los gráficos a dibujar son clases que hereden de objeto gráfico. Reciben una clase de pintado similar al java.awt.Graphics de java, pero en el que las coordenadas a usar son directamente coordenadas de usuario en vez de pixels.

Los objetos gráficos se añaden a voluntad al lienzo. Se pueden usar los ya creados o bien hacerse unos nuevos. Existen además objetos gráficos suscritos a los movimientos del ratón, de forma que se pueden arrastrar o reaccionar a los clicks de ratón.

URL: http://proyectos.chuidiang.com/graficos/
