package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaBombaSimple extends EstrategiaBombas {
	public EstrategiaBombaSimple() {
	}


	@Override
	public boolean ponerBomba(int fila, int col, Bloque[][] tablero) {
		if (!tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        tablero[fila][col] = GenBloques.getGenBloques().generar("BombaSimple", fila, col);
	        System.out.println("Bomba"); //Debugging
	        setChanged();
	        notifyObservers(new Object[] { 1, col, fila, 1});
	        return true;
	    }
	    else {
	    	return false;
	    }
	}

	@Override
	protected void explotarCelda(int pY, int pX, Bloque[][] tablero) {
		if (tablero[pY][pX].eresExplosion() || tablero[pY][pX].esEnemigo()) {
			tablero[pY][pX].pararTimer();
		}
	    tablero[pY][pX] = GenBloques.getGenBloques().generar("Explosion", pY, pX);
	    // Notificar a la vista que muestre explosi�n
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY, 1});		
	}


	@Override
	public void compExplosion(int pY, int pX, Bloque[][] tablero) {
		explotarCelda(pY, pX, tablero);

        if (pX < 16 && tablero[pY][pX + 1].esDestructible()) {
        	explotarCelda(pY, pX + 1, tablero);
        }

        if (pX > 0 && tablero[pY][pX - 1].esDestructible()) {
        	explotarCelda(pY, pX - 1, tablero);
       	}

        if (pY < 10 && tablero[pY + 1][pX].esDestructible()) {
        	explotarCelda(pY + 1, pX, tablero);
        }

        if (pY > 0 && tablero[pY - 1][pX].esDestructible()) {
        	explotarCelda(pY-1,  pX, tablero);
        }
	}

}
