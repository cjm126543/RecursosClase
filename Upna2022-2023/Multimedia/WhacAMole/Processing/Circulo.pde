public class Circulo {
  
  // Atributos
  private float x, y, radio;
  private Color col;
  private float tiempoMuestra;
  
  // Constructor
  public Circulo(float x, float y, float r) {
    this.x = x;
    this.y = y;
    this.radio = r;
    this.tiempoMuestra = random(2.0, 5.01); // 0.1ms anhadidos para incluir 5s
    this.col = Color.randomColor();
  }
  
  // Getters y setters
  public void setX(float x) { this.x = x; }
  public void setY(float y) { this.y = y; }
  public void setRadio(float radio) { this.radio = radio; }
  public void setColor(Color col) { this.col = col; }
  public float getX() { return this.x; }
  public float getY() { return this.y; }
  public float getRadio() { return this.radio; }
  public Color getColor() { return this.col; }
  public float getTiempoMuestra() { return this.tiempoMuestra; }
  
  // Metodos propios
  
}
