package Modelo;

public class BloqueDuro extends Bloque{
	public BloqueDuro(int pY, int pX) {
		super(pY,pX);
	}
	public boolean esDestructible() {
		return false;
	}
}
