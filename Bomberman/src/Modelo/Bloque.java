package Modelo;

import java.util.Observable;

public abstract class Bloque extends Observable{
	private int x;
	private int y;
	
	protected Bloque(int pY,int pX) {
		x=pX;
		y=pY;
	}
	protected int getX() {return x;}
	protected int getY() {return y;}
	
	public abstract boolean esDestructible();
}
