Circle c;
int aciertos, clicks;
float start, end;

void setup() {
    clicks = 0;
    aciertos = 0;
    size(800, 600);
    background(0, 0, 0);
    fill(0, 255, 0);
    noStroke();
    c = new Circle(random(width), random(height), 50);
    start = millis();
}

void draw() {
  if (aciertos >= 20) {
     end = millis() - start;
     print("Cociente: " + ((double) aciertos / (double) clicks) * 100.0 + "%\n"); 
     print("Tiempo: " + end + " ms \n");
     exit();
  }
}

void mouseReleased() {
  delay(50);
  clicks += 1; 
  if (dist(c.x, c.y, mouseX, mouseY) < c.r) {
      delay(50);
      background(0, 0, 0);
      c = new Circle(random(width), random(height), 50);
      aciertos += 1;
  }
}

public class Circle {
  float x, y, r;
  Circle(float x, float y, float r) {
      this.x = x;
      this.y = y;
      this.r = r;
      circle(x, y, r); 
  }
}

public class Point {
 float x, y; 
 Point(float x, float y) {
     this.x = x;
     this.y = y;
     point(x, y); 
 }
}
