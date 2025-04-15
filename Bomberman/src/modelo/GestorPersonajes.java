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
	
	public void crearInstanciaPersonaje(int mapaSelect, int personajeSelect) {
		if (personajeSelect == 1) {
			this.bomberman = new PersonajeBlanco(mapaSelect);
		}
		else { //Si no es 1, por ahora solo puede ser 2
			this.bomberman = new PersonajeNegro(mapaSelect);
		}
		
													  
	}
}
