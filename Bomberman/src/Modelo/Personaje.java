package Modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Personaje extends Observable {
	private int x, y;
	private int numBombas;
	
	protected Personaje(int pX, int pY, int pBombas) {
		this.x = pX;
		this.y = pY;
		this.numBombas = pBombas;
	}
	
	protected abstract void dejarDeMostrarPersonaje();					//TODOS ESTOS METODOS ABSTRACTOS SON METODOS QUE SE LLAMAN DESDE LOS SUPER
																		//QUE EN FUNCION DE LA INSTANCIA DESDE LA QUE SE LLAMAN (LOS SUPERS) TIENEN QUE HACER UNA
	protected abstract void mostrarBomba();			//COSA U OTRA
	
	protected abstract void mostrarPersonajeUp();
	
	protected abstract void mostrarPersonajeLeft();
	
	protected abstract void mostrarPersonajeRight();
	
	public abstract void mostrarPersonaje(); //Temporalmente public para poder llamar desde el Main de la Vista
	
	public abstract void plantarBomba();
	
	public abstract void explosionBomba(int pX, int pY); //Este metodo se llama desde el BloqueBombaSimple o Ultra y es para indicarle al personaje que ha explotado
	
	
	public void movimientoL() {
		if (GestorTableros.getGestorTableros().getTablero().puedoMoverme(x-1, y)) {
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
		if (GestorTableros.getGestorTableros().getTablero().puedoMoverme(x+1, y)) {
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
		
		if (GestorTableros.getGestorTableros().getTablero().puedoMoverme(x, y-1)) {
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
		if (GestorTableros.getGestorTableros().getTablero().puedoMoverme(x, y+1)) {
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
