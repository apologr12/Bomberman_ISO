package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class EstrategiaAtaque extends Observable {

	public abstract boolean atacar(int fila, int col, Bloque[][] tablero, String orientacion, String quien); //Los 2 ultimos parametros son concretos para el modo de juego adicional (Sprint 3)

	protected abstract void explotarCelda(int pY, int pX, Bloque[][] tablero, String orientacion, String quien); //Los 2 ultimos parametros son concretos para el modo de juego adicional (Sprint 3)
	
	public abstract void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion, String quien); //Los 2 ultimos parametros son concretos para el modo de juego adicional (Sprint 3)
}
