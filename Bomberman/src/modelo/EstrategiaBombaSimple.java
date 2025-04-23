package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaBombaSimple extends EstrategiaAtaque {
	public EstrategiaBombaSimple() {
	}


	@Override
	public boolean atacar(int fila, int col, Bloque[][] tablero, String orientacion) {
		if (!tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        tablero[fila][col] = GenBloques.getGenBloques().generar("BombaSimple", fila, col, "");
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
	protected void explotarCelda(int pY, int pX, Bloque[][] tablero, String orientacion) {
		/* 1.  Si la celda es un Boss, aplicamos daño */
		if (tablero[pY][pX].esBoss()) {
			EnemigoBoss boss = (EnemigoBoss) tablero[pY][pX];
			boss.recibirDanio();       // quita un punto de vida
			return;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
								// mas abajo por una explosion donde esta el boss
		}

		/* 2.  Si es un enemigo lo matamos directamente */
		if (tablero[pY][pX].eresExplosion() || tablero[pY][pX].esEnemigo()) {
			tablero[pY][pX].pararTimer();
		}
	    tablero[pY][pX] = GenBloques.getGenBloques().generar("Explosion", pY, pX, "");
	    // Notificar a la vista que muestre explosi�n
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY, 1});		
	}


	@Override
	public void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion) {
		explotarCelda(pY, pX, tablero, orientacion);

        if (pX < 16 && tablero[pY][pX + 1].esDestructible()) {
        	explotarCelda(pY, pX + 1, tablero, orientacion);
        }

        if (pX > 0 && tablero[pY][pX - 1].esDestructible()) {
        	explotarCelda(pY, pX - 1, tablero, orientacion);
       	}

        if (pY < 10 && tablero[pY + 1][pX].esDestructible()) {
        	explotarCelda(pY + 1, pX, tablero,orientacion);
        }

        if (pY > 0 && tablero[pY - 1][pX].esDestructible()) {
        	explotarCelda(pY-1,  pX, tablero, orientacion);
        }
	}

}
