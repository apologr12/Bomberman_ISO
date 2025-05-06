package modelo;

import java.util.Timer;
import java.util.TimerTask;

public class BloqueDisparo extends Bloque {
	private String orientacion;
	private Timer timer = null;
	private int cont = 2;
	
	protected BloqueDisparo(int pY, int pX, String orientacion) {
		super(pY, pX);
		this.orientacion = orientacion;
		
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarCont();
			}		
		};
		timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 0, 700); //Empieza a contar el timer
	}
	
	private void actualizarCont() {
		cont--;
		if (cont == 0) {
			//System.out.println("Movimiento disparo"); //Cuando llega a cero se moveria y se tendria que cambiar a un bloque vacio
			GestorTableros.getGestorTableros().getTablero().compAtaque(super.getY(), super.getX(), orientacion);
			timer.cancel(); //Se apaga el timer
		}
		//System.out.println(cont);  //Muestra como va el contador
		
	}

	@Override
	public boolean esDestructible() {
		return false;
	}
	
	@Override
	public boolean eresDisparo() {
		return true;
	}
}
