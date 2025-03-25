package Modelo;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BloqueBomba extends Bloque {
	
	private int cont = 4; //Timer con un cont=4 que dura tres segundos, si se pone cont=3 el timer solo dura 2 segundos.
	private Timer timer = null;
	
	protected BloqueBomba(int pY, int pX) {
		super(pY,pX);
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

    protected void actualizarCont() {
		cont--;
		if (cont == 0) {
			System.out.println("BOOM"); //Cuando llega a cero explotaría y se tendria que cambiar a un bloque vacio o explosion
			timer.cancel(); //Se apaga el timer
			explotarBomba();
		}
		System.out.println(cont);  //Muestra como va el contador
	
    }
    
    protected abstract void explotarBomba();
    
    public boolean esDestructible() {
		return false;
	}
    
    @Override
    public boolean eresBomba() {
    	return true;
    }
    
    @Override
    public boolean puedoMoverme() {
    	return false;
    }
    
}
