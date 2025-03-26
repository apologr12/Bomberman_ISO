package modelo;

public class BloqueDuro extends Bloque{
	public BloqueDuro(int pY, int pX) {
		super(pY,pX);
	}
	public boolean esDestructible() {
		return false;
	}
	
	@Override
	public boolean puedoMoverme() {
    	return false;
    }
}
