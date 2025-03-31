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
	
	public void choqueEnemigo(int pY, int pX) { //Este metodo se llama desde el enemigo, revisa si el personaje esta en la posicion a donde el enemigo se va a mover,
		if (coincideX(pX) && coincideY(pY)) {   // y si esta ahi, lo mata
			System.out.println("Enemigo tocado.");
			System.exit(1);
		}
		
	} 
	
	public void movimientoL() {
		if (GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y, x-1)) {
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
	}
	
	public void movimientoR() {
		if (GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y, x+1)) {
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
	}
	
	public void movimientoU() {
		
		if (GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y-1, x)) {
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
	}
	
	public void movimientoD() {
		if (GestorTableros.getGestorTableros().getTablero().puedoMovermeP(y+1, x)) {
			if (!GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			} else if(GestorTableros.getGestorTableros().getTablero().hayBombaEn(y, x)) {
				mostrarBomba();
			}
			y++;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
	}
	
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
