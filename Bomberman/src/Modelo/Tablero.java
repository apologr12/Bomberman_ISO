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
		
		if (x >= 0 && x < 17 && y >= 0 && y < 11) {		//Dentro del tablero (aqui el problema era que se ponia <=17)
			if (tablero[y][x] instanceof BloqueVacio) {									//No se por que funciona bien si en java el primer corchete es la coordenada Y
				return true;															//y el segundo corchete representa la coordenada X
			}
			else {											//No es bloque vacio
				return false;
			}
		} 
		else {												//Fuera del tablero
			return false;
		}
	}
	
	public void ponerBomba(int fila, int col) {
	    if (this.tablero[fila][col] instanceof BloqueVacio) {						//No entiendo la necesidad de comprobar el 'instanceof' aqui
	        // de momento le damos un rangoEx de 1 pero en un futuro en base a el int que nos indica el personaje tendremos que darle mas rangoEx.
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
	
	public void compExplosion(boolean pEstaVivo, int pX, int pY, int pTipo) {
		if (pTipo == 1) {														//Bomba basica (area 1)
			if (this.tablero[pY][pX+1] instanceof BloqueBlando) {
				this.tablero[pY][pX+1] = new BloqueVacio();
				setChanged();
		        notifyObservers(new Object[] {3, pY, pX + 1}); 
			}
			
		}
	}



}