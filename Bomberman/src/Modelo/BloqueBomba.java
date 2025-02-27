package Modelo;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BloqueBomba extends Bloque {
	private int x;
	private int y;
	
	private int cont = 4; //Timer con un cont=4 que dura tres segundos, si se pone cont=3 el timer solo dura 2 segundos.
	private Timer timer = null;
	
	protected BloqueBomba(int pX, int pY) {
		this.x = pX;
		this.y = pY;
		//Cada segundo se ejectuta actualizarCont para actualizar el contador del semaforo
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				actualizarCont();
			}		
		};
		timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 0, 1000); //Empieza a contar el timer
	}
}
