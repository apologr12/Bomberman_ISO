package modelo;

public class BloqueDisparo extends Bloque {
	private String orientacion;
	
	protected BloqueDisparo(int pY, int pX) {
		super(pY, pX);
	}

	@Override
	public boolean esDestructible() {
		return false;
	}
	
	@Override
	public boolean eresDisparo() {
		return true;
	}
}
