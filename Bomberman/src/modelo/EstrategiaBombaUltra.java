package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaBombaUltra extends EstrategiaAtaque {
	public EstrategiaBombaUltra() {
	}
	
	@Override
	public boolean atacar(int fila, int col, Bloque[][] tablero, String orientacion) {
		if (!tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        tablero[fila][col] = GenBloques.getGenBloques().generar("BombaUltra", fila, col, "");
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
	    notifyObservers(new Object[] {6, pX, pY, 2});		
	}

	@Override
	public void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion) { //Explota las celdas alcanzadas por la explosion de la BombaUltra
		explotarCelda(pY, pX, tablero);
		
		int actX=pX+1;
		while (actX <= 16 && tablero[pY][actX].esDestructible()) {
        	explotarCelda(pY, actX, tablero);
        	actX++;
        }
		
		actX=pX-1;
        while (actX >= 0 && tablero[pY][actX].esDestructible()) {
        	explotarCelda(pY, actX, tablero);
        	actX--;
       	}
        
        int actY=pY+1;
        while (actY <= 10 && tablero[actY][pX].esDestructible()) {
        	explotarCelda(actY, pX, tablero);
        	actY++;
        }
        
        actY=pY-1;
        while (actY >= 0 && tablero[actY][pX].esDestructible()) {
        	explotarCelda(actY,  pX, tablero);
        	actY--;
        }
	}

}
