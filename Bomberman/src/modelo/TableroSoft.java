package modelo;

import java.util.Random;

public class TableroSoft extends Tablero {
	
	
	public TableroSoft() {
		super(11, 17);
	}
	
	
	@SuppressWarnings("deprecation")
	public void crearTablero() {	
		//Este metodo se llama cuando se pulsa el boton para iniciar partida
		Random random = new Random();
		for (int i = 0; i < 11; i++) {													//Generacion del tablero de forma aleatoria
			for (int j = 0; j < 17; j++) {
				int numero;
				if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {	//Las tres casillas donde empiezan el personaje tienen que ser
					numero = 0;															//si o si vacias porque si no se podria quedar bloqueado
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
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
				System.out.println("Prueba");																//en la interfaz
			}
			System.out.println("");
		}													
	}
	
	
	

}