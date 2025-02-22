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
	public void movimientoD() {
		if (Tablero.getTablero().puedoMoverme(x,y+1)){
			y=y+1;
			mostrarPersonaje();
			System.out.println("Ha llegado");
		}
	}
	public void movimientoI() {
		if (Tablero.getTablero().puedoMoverme(x,y-1)){
			y=y-1;
			mostrarPersonaje();
			System.out.println("Ha llegado");
		}
	}
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {x,y});	
	}
}
