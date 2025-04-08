package modelo;

import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

@SuppressWarnings("deprecation")
public abstract class Tablero extends Observable {
	private Bloque[][] tablero;
	private EstrategiaBombas estrategiaBombas;

	public abstract void crearTablero();


	protected Tablero(int tamanoY, int tamanoX, int pTipoPersonaje) {
		this.tablero = new Bloque[tamanoY][tamanoX];
		if (pTipoPersonaje == 1) {
			this.estrategiaBombas = new EstrategiaBombaSimple();
		}
		else {
			this.estrategiaBombas = new EstrategiaBombaUltra();
		}
	}
	protected void ponerBloque(String pTipo, int pY, int pX) {
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar(pTipo, pY, pX);
	}

	protected void ponerEnemigo(String pTipo, int pY, int pX) {
		this.tablero[pY][pX] = GenEnemigos.getGenEnemigos().generar(pTipo, pY, pX);
	}


	protected int puedoMovermeP(int y, int x) {

		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (this.tablero[y][x].eresExplosion()) {
				return -1; //Devuelve -1 si se ha muerto porque ha tocado una explosion
			}
			else if (this.tablero[y][x].esEnemigo()) {
				return -2; //Devuelve -2 si se ha muerto porque ha tocado un enemigo
			}
			else if (this.tablero[y][x].puedoMoverme()) {
				return 1; //Devuelve 1 si puede moverse
			}
			else { 																//Bloque solido no se puede traspasar
				return 0; //Devuelve 0 si no puede moverse
			}
		}
		else {
			return 0;
		}
	}
	
	protected boolean puedoMovermeE(int y, int x) { //Este metodo solo se llama desde los enemigos y les dice si pueden moverse o no
		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (this.tablero[y][x].eresExplosion()) {//Aqui se llama a eresExplosion y no solo a puedoMoverme porque el enemigo con su "IA" no puede
													//moverse a un bloque explosion
				return false;
			}
			else if (this.tablero[y][x].puedoMoverme()) {
				return true;
			}
			else { 																//Bloque solido no se puede traspasar
				return false;
			}
		}
		else {												//Fuera del tablero
			return false;
		}
	}

	protected boolean hayBombaEn(int fila, int col) {
	    // comprobar si en la matriz hay un BloqueBomba
	    return (this.tablero[fila][col].eresBomba());
	}
	
	protected boolean puedeExplotarse(int fila, int col) {
	    // comprobar si en la matriz hay un Bloque explotable
	    return (this.tablero[fila][col].esDestructible());
	}

	protected void compExplosion(int pY, int pX) {
			this.estrategiaBombas.compExplosion(pY, pX, tablero);
	        
	 }

	protected void explotarCelda(int pY, int pX) {
		this.estrategiaBombas.explotarCelda(pY, pX, this.tablero);
	}

	protected void postExplosion(int pY,int pX) {
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar("Vacio", pY, pX);

	    setChanged();
	    notifyObservers(new Object[] {4, pX, pY});
	}


	public boolean ponerBomba(int fila, int col) {  //Devuelve true si se ha podido poner una bomba, false si no
	    return this.estrategiaBombas.ponerBomba(fila, col, this.tablero);
	}

	protected void iniciarTimersEnemigos() { //Este metodo se llama una vez el tablero esta completamente generado
		for (int i = 0; i < this.tablero.length; i++) { //Se busca por todo el tablero que casillas son enemigos
			for (int j = 0; j < this.tablero[0].length; j++) {
				Bloque bloque = this.tablero[i][j];
				if (bloque != null && bloque.esEnemigo()) { //Si es enemigo entonces se enciende su timer con "iniciarMovimiento" para que empiecen a moverse
					((BloqueEnemigo) bloque).iniciarMovimiento();
				}
			}
		}
	}
	public void detenerTimersEnemigosYExplosiones() {
		for (int i = 0; i < this.tablero.length; i++) {
			for (int j = 0; j < this.tablero[0].length; j++) {
				Bloque bloque = this.tablero[i][j];
				if (bloque != null && (bloque.esEnemigo() || bloque.eresExplosion())) {
					bloque.pararTimer();
				}
			}
		}
	}
	
	public void addObserverEnemigos(Observer o) { //El metodo es igual al de arriba, pero como hacen funciones distintas en momentos distintos no podemos juntarlos
		for (int i = 0; i < this.tablero.length; i++) { //Se busca por todo el tablero que casillas son enemigos
			for (int j = 0; j < this.tablero[0].length; j++) {
				Bloque bloque = this.tablero[i][j];
				if (bloque != null && bloque.esEnemigo()) { //Se anade como observer el observer recibido como parametro
					bloque.addObserver(o);
					System.out.println("Me he anadido como observer del enemigo"); //Debugging
				}
			}
		}
		
	}
	
	public void addObserverEstrategia(Observer o) {
		this.estrategiaBombas.addObserver(o);

	}
	
	protected void moverEnemigo(BloqueEnemigo enemigo, int antiguaY, int antiguaX, int nuevaY, int nuevaX) {
		this.tablero[antiguaY][antiguaX] = GenBloques.getGenBloques().generar("Vacio", antiguaY, antiguaX);
		this.tablero[nuevaY][nuevaX] = enemigo; //Este objeto que pasas con this no seria mas adecuado que el propio tablero pase la posicion de un sitio a otro
												//y luego en la posicion antigua se ponga como vacio, en vez de pasarlo como parametro?
		System.out.println("Moviendo " + nuevaY + " " + nuevaX);
	}
	public void changeStrategy(EstrategiaBombas pSB) {
		this.estrategiaBombas = pSB;
	}
	
	public abstract boolean comprobarFila(int pX, int bombaY, int bombaX);
	public abstract boolean comprobarColumna(int pY, int bombaY, int bombaX);

}
