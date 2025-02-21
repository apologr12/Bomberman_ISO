package Modelo;

import java.util.Observable;

public class Tablero extends Observable{
	public static Tablero miTablero=new Tablero();
	private Bloque [][] bloques;
	private Tablero(){
		bloques=new Bloque[11][17];
	}

}