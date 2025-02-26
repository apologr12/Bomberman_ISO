package Modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Personaje extends Observable{
	public static Personaje miPersonaje = new Personaje();
	private int numBombas;
	private boolean estaVivo;
	private int x, y;
	
	private Personaje() {
		numBombas = 10;
		estaVivo = true;
		x = 0;
		y = 0;
		
	}
	public static Personaje getPersonaje() {
		return miPersonaje;
	}
	
	public void movimientoR() {
		if (Tablero.getTablero().puedoMoverme(x+1, y)) {
			if (!Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			}
			else if(Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonajePeroBomba();
			}
			x++;
			mostrarPersonajeRight();
			System.out.println("Derecha");
		}
	}
	
	public void movimientoL() {
		if (Tablero.getTablero().puedoMoverme(x-1, y)) {
			if (!Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			} 
			else if(Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonajePeroBomba();
			}
			x--;
			mostrarPersonajeLeft();
			System.out.println("Izquierda");
		}
	}
	
	public void movimientoU() {
		
		if (Tablero.getTablero().puedoMoverme(x, y-1)) {
			if (!Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			}
			else if(Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonajePeroBomba();
			}												//Esta llamada a mostrar personaje se hace para despintar la posicion actual
			y--;											//para que luego se pinte la nueva.
			mostrarPersonajeUp();							//Esto lo he hecho porque he cambiado un poco la logica de como se pinta el personaje en la vista.
			System.out.println("Arriba");
		}
	}
	
	public void movimientoD() {
		if (Tablero.getTablero().puedoMoverme(x, y+1)) {
			if (!Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonaje();
			} else if(Tablero.getTablero().hayBombaEn(y, x)) {
				dejarDeMostrarPersonajePeroBomba();
			}
			y++;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
	}
	
	public void plantarBomba() {
	    if (numBombas > 0) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        Tablero.getTablero().ponerBomba(y, x);
	        System.out.println("Bomba");
	        this.numBombas--;
	    }
	}
	
	public void explosionBomba(int pX, int pY, int pTipo) {
		this.numBombas++;
		if (pX == this.x && pY == this.y) {
			this.estaVivo = false;
		}
		else if (pTipo == 1) {								//Bomba basica (tipo 1)
			if (pX + 1 == this.x && pY == this.y) {
				this.estaVivo = false;
			}
			else if (pX - 1 == this.x && pY == this.y) {
				this.estaVivo = false;
			}
			else if (pY + 1 == this.y && pX == this.x) {
				this.estaVivo = false;
			}
			else if (pY - 1 == this.y && pX == this.x) {
				this.estaVivo = false;
			}
		}
		else if (pTipo == 2) {
			//TODO Tener en cuenta que si el personaje esta al  otro lado de un bloque duro la bomba no le mata
		}
		Tablero.getTablero().compExplosion(this.estaVivo, pX, pY, pTipo);
		
	}
	
	public void mostrarPersonaje() {						//Public temporalmente para poder llamarlo desde el main?
		setChanged();
		notifyObservers(new Object[] {3, x, y});	
	}
	
	private void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, x, y});
	}
	private void dejarDeMostrarPersonajePeroBomba() {
		setChanged();
		notifyObservers(new Object[] {7, x, y});
	}
	private void mostrarPersonajeUp() {						//Ns si un metodo para cada movimiento es correcto (Se puede cambiar)
		setChanged();
		notifyObservers(new Object[] {8, x, y});	
	}
	private void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, x, y});	
	}
	private void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, x, y});	
	}
}
