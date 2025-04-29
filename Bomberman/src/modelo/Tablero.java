package modelo;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public abstract class Tablero extends Observable {
	private Bloque[][] tablero;
	private EstrategiaAtaque estrategiaAtaque;
	private EstrategiaAtaque estrategiaAtaqueBoss;

	public abstract void crearTablero();


	protected Tablero(int tamanoY, int tamanoX, int pTipoPersonaje) {
		this.tablero = new Bloque[tamanoY][tamanoX];
		if (pTipoPersonaje == 1) {
			this.estrategiaAtaque = new EstrategiaBombaSimple();
		}
		else if (pTipoPersonaje == 2) {
			this.estrategiaAtaque = new EstrategiaBombaUltra();
		}
		else if (pTipoPersonaje == 3) {
			this.estrategiaAtaque = new EstrategiaDisparo();
			this.estrategiaAtaqueBoss = new EstrategiaAtaqueBoss(); //Solo se crea con el personaje de arena
		}
	}
	protected void ponerBloque(String pTipo, int pY, int pX) { //Solo se usa al generar el tablero
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar(pTipo, pY, pX, "");
	}

	protected void ponerEnemigo(String pTipo, int pY, int pX) {
		this.tablero[pY][pX] = GenEnemigos.getGenEnemigos().generar(pTipo, pY, pX);
	}


	protected int puedoMovermeP(int y, int x) {

		if (x >= 0 && x < 17 && y >= 0 && y < 11) {
			if (this.tablero[y][x].eresExplosion() || this.tablero[y][x].eresDisparo()) {
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

	public void compAtaque(int pY, int pX, String orientacion) {
		this.estrategiaAtaque.compAtaque(pY, pX, tablero, orientacion);    
	 }
	
	public void compAtaqueBoss(int pY, int pX, String orientacion) {
		this.estrategiaAtaqueBoss.compAtaque(pY, pX, tablero, orientacion);    
	 }

	protected void explotarCelda(int pY, int pX) {
		this.estrategiaAtaque.explotarCelda(pY, pX, this.tablero, "");
	}

	protected void postExplosion(int pY,int pX) {
		this.tablero[pY][pX] = GenBloques.getGenBloques().generar("Vacio", pY, pX, ""); //El cuarto parametro es solo para el disparo

	    setChanged();
	    notifyObservers(new Object[] {4, pX, pY});
	}


	public boolean atacar(int fila, int col, String orientacion) {  //Devuelve true si se ha podido poner una bomba o disparar, false si no
		return this.estrategiaAtaque.atacar(fila, col, this.tablero, orientacion); //Se pasa la orientacion para el caso concreto del disparo. Si es bomba se pasa ""
	}
	
	public boolean atacarBoss(int fila, int col, String orientacion) {  //Devuelve true si se ha podido poner una bomba o disparar, false si no
		return this.estrategiaAtaqueBoss.atacar(fila, col, this.tablero, orientacion); //Se pasa la orientacion para el caso concreto del disparo. Si es bomba se pasa ""
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
	
	protected void iniciarTimersEnemigosJava8() { //Este metodo se llama una vez el tablero esta completamente generado
		Arrays.stream(tablero).flatMap(Arrays::stream).
		filter(bloque -> bloque != null && bloque.esEnemigo()).
		forEach(bloque -> ((BloqueEnemigo) bloque).iniciarMovimiento());
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
	
	public void detenerTimersEnemigosYExplosionesJava8() {
		Arrays.stream(tablero).flatMap(Arrays::stream).
		filter(bloque -> bloque != null && (bloque.esEnemigo() || bloque.eresExplosion())).
		forEach(bloque -> bloque.pararTimer());
		
	}
	
	public void addObserverEnemigos(Observer o) { //El metodo es igual al de arriba, pero como hacen funciones distintas en momentos distintos no podemos juntarlos
		for (int i = 0; i < this.tablero.length; i++) { //Se busca por todo el tablero que casillas son enemigos
			for (int j = 0; j < this.tablero[0].length; j++) {
				Bloque bloque = this.tablero[i][j];
				if (bloque != null && bloque.esEnemigo()) { //Se anade como observer el observer recibido como parametro
					bloque.addObserver(o);
					//System.out.println("Me he anadido como observer del enemigo"); //Debugging
				}
			}
		}
		
	}
	
	public void addObserverEstrategia(Observer o) {
		this.estrategiaAtaque.addObserver(o);
		this.estrategiaAtaqueBoss.addObserver(o);
	}
	
	protected void moverEnemigo(BloqueEnemigo enemigo, int antiguaY, int antiguaX, int nuevaY, int nuevaX) {
		this.tablero[antiguaY][antiguaX] = GenBloques.getGenBloques().generar("Vacio", antiguaY, antiguaX, ""); //El cuarto parametro es solo para el disparo
		this.tablero[nuevaY][nuevaX] = enemigo; //Este objeto que pasas con this no seria mas adecuado que el propio tablero pase la posicion de un sitio a otro
												//y luego en la posicion antigua se ponga como vacio, en vez de pasarlo como parametro?
		//System.out.println("Moviendo " + nuevaY + " " + nuevaX);
	}
	public void changeStrategy(EstrategiaAtaque pSB) {
		this.estrategiaAtaque = pSB;
	}

	public abstract boolean comprobarFila(int pX, int bombaY, int bombaX);
	public abstract boolean comprobarColumna(int pY, int bombaY, int bombaX);

}
