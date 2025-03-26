package modelo;

public class GestorTableros {
	private static GestorTableros miGestor = new GestorTableros();
	private Tablero tablero;
	
	private GestorTableros() {
	}								
	
	public static GestorTableros getGestorTableros() {
		return miGestor;
	}
	
	public Tablero getTablero() {
		if (this.tablero == null) { //Esto en el futuro hay que quitarlo
			this.tablero = new TableroClasico();
		}
		return this.tablero;
	}
	
	public void crearTablero() {
	    this.tablero.crearTablero();
	}

}
