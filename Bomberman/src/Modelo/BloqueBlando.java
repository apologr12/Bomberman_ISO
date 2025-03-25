package Modelo;

public class BloqueBlando extends Bloque {
	public BloqueBlando(int pY, int pX) {
		super(pY,pX);
	}
	public boolean esDestructible() {
		return true;
	}
	
	@Override
	public boolean puedoMoverme() {
    	return false;
    }
}
