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
		if (Tablero.getTablero().puedoMoverme(x, y+1)){		//o se puede meter todo en uno directamente?
			y = y+1;
			mostrarPersonaje();
			System.out.println("Derecha");
		}
	}
	
	public void movimientoL() {
		if (Tablero.getTablero().puedoMoverme(x, y-1)){
			y = y-1;
			mostrarPersonaje();
			System.out.println("Izquierda");
		}
	}
	
	public void movimientoU() {
		if (Tablero.getTablero().puedoMoverme(x-1, y)){
			x = x-1;
			mostrarPersonaje();
			System.out.println("Arriba");
		}
	}
	
	public void movimientoD() {
		if (Tablero.getTablero().puedoMoverme(x+1, y)){
			x = x+1;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
	}
	
	public void plantarBomba() {
	    if (numBombas > 0) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        Tablero.getTablero().ponerBomba(x, y);
	        System.out.println("Bomba");
	        numBombas--;
	    }
	}
	
	public void mostrarPersonaje() {						//Public temporalmente para poder llamarlo desde el main?
		setChanged();
		notifyObservers(new Object[] {x, y});	
	}


}
