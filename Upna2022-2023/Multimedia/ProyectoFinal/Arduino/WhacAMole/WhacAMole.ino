/* Librerias */
#include <Adafruit_NeoPixel.h>

/* Pines y atributos por componente */
// Botones
#define START 2
#define RESTART 3
#define B1 4
#define B2 5
#define B3 6
#define B4 7
int botones[6] = {START, RESTART, B1, B2, B3, B4};

// Monedas de vibracion
#define C1 8

// Piezoelectricos
#define P1 A1

// Tira LED
#define PIN_TLED 9
#define NUM_LEDS 10
Adafruit_NeoPixel neoPixel(NUM_LEDS, PIN_TLED, NEO_GRB + NEO_KHZ800);

/* Variables globales */
int cont;
int acierto;
int posicion;
int colorRed;
int colorGreen;
int colorBlue;

void setup()
{
  // Declaracion de variables
  cont = 0;
  acierto = -1;
  
  Serial.begin(115200);
  
  // Botones
  pinMode(botones[0], INPUT_PULLUP);
  pinMode(botones[1], INPUT_PULLUP);
  pinMode(botones[2], INPUT_PULLUP);
  pinMode(botones[3], INPUT_PULLUP);
  pinMode(botones[4], INPUT_PULLUP);
  pinMode(botones[5], INPUT_PULLUP);

  
  // Monedas
  pinMode(C1, OUTPUT);
  
  // Piezoelectricos
  pinMode(P1, INPUT);

  // Leds
  neoPixel.begin();
  neoPixel.clear();
  neoPixel.show();
   
}

void loop()
{
  acierto = -1; // Reiniciamos en cada iteracion para que el acceso a la condicion sea solo eventual

  // Envio de informacion
  const int start = digitalRead(botones[0]);
  const int restart = digitalRead(botones[1]);
  const int b1 = digitalRead(botones[2]);
  const int b2 = digitalRead(botones[3]);
  const int b3 = digitalRead(botones[4]);
  const int b4 = digitalRead(botones[5]);
  
  const float p1 = analogRead(P1);
  
  delay(100); // TODO no da tiempo a pulsacion y mostrarse el circulo, mirarlo
  Serial.print(b1);
  Serial.print(',');
  Serial.print(b2);
  Serial.print(',');
  Serial.print(b3);
  Serial.print(',');
  Serial.print(b4);
  Serial.print(',');
  Serial.print(start);
  Serial.print(',');  
  Serial.print(restart);
  Serial.print(',');
  Serial.print(p1);
  Serial.print(',');
  Serial.println(1024);


  // Recepcion de informacion
  if (Serial.available() > 0) {
    delay(250);
    acierto = Serial.parseInt();
    posicion = Serial.parseInt();
    colorGreen = Serial.parseInt();
    colorBlue = Serial.parseInt();
  }

  // Activa vibracion o led dependiendo del acierto (o fallo)
  // TODO mirar como podemos mezclar comportamiento del piezo y moneda
  if (acierto == 0) {
    digitalWrite(C1, HIGH);
    delay(500);
    digitalWrite(C1, LOW);
  }
  
  if (acierto == 1 && cont < 10) {
    neoPixel.setPixelColor(cont, neoPixel.Color(0, colorGreen, colorBlue)); // 0 en rojo ya que solo mostraremos azul y verde
    neoPixel.show();
    cont++;
  }
  delay(250);
}
