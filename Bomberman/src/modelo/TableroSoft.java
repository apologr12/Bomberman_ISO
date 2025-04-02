package modelo;

import java.util.Random;

public class TableroSoft extends Tablero {
	public static final int probAparicion = 10;
	
	public TableroSoft(int pTipoPersonaje) {
		super(11, 17, pTipoPersonaje);
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
					numero = random.nextInt(3);
				}
				if (numero == 0) {														//En funcion del numero aleatorio generado, se pone un bloque de un tipo u otro
					super.ponerBloque("Vacio",i, j);
				}
				else if (numero == 1) {
					super.ponerBloque("Blando",i, j);
				}
				else if (numero == 2) {
					numero = 3; // Pintar enemigo soft
					int probabilidadEnemigo = random.nextInt(101);
					if (probabilidadEnemigo < probAparicion) {
						super.ponerEnemigo("EnemigoSoft", i, j);
					}
					else {
						super.ponerBloque("Vacio",i, j); // si no se pone un enemigo poner un bloque vacio sino es null pointer
						numero = 0; // actualizar correctamente la vista para que se vea el bloque correcto
					}
				}
					//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
				System.out.println("Prueba");																//en la interfaz
			}
			System.out.println("");
		}
		
		setChanged();
		notifyObservers(new Object[] {15}); //Notifica a la vista para que se anada como observer en todos los enemigos y estrategias
		super.iniciarTimersEnemigos(); //TODO Hay que meter los enemigos
	}


	

}