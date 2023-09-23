import processing.serial.*; //you may need to go to Tools, Add Tool, Libraries, Serial

PointingMethod method = new JoystickArdu(); 
final int N_TRIALS = 20;
final float MIN_RAD = 8, MAX_RAD = 50;

//https://colorhunt.co/palette/f2cd5cf2921da61f69400e32
final color ca = color(242, 205, 92);
final color cb = color(242, 146, 29);
final color cc = color(166, 31, 105);
final color cd = color(64, 14, 50);

/*
*    Setup, draw and events
*    
*/
Serial serial;
final ArrayList<Circle> circles = new ArrayList<Circle>();
int nClicks;
long timeStartTrial;

void setup(){
  size(800, 600);
  startTrial();
  
  String[] sPorts = Serial.list();
  printArray(sPorts); //this way you can check in which index is the arduino PORT
  int portIndex = 0;
  for (int i = 0; i < sPorts.length; i++) {
    if (match(sPorts[i], "ttyACM0") != null) {
      portIndex = i;
    }
  }
  serial = new Serial(this, sPorts[portIndex], 115200); //be sure to match this baudrate to the Arduino Sketch
  serial.clear(); //clear things that could be on the buffer
}

float arduVx1, arduVy1, arduVx2, arduVy2;
boolean arduBt1, prevArduBt1, arduBt2, prevArduBt2;
//this gets called everytime we have things to read
void serialEvent(Serial p){
  try {
   final String msg = p.readStringUntil('\n');    
   final String[] parts = msg.trim().split(",");
   
   // Joystick 1
   arduVx1 = map( Float.parseFloat( parts[0] ), 0,1023, -5, 5);
   arduVy1 = map( Float.parseFloat( parts[1] ), 0,1023, -5, 5);
   arduBt1 = Integer.parseInt( parts[2] ) == 0;
   
   // Joystick 2
   arduVx2 = map( Float.parseFloat( parts[3] ), 0,1023, -5, 5);
   arduVy2 = map( Float.parseFloat( parts[4] ), 0,1023, -5, 5);
   arduBt2 = Integer.parseInt( parts[5] ) == 0;
  } catch (Exception NullPointerException) {
    return;
  }
}

void startTrial(){
  addRandomCircles(circles, 20);
  circles.get(0).isTarget = true;
  nClicks = 0;
  timeStartTrial = millis();
}
 
void draw(){
  background(ca);
  stroke(0);
  strokeWeight(1);
  for(Circle c : circles){
    fill(c.isTarget ? cc : cb);
    c.draw();
  }
  
  if (arduBt1 == true && prevArduBt1 == false){ //button joystick 1 is pressed
    Circle c = method.onClick(circles, 0, 0);
    pointOn( c );
  }
  prevArduBt1 = arduBt1;
  
  if (arduBt2 == true && prevArduBt2 == false){ //button joystick 2 is pressed
    Circle c = method.onClick2(circles, 0, 0);
    pointOn( c );
  }
  prevArduBt2 = arduBt2;
  
  method.draw(circles);
}


void pointOn( Circle c ){
  nClicks += 1;
  
  if (c != null && c.isTarget){
    circles.remove( c );
  }
  
  if (circles.isEmpty()){
    showResults();
    startTrial();
  }else{
    circles.get(0).isTarget = true;
  }
}

void showResults(){
  final long t = millis() - timeStartTrial;
  final float accuracy = N_TRIALS * 100.0f / nClicks ;
  println("****** Test Results:");
  println("TCT(ms): " + t);
  println("accuracy: " + accuracy);
}


/*
*   Circles
*    
*/
class Circle{
  float x,y,rad;
  boolean isTarget;
  public Circle(float x, float y, float rad) { this.x = x; this.y = y; this.rad = rad;}
  void draw(){
    circle(x,y,rad*2);
  }
  boolean isInside(float px, float py) { return dist(px,py,this.x,this.y) < rad; }
  boolean isInside(Circle o) { return dist(this.x,this.y,o.x,o.y) < this.rad+o.rad;}
}
Circle pickCircle(ArrayList<Circle> cs, float x, float y){
  for (Circle c : cs)
    if (c.isInside(x,y)) return c;
  return null;
}
Circle pickCircle(ArrayList<Circle> cs, Circle cp){
  for (Circle c : cs)
    if (c.isInside(cp)) return c;
  return null;
}

void addRandomCircles(ArrayList<Circle> circles, int n){
    circles.clear();
    //Could be more efficient and nicer [physics based for example] but it does the job
    for(int i = 0; i < n; i++){
      Circle c;
      do{ //no overlapping, careful if too many circles -> blocked. 
        float rad = random(MIN_RAD, MAX_RAD);
        c = new Circle( random(rad, width-rad), random(rad,height-rad), rad);
      }while ( pickCircle(circles, c) != null); 
      circles.add(c);
    }
}


/*
*    Pointing Methods
*    
*/
class PointingMethod{
    void draw(ArrayList<Circle> cs){
      fill(0);
      drawCrossHair(mouseX,mouseY);
    }
    void drawCrossHair(float x, float y){
      line(x-10,y,x+10,y);
      line(x,y-10,x,y+10);
    }
    Circle onKey(ArrayList<Circle> cs, int k, int ck, boolean pressingDown){return null;}
    Circle onClick(ArrayList<Circle> cs, float x, float y){return null;}
    Circle onClick2(ArrayList<Circle> cs, float x, float y){return null;}
}

class SimpleCursor extends PointingMethod{
  Circle onClick(ArrayList<Circle> cs, float x, float y){
    return pickCircle(circles, x,y);
  }
}

class JoystickArdu extends PointingMethod{
  int x = 100, y = 100, x2 = 200, y2 = 200;
  
  void draw(ArrayList<Circle> cs){
      fill(0);
      strokeWeight(5);
      drawCrossHair(x,y);
      drawCrossHair(x2, y2);
      
      //not the place right for logic, but it will do for the moment.
      x += int(arduVx1);
      x2 += int(arduVx2);
      y += int(arduVy1);
      y2 += int(arduVy2);
      x = constrain(x,0,width);
      x2 = constrain(x2,0,width);
      y = constrain(y,0,height);
      y2 = constrain(y2,0,height);
      
      //how close is the closest target
      float minDist = Float.MAX_VALUE;
      float minDist2 = Float.MAX_VALUE;
      for( Circle c : cs ){
        minDist = min( minDist, dist(x,y, c.x, c.y) - c.rad );
        minDist2 = min( minDist2, dist(x2,y2, c.x, c.y) - c.rad );
      }
  }
  
  Circle onClick(ArrayList<Circle> cs, float x, float y){
    return pickCircle(circles, this.x, this.y);
  }
  
  Circle onClick2(ArrayList<Circle> cs, float x, float y){
    return pickCircle(circles, this.x2, this.y2);
  }
  
}
