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
//		if (this.tablero == null) { //Esto en el futuro hay que quitarlo
//			this.tablero = new TableroClasico();
//		}
		return this.tablero;
	}
	
	public void crearTablero() {
	    this.tablero.crearTablero();
	}
	public Tablero crearInstanciaTablero(int pTipoTablero, int pTipoPersonaje) {
	    //TODO
		if (pTipoTablero == 1) {
			this.tablero = new TableroClasico(pTipoPersonaje);
		}
		else if(pTipoTablero == 2) {
			this.tablero = new TableroSoft(pTipoPersonaje);
		}
		else if(pTipoTablero == 3) {
			this.tablero=new TableroEmpty(pTipoPersonaje);
		}
		else{
			System.out.println("No hay mas tableros");
		}
		return this.tablero;
	}

}
