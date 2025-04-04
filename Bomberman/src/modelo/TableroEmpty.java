package modelo;

import java.util.Random;

public class TableroEmpty extends Tablero {

	private static final int probAparicion = 5; // 5%

	public TableroEmpty(int pTipoPersonaje) {
		super(11, 17, pTipoPersonaje);
	}

	@SuppressWarnings("deprecation")
	public void crearTablero() {
		Random random = new Random();

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				int numero;
				// Casillas de inicio (siempre vacías)
				if ((i == 0 && j == 0) || (i == 0 && j == 1) || (i == 1 && j == 0)) {
					super.ponerBloque("Vacio", i, j);
					numero = 0;  // bloque vacío
				} else {
					// Probabilidad de colocar enemigo (5%)
					int probabilidadEnemigo = random.nextInt(101);

					if (probabilidadEnemigo < probAparicion) {
						// Escoger aleatoriamente el tipo de enemigo
						int enemigoAleatorio = random.nextInt(3);
						if (enemigoAleatorio == 0) {
							// EnemigoClassic
							super.ponerEnemigo("EnemigoClassic", i, j);
							numero = 2;
						} else if (enemigoAleatorio == 1) {
							// EnemigoSoft
							super.ponerEnemigo("EnemigoSoft", i, j);
							numero = 3;
						} else {
							// EnemigoEmpty
							super.ponerEnemigo("EnemigoEmpty", i, j);
							numero = 4;
						}
					} else {
						// Si no toca enemigo, el bloque es vacío
						super.ponerBloque("Vacio", i, j);
						numero = 0;
					}
				}
				// Notificamos a la vista
				setChanged();
				notifyObservers(new Object[] {2, numero, i, j});
			}
			System.out.println(""); // Para depurar, salto de línea por fila
		}

		setChanged();
		notifyObservers(new Object[] {15}); // Se añade la vista como observer de enemigos/estrategias
		super.iniciarTimersEnemigos();
	}
	
	@Override
	public boolean comprobarFila(int pX, int bombaY, int bombaX) { //Comprueba si el personaje esta en el rango de la BombaUltra estando en la misma fila
		return true; //Como no hay BloqueDuros la explosion siempre alcanza el Personaje
	}
	
	@Override
	public boolean comprobarColumna(int pY, int bombaY, int bombaX) { //Comprueba si el personaje esta en el rango de la BombaUltra estando en la misma columna
		return true; //Como no hay BloqueDuros la explosion siempre alcanza el Personaje
	}
}
