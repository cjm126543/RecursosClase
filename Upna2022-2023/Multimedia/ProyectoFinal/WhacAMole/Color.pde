import java.util.Random;

public enum Color {
  
  // Valores posibles
  VERDE(10, 0, 255, 0), AZUL(1, 0, 0, 255), ROJO(-2, 255, 0, 0), BLANCO(-20, 255, 255, 255);
  
  // Atributos
  private int puntos;
  private int r, g, b;
  private static final Random RND = new Random();
  private static final Color[] colores = values();
  
  // Constructor
  private Color(int p, int r, int g, int b) {
    this.puntos = p;
    this.r = r;
    this.g = g;
    this.b = b;
  }
  
  // Getters
  public int getPuntos() { return this.puntos; }
  public int getRed() { return this.r; }
  public int getBlue() { return this.g; }
  public int getGreen() { return this.b; }
  
  // Metodos propios
  public static Color randomColor() {
    return colores[RND.nextInt(colores.length)];
  }
}
