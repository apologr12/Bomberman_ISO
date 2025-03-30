package modelo;

public class GestorPersonajes {
	private static GestorPersonajes miGestorPersonajes = new GestorPersonajes();
	private Personaje bomberman;
																	 
	private GestorPersonajes() {
	}
	
	public static GestorPersonajes getGestorPersonajes() {
		return miGestorPersonajes;
	}

	public Personaje getPersonaje() {
		return this.bomberman;
	}
	
	public void crearInstanciaPersonaje(int num) {
		if (num == 1) {
			this.bomberman = new PersonajeBlanco();  //El personaje se crea aqui temporalmente, es decir, esto en el futuro se quitara
														//y la creacion del personaje se hara con un metodo cuando detecte el boton que ha seleccionado al personaje
		}
		else { //Si no es 1, por ahora solo puede ser 2
			this.bomberman = new PersonajeNegro();
		}
		
													  
	}
}
