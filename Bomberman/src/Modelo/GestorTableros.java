package Modelo;

public class GestorTableros {
	private static GestorTableros miGestor = new GestorTableros();
	private Tablero tablero; //Este atributo se inicializa de un tipo de tablero u otro en funcion del tablero que se haya creado.
	 						//Asi es como entiendo el funcionamiento del gestor 
	private GestorTableros() {
	}								//por lo que esta llamada habra que quitarla
	
	public static GestorTableros getGestorTableros() {
		return miGestor;
	}
	
	public Tablero getTablero() {//Esto no me convence nada, pero ahora mismo no se me ocurre otra forma de hacerlo
		if (this.tablero == null) { //Este if es temporal, ahora hace falta porque no hay menu. Cuando haya menu la instancia siempre deberia estar creada
			this.tablero = new TableroClasico();	//en funcion del tablero seleccionado
		}
		return this.tablero;					//(hay que preguntarle a Ander)
	}
	
	public void crearTablero() {
	    this.tablero.crearTablero();
	}

}
