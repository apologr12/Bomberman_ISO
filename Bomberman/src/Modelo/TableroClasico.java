package Modelo;

import java.util.Random;

public class TableroClasico extends Tablero {
	
	
	public TableroClasico() {
		super(17, 11);
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
					super.ponerBloque("Vacio",i, j);
				}
				else if (numero == 1) {
					super.ponerBloque("Blando",i, j);
				}
				else if (numero == 2) {
					super.ponerBloque("Duro",i, j);
				}
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
				System.out.println("Prueba");																//en la interfaz
			}
			System.out.println("");
		}													
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean ponerBomba(int fila, int col) {  //Devuelve true si se ha podido poner una bomba, false si no
	    if (super.esBloqueVacio(fila, col)) {						//Se pone una bomba si no hay ya una bomba puesta
	        super.ponerBloque("BombaSimple",fila, col);
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