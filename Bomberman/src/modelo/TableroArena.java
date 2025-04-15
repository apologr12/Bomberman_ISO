package modelo;

@SuppressWarnings("deprecation")
public class TableroArena extends Tablero {

	protected TableroArena(int pTipoPersonaje) {
		super(11, 17, pTipoPersonaje);
	}

	@Override
	public void crearTablero() {
		
		
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				super.ponerBloque("Vacio", i, j);
				//No hace falta notificar nada porque es vacio
			}
		}

		for (int i = 0; i < 11; i++) {
			super.ponerBloque("Duro", i, 0); //Columna de la izquierda
			setChanged();
			notifyObservers(new Object[] {2, 5, i, 0});			//El 5 indica que se esta poniendo un bloque duro
		}
		
		for (int i = 0; i < 11; i++) {
			super.ponerBloque("Duro", i, 16); //Columna de la derecha
			setChanged();
			notifyObservers(new Object[] {2, 5, i, 16});
		}
		
		for (int j = 1; j < 17; j++) {
			super.ponerBloque("Duro", 0, j); //Fila de arriba
			setChanged();
			notifyObservers(new Object[] {2, 5, 0, j});
		}
		
		for (int j = 1; j < 17; j++) {
			super.ponerBloque("Duro", 10, j); //Fila de abajo
			setChanged();
			notifyObservers(new Object[] {2, 5, 10, j});
		}
		
		setChanged();
		notifyObservers(new Object[] {15}); // Se añade la vista como observer de enemigos/estrategias
	}

	@Override
	public boolean comprobarFila(int pX, int bombaY, int bombaX) {
		return true; //Como no hay BloqueDuros la explosion siempre alcanza el Personaje
	}

	@Override
	public boolean comprobarColumna(int pY, int bombaY, int bombaX) {
		return true; //Como no hay BloqueDuros la explosion siempre alcanza el Personaje
	}

}
