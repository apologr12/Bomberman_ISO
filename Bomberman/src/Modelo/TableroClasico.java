package Modelo;

import java.util.Random;

public class TableroClasico extends Tablero {
	
	private static TableroClasico instancia;
	
	private TableroClasico() {
		super(17, 11);
	}
	
	public static TableroClasico getInstancia() {
        if (instancia == null) {
            instancia = new TableroClasico();
        }
        return instancia;
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
					super.ponerBloqueVacio(i, j);
				}
				else if (numero == 1) {
					super.ponerBloqueBlando(i, j);
				}
				else if (numero == 2) {
					super.ponerBloqueDuro(i, j);
				}
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
																						//en la interfaz
			}
			System.out.println("");
		}													
	}
	
	
	@SuppressWarnings("deprecation")
	public void ponerBomba(int fila, int col) {
	    if (super.esBloqueVacio(fila, col)) {						//Se pone una bomba si no hay ya una bomba puesta
	        super.ponerBloqueBombaSimple(fila, col);
	        setChanged();
	        notifyObservers(new Object[] { 1, col, fila }); 

	    }
	    
	}

}