package modelo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class EnemigoBoss extends BloqueEnemigo {


	private int spriteActual = 14;
	private int vida         = 3;
	private boolean muerto   = false;

	public EnemigoBoss(int pY, int pX) {
		super(pY, pX);
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
	
	@Override
	public void iniciarMovimiento() {
		TimerTask moverTask = new TimerTask() {
            @Override
            public void run()  {
                try {
					mover();
					System.out.println("Empezando movimiento"); //Debugging
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
			spriteActual = 18;
			super.moverDerecha();
		}
		else if (respuesta.equals("x-axis decrease")) {
			spriteActual = 14;
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
		if (vida > 0) {
			spriteActual = 21;                        // sprite herido
			pintarEnemigo(super.getY(), super.getX());
		} else {
			muerto = true;
			spriteActual = 22;                      // sprite muerte
			pararTimer();                           // deja de moverse
			pintarEnemigo(super.getY(), super.getX());
			// Sustituir por un bloque duro para que no se pueda entrar en el cadaver????
		}
	}

	public boolean esBoss() {
		return true;
	}

	public boolean puedoMoverme() { // para que pueda recibir daño
		return true;
	}
}
