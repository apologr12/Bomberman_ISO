package modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Tablero extends Observable {
	private Bloque[][] tablero;
	
	public abstract void crearTablero();
	
	
	protected Tablero(int tamanoX, int tamanoY) {
		this.tablero = new Bloque[tamanoY][tamanoX];
	}
	protected void ponerBloque(String pTipo, int pY, int pX) {
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar(pTipo, pY, pX);
	}

	
	
	protected boolean puedoMoverme(int x, int y) {
		
		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (this.tablero[y][x].eresExplosion()) { //Futura muerte
				System.exit(1);
				return false;
			}
			else if (this.tablero[y][x].puedoMoverme()) {	
				return true;					
			}
			else { 																//Bloque solido no se puede traspasar
				return false;
			}
		} 
		else {												//Fuera del tablero
			return false;
		}
	}
	
	protected boolean hayBombaEn(int fila, int col) {
	    // comprobar si en la matriz hay un BloqueBomba
	    return (this.tablero[fila][col].eresBomba());
	}
	
	protected void compExplosionSimple(int pX, int pY) {
			// Si no es BloqueDuro, es o blando, vacio o enemigo en un futuro
	    	
		//Se explota la casilla donde estaba la bomba
	        explotarCelda(pY, pX);
	        
	        if (pX < 16 && this.tablero[pY][pX + 1].esDestructible()) {
	        	explotarCelda(pY, pX + 1);
	        	}
	        
	        if (pX > 0 && this.tablero[pY][pX - 1].esDestructible()) {
	        	explotarCelda(pY, pX - 1);
	        	}

	        if (pY < 10 && this.tablero[pY + 1][pX].esDestructible()) {
	        	explotarCelda(pY + 1, pX);
	            }
	        
	        if (pY > 0 && this.tablero[pY - 1][pX].esDestructible()) {
	        	explotarCelda(pY-1,  pX);
	            }
	 }
	
	protected void explotarCelda(int pY, int pX) {
		if(this.tablero[pY][pX].eresExplosion()) {
			this.tablero[pY][pX].pararTimer();
		}
	    this.tablero[pY][pX] = GenBloques.getGenBloques().generar("Explosion", pY, pX);
	    // Notificar a la vista que muestre explosión
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY});
	}
	
	protected void postExplosion(int pY,int pX) {
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar("Vacio", pY, pX);
		
	    setChanged();
	    notifyObservers(new Object[] {4, pX, pY});
	}

	
	@SuppressWarnings("deprecation")
	public boolean ponerBomba(int fila, int col) {  //Devuelve true si se ha podido poner una bomba, false si no
	    if (!this.tablero[fila][col].eresBomba()) {						//Se pone una bomba si no hay ya una bomba puesta
	        ponerBloque("BombaSimple",fila, col);
	        System.out.println("Bomba");
	        setChanged();
	        notifyObservers(new Object[] { 1, col, fila });
	        return true;
	    }
	    else {
	    	return false;
	    }
	}
}
