package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Personaje extends Observable {
	private int x, y;
	private int numBombas;
	
	protected Personaje(int pY, int pX, int pBombas) {
		this.x = pX;
		this.y = pY;
		this.numBombas = pBombas;
	}
	
	protected abstract void dejarDeMostrarPersonaje();					//TODOS ESTOS METODOS ABSTRACTOS SON METODOS QUE SE LLAMAN DESDE LOS SUPER
																		//QUE EN FUNCION DE LA INSTANCIA DESDE LA QUE SE LLAMAN (LOS SUPERS) TIENEN QUE HACER UNA
	protected abstract void mostrarBomba();								//COSA U OTRA
	
	protected abstract void mostrarPersonajeUp();
	
	protected abstract void mostrarPersonajeLeft();
	
	protected abstract void mostrarPersonajeRight();
	
	protected abstract void mostrarPersonaje();
	
	public void plantarBomba() {
	    if (this.quedanBombas()) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        boolean seHaPodido = GestorTableros.getGestorTableros().getTablero().ponerBomba(this.y, this.x);
	        if (seHaPodido) {
	        	this.restarBomba();
	        }
	    }
	}
	
	public abstract void explosionBomba(int pY, int pX); //Este metodo se llama desde el BloqueBombaSimple o Ultra y es para indicarle al personaje que ha explotado
	
	public boolean choque(int pY, int pX) { //Este metodo revisa si el personaje esta en la posicion a donde el enemigo se va a mover o si coincide con una explosion,
		if (this.coincideX(pX) && this.coincideY(pY)) {   // y si esta ahi, lo mata
			return true;
		}
		else {
			return false;
		}
		
	} 
	
	
	public void movimientoL() {
		int puedoMoverme;
		if ((puedoMoverme = GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y, x-1)) == 1) {
			if (!GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			} 
			else if(GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				mostrarBomba();																//Dejamos de mostrar el personaje con la bomba, pero dejamos la bomba solo
			}
			x--;
			mostrarPersonajeLeft();
			System.out.println("Izquierda");
		}
		else if (puedoMoverme == -1) { //Si le ha devuelto -1 es que se ha muerto
			dejarDeMostrarPersonaje();
			x--;
			mostrarPersonajeLeft();
			this.meHeMuerto(1);
		}
		else if (puedoMoverme == -2) { //Si le ha devuelto -2 es que se ha muerto por tocar un enemigo
			dejarDeMostrarPersonaje();
			x--;
			mostrarPersonaje();
			this.meHeMuerto(2);
		}
	}
	
	public void movimientoR() {
		int puedoMoverme;
		if ((puedoMoverme = GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y, x+1)) == 1) {
			if (!GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			}
			else if(GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				mostrarBomba();
			}
			x++;
			mostrarPersonajeRight();
			System.out.println("Derecha");
		}
		else if (puedoMoverme == -1) { //Si le ha devuelto -1 es que se ha muerto
			dejarDeMostrarPersonaje();
			x++;
			mostrarPersonajeRight();
			this.meHeMuerto(1);
		}
		else if (puedoMoverme == -2) { //Si le ha devuelto -2 es que se ha muerto por tocar un enemigo
			dejarDeMostrarPersonaje();
			x++;
			mostrarPersonaje();
			this.meHeMuerto(2);
		}
	}
	
	public void movimientoU() {
		int puedoMoverme;
		if ((puedoMoverme = GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y-1, x)) == 1) {
			if (!GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			}
			else if(GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				mostrarBomba();
			}												//Esta llamada a mostrar personaje se hace para despintar la posicion actual
			y--;											//para que luego se pinte la nueva.
			mostrarPersonajeUp();							//Esto lo he hecho porque he cambiado un poco la logica de como se pinta el personaje en la vista.
			System.out.println("Arriba");
		}
		else if (puedoMoverme == -1) { //Si le ha devuelto -1 es que se ha muerto
			dejarDeMostrarPersonaje();
			y--;											//para que luego se pinte la nueva.
			mostrarPersonajeUp();
			this.meHeMuerto(1);
		}
		else if (puedoMoverme == -2) { //Si le ha devuelto -2 es que se ha muerto por tocar un enemigo
			dejarDeMostrarPersonaje();
			y--;
			mostrarPersonaje();
			this.meHeMuerto(2);
		}
	}
	
	public void movimientoD() {
		int puedoMoverme;
		if ((puedoMoverme = GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y+1, x)) == 1) {
			if (!GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			} else if(GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				mostrarBomba();
			}
			y++;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
		else if (puedoMoverme == -1) { //Si le ha devuelto -1 es que se ha muerto por tocar una explosion
			dejarDeMostrarPersonaje();
			y++;
			mostrarPersonaje();
			this.meHeMuerto(1);
		}
		else if (puedoMoverme == -2) { //Si le ha devuelto -2 es que se ha muerto por tocar un enemigo
			dejarDeMostrarPersonaje();
			y++;
			mostrarPersonaje();
			this.meHeMuerto(2);
		}
	}
	
	protected abstract void meHeMuerto(int motivo);
	
	protected int getX() {
		return this.x;
	}
	
	protected int getY() {
		return this.y;
	}
	
	protected boolean coincideX(int pX) {
		if (this.x == pX) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected boolean coincideY(int pY) {
		if (this.y == pY ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected boolean quedanBombas() {
		if (this.numBombas > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected void sumarBomba() {
		this.numBombas++;
	}
	
	protected void restarBomba() {
		this.numBombas--;
	}
	
}
