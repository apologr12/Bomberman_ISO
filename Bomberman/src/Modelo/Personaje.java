package Modelo;

import java.util.Observable;

public class Personaje extends Observable{
	public static Personaje miPersonaje = new Personaje();
	private int maxBombas;
	private boolean estaVivo;
	private int x,y;
	
	private Personaje() {
		maxBombas=10;
		estaVivo=true;
		x=0;
		y=0;
		
	}
	public static Personaje getPersonaje() {
		return miPersonaje;
	}
	public void movimientoR() {
		if (Tablero.getTablero().puedoMoverme(x,y+1)){
			y=y+1;
			mostrarPersonaje();
			System.out.println("Derecha");
		}
	}
	public void movimientoL() {
		if (Tablero.getTablero().puedoMoverme(x,y-1)){
			y=y-1;
			mostrarPersonaje();
			System.out.println("Izquierda");
		}
	}
	public void movimientoU() {
		if (Tablero.getTablero().puedoMoverme(x-1,y)){
			x=x-1;
			mostrarPersonaje();
			System.out.println("Arriba");
		}
	}
	public void movimientoD() {
		if (Tablero.getTablero().puedoMoverme(x+1,y)){
			x=x+1;
			mostrarPersonaje();
			System.out.println("Abajo");
		}
	}
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {x,y});	
	}
	public void plantarBomba() {
	    if (maxBombas > 0) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        Tablero.getTablero().ponerBomba(x, y);
	        System.out.println("Bomba");
	        maxBombas--;
	    }
	}

}
