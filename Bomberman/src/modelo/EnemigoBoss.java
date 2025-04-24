package modelo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class EnemigoBoss extends BloqueEnemigo {


	private int spriteActual = 14;
	private int vida         = 4;
	private boolean muerto   = false;

	public EnemigoBoss(int pY, int pX) {
		super(pY, pX);
		this.bossBarEnemigo();
	}

	@Override
	protected void pintarEnemigo(int nuevaY, int nuevaX) {
		setChanged();
		notifyObservers(new Object[] { spriteActual, nuevaX, nuevaY });
		//14  bossRight.png
		//18  bossLeft.png
		//19  bossUp.png
		//20  bossDown.png
		//21  bossHit.png
		//22  bossDead.png
	}
	
	private void bossBarEnemigo() {
		setChanged();
		notifyObservers(new Object[] { 23, vida});
	}
	@Override
	public void iniciarMovimiento() {
		this.bossBarEnemigo();
		TimerTask moverTask = new TimerTask() {
            @Override
            public void run()  {
                try {
					mover();
					atacarJugador();
					//System.out.println("Empezando movimiento"); //Debugging
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        
        super.setTarea(moverTask, 1000);
	}
	
	@Override
	public void mover() throws IOException {

		// Debugging porque como no puedo ejecutar la IA no dispara el boss



		
		String respuesta = null;
		int persX = GestorPersonajes.getGestorPersonajes().getPersonaje().getX();
		int persY = 10 - GestorPersonajes.getGestorPersonajes().getPersonaje().getY(); //Esto se hace para invertir el hecho de que la y= 0 es arriba, y para el prompt
																						// y=0 es abajo
		do { //Si a la IA le da por responder otra cosa, pues que se lo vuelva a pedir
			int cont = 0;
			respuesta = LlamadasIA.getLlamadasIA().movimientoIA(persY, persX, 10 - super.getY(), super.getX()); //Lo mismo que el comentario de arriba
			respuesta = respuesta.trim();
			//System.out.println(respuesta + cont); //Debugging
			cont++;
		} while (!respuesta.equals("x-axis increase") && !respuesta.equals("x-axis decrease") && !respuesta.equals("y-axis increase") && !respuesta.equals("y-axis decrease"));
			
		if (respuesta.equals("x-axis increase")) {
			spriteActual = 14;
			super.moverDerecha();
		}
		else if (respuesta.equals("x-axis decrease")) {
			spriteActual = 18;
			super.moverIzquierda();
		}
		else if (respuesta.equals("y-axis increase")) {
			spriteActual = 19;
			super.moverArriba();
		}
		else if (respuesta.equals("y-axis decrease")) { //Decrementar y para el prompt es como moverse hacia abajo, lo contrario de nuestro sistema de referencia
			spriteActual = 20;
			super.moverAbajo();
		}

		 
	}

	public void recibirDanio() {
		vida--;
		System.out.println(vida);
		this.bossBarEnemigo();
		if (vida > 0) {
			spriteActual = 21;                        // sprite herido
			pintarEnemigo(super.getY(), super.getX());
		} else {
			muerto = true;
			spriteActual = 22;                      // sprite muerte
			pararTimer();                           // deja de moverse
			pintarEnemigo(super.getY(), super.getX());
			super.pararTimer();
			TimerTask timerTask = new TimerTask() { //Se ha utilizado el timer en vez del Thread.sleep() porque nos daba problemas
				@Override
				public void run() {
					System.exit(1);
				}		
			};
			
			Timer timer = new Timer(); 
			timer.scheduleAtFixedRate(timerTask, 3000, 5); //Empieza a contar el timer
		}
	}

	public boolean esBoss() {
		return true;
	}

	public boolean puedoMoverme() { // para que pueda recibir daño
		return true;
	}

	public void atacarJugador() {
		Personaje jugador = GestorPersonajes.getGestorPersonajes().getPersonaje();
		int jugadorX = jugador.getX();
		int jugadorY = jugador.getY();
		int bossX = super.getX();
		int bossY = super.getY();
		System.out.println(jugadorX + " " + jugadorY + " " + bossX + " " + bossY);
		// El disparo solo se realiza si el jugador está alineado en recta
		if (jugadorX == bossX) {
			if (jugadorY < bossY) {
				this.atacarPersonaje("Arriba"); // disparar hacia arriba
			} else if (jugadorY > bossY) {
				this.atacarPersonaje("Abajo"); // disparar hacia abajo
			}
		} else if (jugadorY == bossY) {
			if (jugadorX < bossX) {
				this.atacarPersonaje("Izquierda"); // disparar hacia la izquierda
			} else if (jugadorX > bossX) {
				this.atacarPersonaje("Derecha"); // disparar hacia la derecha
			}
		}
		// Si no están alineados, no se dispara (se podría añadir lógica futura aquí)
	}

	public void atacarPersonaje(String orientacion) {
			boolean seHaPodido = GestorTableros.getGestorTableros().getTablero().atacar(super.getY(), super.getX(), orientacion);
			System.out.println(seHaPodido); // debugging
	}

}
