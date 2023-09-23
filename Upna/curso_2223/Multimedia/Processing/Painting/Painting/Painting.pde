// Java imports
import java.util.List;
import java.util.ArrayList;

/**
*  Classes for sketch functionality
*/
public enum Mode {
  NONE,
  TRACKING,
  SPIRAL,
  PROXIMITY
}

public class PolyLine {
  // Attributes
  //private static int identificator = 0;
  private List<Vertex> points;
  
  // Constructor
  public PolyLine() {
    //this.identificator++;  // Easy recognition for further shape searching (if developed)
    this.points = new ArrayList<>();
  }
  
  // Getters and setters
  public void setPoints(List<Vertex> arr) { this.points = arr; }
  public List<Vertex> getPoints() { return points; }
  
  // Class methods
  public void addVertex(Vertex v) {
     points.add(v);
  }
  
  public void begin() {
    noFill();
    beginShape(); 
  }
  
  public void drawPoints() {
    for (Vertex v: points) {
       vertex(v.getX(), v.getY());
    }
  }
  
  public void finish() {
    endShape(); 
  }
  
  public void eraseAll() {
    points.removeAll(points);
    background(255);
  }
}

public class Vertex {
  // Attributes
  private float x, y;

  // Constructor
  public Vertex(float x, float y) {
    this.x = x;
    this.y = y;
  }

  // Getters and setters
  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
  public float getX() { return x; }
  public float getY() { return y; }
}

/**
*  Main sketch body
*/

// Global variables
PolyLine pol;
Mode currentMode;
int actualStroke;

// Methods
void setup() {
  currentMode = Mode.NONE;
  displayMode(currentMode);
  size(1000, 1000);
  background(255);
  pol = new PolyLine();
  actualStroke = 5;
}

void draw() {
  // Display current mode
  displayMode(currentMode);
  
  pol.begin();
  
  // W moves the points upwards TODO
  if (keyPressed && (key == 'w' || key == 'W')) {
    background(255);
    List<Vertex> points = pol.getPoints();
    for (Vertex v: points) {
       if (v.getY() + 1.0 < height) {
         v.setY(0.0);
       } else {
         v.setY(v.getY() + 1.0); 
       }
    }
  }
  
  // A moves the points left-sideways TODO
  if (keyPressed && (key == 'a' || key == 'A')) {
    background(255);
    List<Vertex> points = pol.getPoints();
    for (Vertex v: points) {
       if (v.getX() + 1.0 < width) {
         v.setX(0.0);
       } else {
         v.setX(v.getX() + 1.0); 
       }
    }
  }
  
  
  pol.drawPoints();
  pol.finish();
}

// Whenever the mouse is pressed draw a new line between those points
void mouseClicked() {
  pol.addVertex(new Vertex(mouseX, mouseY));
}

void keyPressed() {
  // N is for cleaning the window
  if (key == 'n' || key == 'N')  {
     pol.finish();
     pol.eraseAll();
  }
  
  // (0-9) selects the color of next segment
  switch(key) {
    case '0':
      stroke(0, 0, 0);
      break;
      
    case '1':
      stroke(255, 0, 0);
      break;
      
    case '2':
      stroke(0, 255, 0);
      break;
      
    case '3':
      stroke(0, 0, 255);
      break;
      
    case '4':
      stroke(0, 255, 255);
      break;
      
    case '5':
      stroke(255, 0, 255);
      break;
      
    case '6':
      stroke(255, 255, 0);
      break;
      
    case '7':
      stroke(100, 50, 50);
      break;
      
    case '8':
      stroke(50, 100, 50);
      break;
      
    case '9':
      stroke(50, 50, 100);
      break;
      
    // + and - selects thickness of next segment
    case '+':
      actualStroke += 1;
      strokeWeight(actualStroke);
      break;
      
    case '-':
      if (actualStroke < 1) actualStroke = 1;
      else actualStroke -= 1;
      strokeWeight(actualStroke);
      break;
  }
  
  // F enables tracking mode TODO
  
  
  // Q enables spiral mode TODO
  
  
  // D enables proximity mode TODO
}

void displayMode(Mode m) {
  String mode = "";
  switch (m) {
    case NONE:
      mode = "None";
      break;
      
    case TRACKING:
      mode = "Tracking";
      break;
      
    case SPIRAL:
      mode = "Spiral";
      break;
      
    case PROXIMITY:
      mode = "Proximity";
      break;
  }
  textSize(20);
  fill(0);
  text("Modo activo: " + mode + "\n", 25, 50);
}
