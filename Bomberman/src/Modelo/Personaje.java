package Modelo;

import java.util.Observable;

public class Personaje extends Observable{
	public static Personaje miPersonaje=new Personaje();
	private int maxBombas;
	private boolean estaVivo;
	private int x,y;
	
	private Personaje() {
		maxBombas=10;
		estaVivo=true;
		x=0;
		y=0;
		
	}
}
