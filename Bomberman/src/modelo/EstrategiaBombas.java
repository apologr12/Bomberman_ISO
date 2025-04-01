package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class EstrategiaBombas extends Observable {
	public abstract boolean ponerBomba(int fila, int col, Bloque[][] tablero);
	
	protected abstract void explotarCelda(int pY, int pX, Bloque[][] tablero);
	
	public abstract void compExplosion(int pY, int pX, Bloque[][] tablero);
}
