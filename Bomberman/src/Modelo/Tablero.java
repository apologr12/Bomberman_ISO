package Modelo;

import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Tablero extends Observable {
	private Bloque[][] tablero;
	
	protected Tablero(int tamanoX, int tamanoY) {
		this.tablero = new Bloque[tamanoY][tamanoX];
	}
	
	protected void ponerBloqueVacio(int pY, int pX) {
		this.tablero[pY][pX] = new BloqueVacio();
	}
	
	protected void ponerBloqueBlando(int pY, int pX) {
		this.tablero[pY][pX] = new BloqueBlando();
	}
	
	protected void ponerBloqueDuro(int pY, int pX) {
		this.tablero[pY][pX] = new BloqueDuro();
	}
	
	protected void ponerBloqueBombaSimple(int pY, int pX) {
		this.tablero[pY][pX] = new BloqueBombaSimple(pY, pX);
	}
	
	
	protected boolean puedoMoverme(int x, int y) {
		
		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (this.tablero[y][x] instanceof BloqueVacio) {	
				return true;					
			}
			else if (this.tablero[y][x] instanceof BloqueBomba) {						//Futura muerte
				return false;
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
	    return (this.tablero[fila][col] instanceof BloqueBomba);
	}
	
	protected void compExplosion(int pX, int pY, int pTipo) {
		if (pTipo == 1) {												// Si no es BloqueDuro, es o blando, vacio o enemigo en un futuro
	    	if (!(this.tablero[pY][pX] instanceof BloqueDuro) && !(this.tablero[pY][pX + 1] instanceof BloqueBomba)) {
	        	explotarCelda(pY, pX);
	        	}
	        
	        if (pX < 16 && !(this.tablero[pY][pX + 1] instanceof BloqueDuro) && !(this.tablero[pY][pX + 1] instanceof BloqueBomba)) {
	        	explotarCelda(pY, pX + 1);
	        	}
	        
	        if (pX > 0 && !(this.tablero[pY][pX - 1] instanceof BloqueDuro) && !(this.tablero[pY][pX - 1] instanceof BloqueBomba)) {
	        	explotarCelda(pY, pX - 1);
	        	}

	        if (pY < 10 && !(this.tablero[pY + 1][pX] instanceof BloqueDuro) && !(this.tablero[pY + 1][pX] instanceof BloqueBomba)) {
	        	explotarCelda(pY + 1, pX);
	            }
	        
	        if (pY > 0 && !(this.tablero[pY - 1][pX] instanceof BloqueDuro) && !(this.tablero[pY - 1][pX] instanceof BloqueBomba)) {
	        	explotarCelda(pY-1,  pX);
	            }
	        } // if (pTipo == 1)
	 }
	
	protected void explotarCelda(int pY, int pX) {
	    this.tablero[pY][pX] = new BloqueExplosion(pX,pY); // Comprobar si estan las coordenadas bien
	    // Notificar a la vista que muestre explosión
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY});
        // INICIAR TIMER PARA MOSTRARLA DURANTE 2 SEGUNDOS?
    	// EN UN FUTURO COLOCAR EL BLOQUE VACIO QUITANDO EL BLOQUE EXPLOSION
	}
	
	protected abstract void ponerBomba(int fila, int col);
	
	protected void postExplosion(int pY,int pX) {
		this.tablero[pY][pX] = new BloqueVacio();
		
	    setChanged();
	    notifyObservers(new Object[] {4, pX, pY});
	}

	protected boolean esBloqueVacio(int pY, int pX) {
		if (this.tablero[pY][pX] instanceof BloqueVacio) {
			return true;
		}
		else {
			return false;
		}
	}
}
