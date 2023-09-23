import processing.serial.*;
import java.util.List;
import java.util.ArrayList;

/**
*  Controlador del flujo del juego y los eventos enviados del/al
*  arduino.
*  @author Carlos Jimeno Miguel, Miguel Lopez de Vicente
*/

// Variables globales
int pos;
int contadorJuego;
int cont;
float umbral;
int puntuacion;
Serial serial;
Circulo c1, c2, c3, c4;
List<Circulo> circulos = new ArrayList<Circulo>();
boolean buttonsPressed[];
boolean prevButtonsPressed[];
float piezo;
boolean isStartPressed, isResetPressed, prevStartPressed, prevResetPressed, gameStarted;
float p1, p2, p3, p4;
boolean circuloElegido;
Color col;

void setup() {
  
  pos = 0;
  contadorJuego = 0;
  cont = 0;
  umbral = 3.0;
  col = Color.randomColor();
  circuloElegido = false;
  
  isStartPressed = false;
  isResetPressed = false;
  prevStartPressed = false;
  prevResetPressed = false;
  gameStarted = false;
  puntuacion = 0;
  buttonsPressed = new boolean[4];
  buttonsPressed[0] = false;
  buttonsPressed[1] = false;
  buttonsPressed[2] = false;
  buttonsPressed[3] = false;
  prevButtonsPressed = new boolean[4];
  prevButtonsPressed[0] = false;
  prevButtonsPressed[1] = false;
  prevButtonsPressed[2] = false;
  prevButtonsPressed[3] = false;
  piezo = 0.0;
  
  size(1280, 720);
  c1 = new Circulo(160,360,150);
  c2 = new Circulo(480,260,150);
  c3 = new Circulo(800,260,150);
  c4 = new Circulo(1120,360,150);
  circulos.add(c1);
  circulos.add(c2);
  circulos.add(c3);
  circulos.add(c4);
  
  // Inicializacion de los puertos serie
  String[] ports = Serial.list();
  printArray(ports);
  final int portIndex = ports.length - 1;
  serial = new Serial(this, ports[portIndex], 115200);
  serial.clear();
}

void draw() {
    noStroke();
    background(255,255,0);
    textSize(50);
    
    // Si el juego no ha comenzado indica al usuario como empezar
    if (contadorJuego < 10 || piezo >= umbral) {    
      if (!gameStarted || (isResetPressed && !prevResetPressed)) {
        puntuacion = 0;
        contadorJuego = 0;
        gameStarted = false;
        fill(0);
        text("Presiona 'Start' para empezar", 340, 600);
      }
      
      if ((isStartPressed && !prevStartPressed) || gameStarted) {
        gameStarted = true;
      }
      
      if(!circuloElegido && gameStarted) {
         pos = (int)random(0,4);
         circuloElegido = true;
         Circulo c = circulos.get(pos);
         col = Color.randomColor();
         c.setMostrado(true);
      } else if(circuloElegido && (gameStarted || (isStartPressed && !prevStartPressed))) {
        comenzarJuego(pos);
        cont++;
      }
    } else { 
      gameStarted = false;
      background(255,255,0);
      fill(0);
      text("Fin de la partida, puntuación: " + puntuacion, 320, 300);
      text("¡Para volver a jugar golpea el piezo!", 330, 600);
      
    }

    prevResetPressed = isResetPressed;
    prevStartPressed = isStartPressed;
    prevButtonsPressed[0] = buttonsPressed[0];
    prevButtonsPressed[1] = buttonsPressed[1];
    prevButtonsPressed[2] = buttonsPressed[2];
    prevButtonsPressed[3] = buttonsPressed[3];
  }

void serialEvent(Serial p) {
  try {
    final String msg = p.readStringUntil('\n');
    if (msg == null) return;
    final String[] parts = msg.trim().split(",");
    
    // Recoger pulsacion botones
    buttonsPressed[0] = (Integer.parseInt(parts[0]) == 0);
    buttonsPressed[1] = (Integer.parseInt(parts[1]) == 0);
    buttonsPressed[2] = (Integer.parseInt(parts[2]) == 0);
    buttonsPressed[3] = (Integer.parseInt(parts[3]) == 0);
    isStartPressed = (Integer.parseInt(parts[4]) == 0);
    isResetPressed = (Integer.parseInt(parts[5]) == 0);
    
    int boton = -1;
    if (buttonsPressed[0]) {
      boton = 0; 
    }
    if (buttonsPressed[1]) {
      boton = 1; 
    }
    if (buttonsPressed[2]) {
      boton = 2; 
    }
    if (buttonsPressed[3]) {
      boton = 3; 
    }

    // Recoger fuerza piezos
    piezo = map(Float.parseFloat(parts[6]), 0, 1023, 0, 100);
    actualizaPuntuacion(boton);
  } catch (RuntimeException e) {
    e.printStackTrace();
  }
}

// Metodos propios usados para el juego
/**
*    Este metodo se encarga de mostrar la puntuacion obtenida por
*    el jugador hasta el momento y dibujar los circulos en pantalla.
*    
*    @param circulo posicion del circulo a mostrar
*/
void comenzarJuego(int circulo) {

  text("", 500, 600);
  text("Puntuacion: " + puntuacion, 500, 600);
  // Dibujamos los circulos
    Circulo c = circulos.get(circulo);
    if (cont <= 100) {
      c.setTiempoMuestra(millis());
      c.drawCircle(col.getRed(), col.getGreen(), col.getBlue());
      c.setColor(col);
    } else {
      c.setMostrado(false);
      circuloElegido = false;
      cont = 0;
    }
  fill(0);
  
}


/*
*    Metodo que se encarga de comprobar si el circulo se ha presionado
*    correctamente y que puntos deben sumarse a la puntuacion actual
*    del jugador.
*
*    @param num posicion del boton presionado
*/
void actualizaPuntuacion(int num) {
    if (pos > -1 && num > -1) {
     Circulo c = circulos.get(num);
     if ((buttonsPressed[pos] && !prevButtonsPressed[pos]) && c.getMostrado()) {
       puntuacion += c.getColor().getPuntos();
       if (c.getColor() == Color.AZUL || c.getColor() == Color.VERDE) {
         int gr = c.getColor().getGreen();
         int bl = c.getColor().getBlue();
         serial.write(1 + "," + num + "," + gr + "," + bl + "\n");
         c.setMostrado(false);
         contadorJuego++;
       } else {
         serial.write(0 + "," + num + "," + -1 + "," + -1 + "\n");
       }
     }
   }
}
