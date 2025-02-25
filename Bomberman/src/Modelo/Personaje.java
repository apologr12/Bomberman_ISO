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
	
	public void movimientoR() {								//Es necesario realmente dividir el movimiento del personaje en 4 metodos
		if (Tablero.getTablero().puedoMoverme(x+1, y)){		//o se puede meter todo en uno directamente?
			x++;
			mostrarPersonaje();
			System.out.println("Derecha");
		}
	}
	
	public void movimientoL() {
		if (Tablero.getTablero().puedoMoverme(x-1, y)){
			x--;
			mostrarPersonaje();
			System.out.println("Izquierda");
		}
	}
	
	public void movimientoU() {
		if (Tablero.getTablero().puedoMoverme(x, y-1)){
			y--;
			mostrarPersonaje();
			System.out.println("Arriba");
		}
	}
	
	public void movimientoD() {
		if (Tablero.getTablero().puedoMoverme(x, y+1)){
			y++;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
	}
	
	public void plantarBomba() {
	    if (numBombas > 0) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        Tablero.getTablero().ponerBomba(y, x);
	        System.out.println("Bomba");
	        numBombas--;
	    }
	}
	
	public void explosionBomba(int pX, int pY, int pTipo) {
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
		notifyObservers(new Object[] {1, x, y});	
	}


}
