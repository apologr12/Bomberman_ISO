package Modelo;

import java.util.Observable;
import java.util.Random;

import javax.swing.JLabel;

public class Tablero extends Observable {
	
	private static Tablero miTablero = new Tablero();
	
	private Bloque[][] tablero;
	
	
	private Tablero(){
		this.tablero = new Bloque[11][17];
	}
	
	public static Tablero getTablero() {
		return miTablero;
	}
	
	@SuppressWarnings("deprecation")
	public void crearTablero() {	
		//Este metodo sera llamado cuando se pulse el boton para iniciar partida
		Random random = new Random();									//Actualmente es llamado desde main para las pruebas
		for (int i = 0; i < 11; i++) {													//Generacion del tablero de forma aleatoria
			for (int j = 0; j < 17; j++) {
				int numero;
				if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {	//Las tres casillas donde empiezan el personaje tienen que ser
					numero = 0;															//si o si vacias porque si no se podria quedar bloqueado
				}
				else if (i % 2 != 0 && j % 2 != 0) {
					numero = 2;
				}
				else {
					numero = random.nextInt(2);
				}
				
				if (numero == 0) {														//En funcion del numero aleatorio generado, se pone un bloque de un tipo u otro
					this.tablero[i][j] = new BloqueVacio();
				}
				else if (numero == 1) {
					this.tablero[i][j] = new BloqueBlando();
				}
				else if (numero == 2) {
					this.tablero[i][j] = new BloqueDuro();
				}
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
																						//en la interfaz
			}
			System.out.println("");
		}													
	}
	public boolean puedoMoverme(int x, int y) {
		
		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (tablero[y][x] instanceof BloqueVacio) {	
				return true;					
			}
			else if(tablero[y][x] instanceof BloqueExplosion){						//Futura muerte
				return false;
			} else { 																//Bloque solido no se puede traspasar
				return false;
			}
		} 
		else {												//Fuera del tablero
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void ponerBomba(int fila, int col) {
	    if (this.tablero[fila][col] instanceof BloqueVacio) {						//Se pone una bomba si no hay ya una bomba puesta
	        BloqueBomba bomba = new BloqueBomba(1, col, fila);
	        this.tablero[fila][col] = bomba;
	        setChanged();
	        notifyObservers(new Object[] { 1, col, fila }); 

	    }
	    
	}
	public boolean hayBombaEn(int fila, int col) {
	    // comprobar si en la matriz hay un BloqueBomba
	    return (this.tablero[fila][col] instanceof BloqueBomba);
	}
	
	@SuppressWarnings("deprecation")
	public void compExplosion(boolean pEstaVivo, int pX, int pY, int pTipo) {
		if (pTipo == 1) {												// Si no es BloqueDuro, es o blando, vacio o enemigo en un futuro
	    	if (!(this.tablero[pY][pX] instanceof BloqueDuro)) {
	        	explotarCelda(pY, pX);
	        	}
	        
	        if (pX < 16 && !(this.tablero[pY][pX + 1] instanceof BloqueDuro)) {
	        	explotarCelda(pY, pX + 1);
	        	}
	        
	        if (pX > 0 && !(this.tablero[pY][pX - 1] instanceof BloqueDuro)) {
	        	explotarCelda(pY, pX - 1);
	        	}

	        if (pY < 10 && !(this.tablero[pY + 1][pX] instanceof BloqueDuro)) {
	        	explotarCelda(pY + 1, pX);
	            }
	        
	        if (pY > 0 && !(this.tablero[pY - 1][pX] instanceof BloqueDuro)) {
	        	explotarCelda(pY-1,  pX);
	            }
	        } // if (pTipo == 1)
	    }
	private void explotarCelda(int pY, int pX) {
	    this.tablero[pY][pX] = new BloqueExplosion(pX,pY); // Comprobar si estan las coordenadas bien
	    // Notificar a la vista que muestre explosión
	    setChanged();
	    notifyObservers(new Object[] {6, pX, pY});
        // INICIAR TIMER PARA MOSTRARLA DURANTE 2 SEGUNDOS?
    	// EN UN FUTURO COLOCAR EL BLOQUE VACIO QUITANDO EL BLOQUE EXPLOSION
	}
	public void postExplosion(int pY,int pX) {
		this.tablero[pY][pX] = new BloqueVacio(); // Comprobar si estan las coordenadas bien
	    setChanged();
	    notifyObservers(new Object[] {4, pX, pY});
	}

}