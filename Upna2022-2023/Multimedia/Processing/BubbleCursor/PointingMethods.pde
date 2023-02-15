PointingMethod method = new BubbleCursor(); //change here for other methods: SimpleCursor, KeyboardCtrl, NinjaCursor
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

class BubbleCursor extends PointingMethod{
  final int minDist = 200;
  Circle nc;
  float r = 0.0;
  
  void draw(ArrayList<Circle> cursor) {
    
    // Pick nearest circle
    nc = pickNearestCircle(cursor);
    
    // Draw transparent circle
    fill(50, 50, 50, 50);
    stroke(0);
    strokeWeight(2);
    if ((dist(mouseX, mouseY, nc.x, nc.y) * 2 + nc.rad * 2) > 200) {
      noFill();
    } else {
      circle(mouseX, mouseY, (dist(mouseX, mouseY, nc.x, nc.y) * 2 + nc.rad * 2));
    }
    
    // Draw crosshair
    strokeWeight(1);
    drawCrossHair(mouseX, mouseY);
  }
  
  Circle pickNearestCircle(ArrayList<Circle> arr) {
    Circle min = new Circle(1000, 1000, 1000);
    for (Circle c: arr) {
      
      if (dist(mouseX, mouseY, c.x, c.y) < dist(mouseX, mouseY, min.x, min.y)) {
        min = c ;
      }
    }
    
    return min;
  }

}
