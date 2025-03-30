package modelo;

import java.util.Random;

public class TableroClasico extends Tablero {
	
	
	public TableroClasico() {
		super(17, 11);
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
				else if (i % 2 != 0 && j % 2 != 0) {
					numero = 5;
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
					int probabilidadEnemigo = random.nextInt(101);
					if (probabilidadEnemigo < 10) {
						super.ponerEnemigo("EnemigoClassic",i, j);
					}else {
						super.ponerBloque("Vacio",i, j); // si no se pone un enemigo poner un bloque vacio sino es null pointer
						numero = 0; // actualizar correctamente la vista para que se vea el bloque correcto
					}

				}
				else if (numero == 5) {
					super.ponerBloque("Duro",i, j);
				}
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});							//Notifica a la vista para que ponga el tipo de bloque correspondiente
				System.out.println("Prueba");																//en la interfaz
			}
			System.out.println("");
		}
		this.iniciarTimersEnemigos();
	}
	
	

}