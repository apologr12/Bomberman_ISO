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
	public void crearTablero() {										//Este metodo sera llamado cuando se pulse el boton para iniciar partida
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
				//System.out.println(numero);
				setChanged();
				notifyObservers(new Object[] {numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
																						//en la interfaz
			}
		}													
	}
	public boolean puedoMoverme(int x, int y) {
		
		if(x>=0 && x<=17 && y>=0 && y<=11) {
			if (tablero[x][y] instanceof BloqueVacio) {
				return true;
			}
			else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void ponerBomba(int fila, int col) {
	    if (this.tablero[fila][col] instanceof BloqueVacio) {
	        // de momento le damos un rangoEx de 1 pero en un futuro en base a el int que nos indica el personaje tendremos que darle mas rangoEx.
	        BloqueBomba bomba = new BloqueBomba(1);
	        this.tablero[fila][col] = bomba;
	        setChanged();
	        notifyObservers(new Object[] { 3, fila, col }); 

	    }
	    
	}
	public boolean hayBombaEn(int fila, int col) {
	    // comprobar si en la matriz hay un BloqueBomba
	    return (this.tablero[fila][col] instanceof BloqueBomba);
	}



}