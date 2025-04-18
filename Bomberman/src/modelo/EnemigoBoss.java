package modelo;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class EnemigoBoss extends BloqueEnemigo {
	
	public EnemigoBoss(int pY, int pX) {
		super(pY, pX);
	}

	@Override
	protected void pintarEnemigo(int nuevaY, int nuevaX) {
		setChanged();
        notifyObservers(new Object[]{14, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
		
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
			System.out.println(respuesta + cont); //Debugging
			cont++;
		} while (!respuesta.equals("x-axis increase") && !respuesta.equals("x-axis decrease") && !respuesta.equals("y-axis increase") && !respuesta.equals("y-axis decrease"));
			
		if (respuesta.equals("x-axis increase")) {
			super.moverDerecha();
		}
		else if (respuesta.equals("x-axis decrease")) {
			super.moverIzquierda();
		}
		else if (respuesta.equals("y-axis increase")) {
			super.moverArriba();
		}
		else if (respuesta.equals("y-axis decrease")) { //Decrementar y para el prompt es como moverse hacia abajo, lo contrario de nuestro sistema de referencia
			super.moverAbajo();
		}
	}
}
