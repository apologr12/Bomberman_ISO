package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class EstrategiaAtaque extends Observable {

	public abstract boolean atacar(int fila, int col, Bloque[][] tablero, String orientacion);

	protected abstract void explotarCelda(int pY, int pX, Bloque[][] tablero, String orientacion);
	
	public abstract void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion);
}
