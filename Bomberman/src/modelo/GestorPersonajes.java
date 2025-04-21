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
		if (personajeSelect == 1) { //Blanco
			this.bomberman = new PersonajeBlanco(mapaSelect);
		}
		else if (personajeSelect == 2) { //Negro
			this.bomberman = new PersonajeNegro(mapaSelect);
		}
		else { //Si no es ni blanco ni negro por ahora solo puede ser azul
			this.bomberman = new PersonajeArena(mapaSelect);
		}
		
													  
	}
}
