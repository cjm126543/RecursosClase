#define PIN_POT_X1 A0
#define PIN_POT_X2 A2
#define PIN_POT_Y1 A1
#define PIN_POT_Y2 A5
#define PIN_B1 2
#define PIN_B2 4

void setup(){
  pinMode(PIN_POT_X1, INPUT);
  pinMode(PIN_POT_X2, INPUT);
  pinMode(PIN_POT_Y1, INPUT);
  pinMode(PIN_POT_Y2, INPUT);
  pinMode(PIN_B1, INPUT_PULLUP);
  pinMode(PIN_B2, INPUT_PULLUP);
  Serial.begin( 115200 );
}

void loop(){
  const int x1 = analogRead(PIN_POT_X1);
  const int y1 = analogRead(PIN_POT_Y1);
  const int b1 = digitalRead(PIN_B1);
  const int x2 = analogRead(PIN_POT_X2);
  const int y2 = analogRead(PIN_POT_Y2);
  const int b2 = digitalRead(PIN_B2);
 
  Serial.print(x1);
  Serial.print(',');
  Serial.print(y1);
  Serial.print(',');
  Serial.print(b1);
  Serial.print(',');
  Serial.print(x2);
  Serial.print(',');
  Serial.print(y2);
  Serial.print(',');
  Serial.print(b2);
  Serial.print(',');
  Serial.println(1024);
}
