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

	public Personaje getPersonajeBlanco() { //Esto es lo mismo que lo del gestor de tableros. No me convence nada
		//if (this.bombermanBlanco == null) {
           // this.bombermanBlanco = PersonajeBlanco.getPersonaje();
        //}
		return this.bombermanBlanco;			  //pero es que si no lo hacemos asi no se que sentido tiene tener un gestor.
	}											  //No veo otra forma en que se pueda usar sin poner todos los metodos de 'Personaje' aqui.
												  //Y eso lo unico que haria seria convertir esta clase de nuevo en un personaje multiple.
	public void crearPersonajeBlanco() {
		this.bombermanBlanco = new PersonajeBlanco();  //El personaje se crea aqui temporalmente, es decir, esto en el futuro se quitará
													   //y la creacion del personaje se hara con un metodo cuando detecte el boton que ha seleccionado al personaje
	}
}
