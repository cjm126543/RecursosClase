public class Circulo {
  
  // Atributos
  private float x, y, radio;
  private Color col;
  private int tiempoMuestra;
  private boolean mostrado;
  
  // Constructor
  public Circulo(float x, float y, float r) {
    this.x = x;
    this.y = y;
    this.radio = r;
    this.col = Color.randomColor();
    this.tiempoMuestra = 0;
    this.mostrado = false;
  }
  
  // Getters y setters
  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
  public void setRadio(float radio) { this.radio = radio; }
  public void setColor(Color col) { this.col = col; }
  public void setTiempoMuestra(int tiempo) { this.tiempoMuestra = tiempo; }
  public void setMostrado(boolean val) { this.mostrado = val; }
  public float getX() { return this.x; }
  public float getY() { return this.y; }
  public float getRadio() { return this.radio; }
  public Color getColor() { return this.col; }
  public int getTiempoMuestra() { return this.tiempoMuestra; }
  public boolean getMostrado() { return this.mostrado; }
  
  // Metodos propios
  public void nuevoTiempoMuestra() {
    this.tiempoMuestra = (int) random(2000, 5000);
  }
  
  void drawCircle(int red, int green, int blue){
    fill(red, green, blue);
    circle(this.x, this.y, this.radio);
  }
}
