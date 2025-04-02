package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaBombaUltra extends EstrategiaBombas {
	public EstrategiaBombaUltra() {
	}
	
	@Override
	public boolean ponerBomba(int fila, int col, Bloque[][] tablero) {
		if (!tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        GenBloques.getGenBloques().generar("BombaSimple", fila, col);
	        System.out.println("Bomba");
	        setChanged();
	        notifyObservers(new Object[] { 1, col, fila, 2});
	        return true;
	    }
	    else {
	    	return false;
	    }
	}

	@Override
	protected void explotarCelda(int pY, int pX, Bloque[][] tablero) { //en el notify agrega un 2 que va a ser la manera para que sepa la vista que tipo de explosion
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compExplosion(int pY, int pX, Bloque[][] tablero) {
		// TODO Auto-generated method stub
		
	}

}
