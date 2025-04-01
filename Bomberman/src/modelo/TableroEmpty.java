package modelo;

import java.util.Random;

public class TableroEmpty extends Tablero {
	
	
	public TableroEmpty(int pTipoPersonaje) {
		super(11, 17, pTipoPersonaje);
	}
	
	
	@SuppressWarnings("deprecation")
	public void crearTablero() {	
		//Este metodo se llama cuando se pulsa el boton para iniciar partida
		for (int i = 0; i < 11; i++) {													//Generacion del tablero con solo bloques vacios
			for (int j = 0; j < 17; j++) {
				super.ponerBloque("Vacio", i, j);
				//System.out.print(i + "" + j + " "); Debugging
				setChanged();
				notifyObservers(new Object[] {2, 0, i, j});							//Notifica a la vista para que ponga el bloque vacio
				System.out.println("Prueba");																//en la interfaz
			}
			System.out.println("");
		}
		
		setChanged();
		notifyObservers(new Object[] {15}); //Notifica a la vista para que se anada como observer en todos los enemigos y estrategias
		//super.iniciarTimersEnemigos(); //TODO Hay que meter los enemigos
	}
	
	
	

}