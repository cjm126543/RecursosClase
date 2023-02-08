PointingMethod method = new NinjaCursor(); //change here for other methods: SimpleCursor, KeyboardCtrl, NinjaCursor
final int N_TRIALS = 20;
final float MIN_RAD = 8, MAX_RAD = 50;

//https://colorhunt.co/palette/f2cd5cf2921da61f69400e32
final color ca = color(242, 205, 92);
final color cb = color(242, 146, 29);
final color cc = color(166, 31, 105);
final color cd = color(64, 14, 50);

final ArrayList<Circle> circles = new ArrayList<Circle>();
int nClicks;
long timeStartTrial;

void setup(){
  size(800, 600);
  startTrial();
}

void startTrial(){
  addRandomCircles(circles, 20);
  circles.get(0).isTarget = true;
  nClicks = 0;
  timeStartTrial = millis();
}
 
boolean prevKeyPressed = false;
void draw(){
  background(ca);
  stroke(0);
  strokeWeight(1);
  for(Circle c : circles){
    fill(c.isTarget ? cc : cb);
    c.draw();
  }
  if (keyPressed){
    boolean pressingDown = prevKeyPressed == false && keyPressed == true;
    Circle c = method.onKey(circles, key, keyCode, pressingDown );
    if (pressingDown && key == ' '){
      pointOn( c );
    }
  }
  
  method.draw(circles);
}


void mousePressed(){
  nClicks += 1;
  Circle c = method.onClick(circles, mouseX, mouseY);
  pointOn( c );
}

void pointOn( Circle c ){
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
}

class SimpleCursor extends PointingMethod{
  Circle onClick(ArrayList<Circle> cs, float x, float y){
    return pickCircle(circles, x,y);
  }
}

class KeyboardCtrl extends PointingMethod{ //ASDW for fast movement, direction keys for slow movement, space to click
  float x = 100, y = 100;
  void draw(ArrayList<Circle> cs){
      fill(0);
      drawCrossHair(x,y);
  }

  Circle onKey(ArrayList<Circle> cs, int k, int ck, boolean pressingDown){
    final float speed1 = 15;
    final float speed2 = 1;
    if (k == 'a' || k == 'A' ) { x -= speed1;}
    else if (k == 'd' || k == 'D' ) { x += speed1;}
    else if (k == 's' || k == 'S' ) { y += speed1;}
    else if (k == 'w' || k == 'W' ) { y -= speed1;}
    
    if (ck == LEFT ) { x -= speed2;}
    else if (ck == RIGHT) { x += speed2;}
    else if (ck == DOWN) { y += speed2;}
    else if (ck == UP) { y -= speed2;}
    
    if (pressingDown && k == ' '){
      return pickCircle(cs, x,y);
    }
    
    return null;
  }
}

class NinjaCursor extends PointingMethod{
  final float DIST = 200;
  final float[][] OFFSETS = {{-DIST,0}, {DIST,0}, {0,DIST}, {0,-DIST}};
  int iCursor = 0;
  
  void draw(ArrayList<Circle> cs){
      stroke(cd);
      for(float[] p : OFFSETS){
        drawCrossHair(mouseX + p[0],mouseY + p[1]);
      }
      
      stroke(0);
      strokeWeight(4);
      drawCrossHair(mouseX + OFFSETS[iCursor][0],mouseY + OFFSETS[iCursor][1]);
    }
    
    Circle onClick(ArrayList<Circle> cs, float x, float y){
      /*for(float[] p : OFFSETS){
        Circle c = pickCircle(cs, mouseX+p[0], mouseY+p[1]);
        if (c != null) {return c;}
      }
      return null;
      */
      return pickCircle(cs, mouseX+OFFSETS[iCursor][0], mouseY+OFFSETS[iCursor][1]);
    }
    
    Circle onKey(ArrayList<Circle> cs, int k, int ck, boolean pressingDown){
      if (ck == LEFT ) {iCursor = 0;}
      else if (ck == RIGHT) { iCursor = 1;}
      else if (ck == DOWN) { iCursor = 2;}
      else if (ck == UP) { iCursor = 3;}
    
      return null;
    }
}

class BubbleCursor extends PointingMethod{
  
}
