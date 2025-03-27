package modelo;

import java.util.Random;

public class TableroEmpty extends Tablero {
	
	
	public TableroEmpty() {
		super(17, 11);
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