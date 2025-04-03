package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaBombaUltra extends EstrategiaBombas {
	public EstrategiaBombaUltra() {
	}
	
	@Override
	public boolean ponerBomba(int fila, int col, Bloque[][] tablero) {
		if (!tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        tablero[fila][col] = GenBloques.getGenBloques().generar("BombaUltra", fila, col);
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
		if (tablero[pY][pX].eresExplosion() || tablero[pY][pX].esEnemigo()) {
			tablero[pY][pX].pararTimer();
		}
	    tablero[pY][pX] = GenBloques.getGenBloques().generar("Explosion", pY, pX);
	    // Notificar a la vista que muestre explosi�n
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY, 2});		
	}

	@Override
	public void compExplosion(int pY, int pX, Bloque[][] tablero) {
		explotarCelda(pY, pX, tablero);
		
		int actX=pX+1;
		while (actX <= 16 && tablero[pY][actX].esDestructible()) {
        	explotarCelda(pY, actX, tablero);
        	if(GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, actX)) { //Revisar si esta el personaje
        		System.exit(1); //Ahora mismo si no se le llama desde aqui no se como hacer que el personaje sepa si coincide con la explosion
        	}
        	actX++;
        }
		
		actX=pX-1;
        while (actX >= 0 && tablero[pY][actX].esDestructible()) {
        	explotarCelda(pY, actX, tablero);
        	if(GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, actX)) { //Revisar si esta el personaje
        		System.exit(1); //Ahora mismo si no se le llama desde aqui no se como hacer que el personaje sepa si coincide con la explosion
        	}
        	actX--;
       	}
        
        int actY=pY+1;
        while (actY <= 10 && tablero[actY][pX].esDestructible()) {
        	explotarCelda(actY, pX, tablero);
        	if(GestorPersonajes.getGestorPersonajes().getPersonaje().choque(actY, pX)) { //Revisar si esta el personaje
        		System.exit(1); //Ahora mismo si no se le llama desde aqui no se como hacer que el personaje sepa si coincide con la explosion
        	}
        	actY++;
        }
        
        actY=pY-1;
        while (actY >= 0 && tablero[actY][pX].esDestructible()) {
        	explotarCelda(actY,  pX, tablero);
        	if(GestorPersonajes.getGestorPersonajes().getPersonaje().choque(actY, pX)) { //Revisar si esta el personaje
        		System.exit(1); //Ahora mismo si no se le llama desde aqui no se como hacer que el personaje sepa si coincide con la explosion
        	}
        	actY--;
        }
	}

}
