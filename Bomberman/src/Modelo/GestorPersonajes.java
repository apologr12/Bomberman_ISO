package Modelo;

public class GestorPersonajes {
	private static GestorPersonajes miGestorPersonajes = new GestorPersonajes();
	private PersonajeBlanco bombermanBlanco = null;
	
	private GestorPersonajes() {
	}
	
	public static GestorPersonajes getGestorPersonajes() {
		return miGestorPersonajes;
	}

	public PersonajeBlanco getPersonajeBlanco() { //Esto es lo mismo que lo del gestor de tableros. No me convence nada
		if (this.bombermanBlanco == null) {
            this.bombermanBlanco = PersonajeBlanco.getPersonaje();
        }
		return this.bombermanBlanco;			  //pero es que si no lo hacemos asi no se que sentido tiene tener un gestor.
	}											  //No veo otra forma en que se pueda usar sin poner todos los metodos de 'Personaje' aqui.
												  //Y eso lo unico que haria seria convertir esta clase de nuevo en un personaje multiple.

}
