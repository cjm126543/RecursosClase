import java.util.Random;

public enum Color {
  
  // Valores posibles
  VERDE(1.0, 0x00ff00), AZUL(10.0, 0x0000ff), ROJO(-2.0, 0xff0000), BLANCO(-20.0, 0xFFFFFF);
  
  // Atributos
  private float puntos;
  private int col;
  private static final Random RND = new Random();
  private static final Color[] colores = values();
  
  // Constructor
  private Color(float p, int col) {
    this.puntos = p;
    this.col = col;
  }
  
  // Getters
  public float getPuntos() { return this.puntos; }
  public int getColor() { return this.col; }
  
  // Metodos propios
  public static Color randomColor() {
    return colores[RND.nextInt(colores.length)];
  }
}
