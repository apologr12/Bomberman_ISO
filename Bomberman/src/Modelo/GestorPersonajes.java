package Modelo;

public class GestorPersonajes {
	private static GestorPersonajes miGestorPersonajes = new GestorPersonajes();
	private Personaje bombermanBlanco;
																	 
	private GestorPersonajes() {
		this.crearPersonajeBlanco(); //LLAMADA TEMPORAL QUE SERA QUITADA UNA VEZ TENGAMOS EL MENU
	}
	
	public static GestorPersonajes getGestorPersonajes() {
		return miGestorPersonajes;
	}

	public Personaje getPersonaje() {
		return this.bombermanBlanco;
	}
	
	public void crearPersonajeBlanco() {
		this.bombermanBlanco = new PersonajeBlanco();  //El personaje se crea aqui temporalmente, es decir, esto en el futuro se quitara
													   //y la creacion del personaje se hara con un metodo cuando detecte el boton que ha seleccionado al personaje
	}
}
