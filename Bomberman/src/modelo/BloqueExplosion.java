package modelo;

import java.util.Timer;
import java.util.TimerTask;

public class BloqueExplosion extends Bloque{
	
		private int cont = 3; //Timer con un cont=3 que dura dos segundos.
		private Timer timer = null;

	    public BloqueExplosion(int pY, int pX) {
	    	super(pY,pX);
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					actualizarCont();
				}		
			};
			timer = new Timer(); 
			timer.scheduleAtFixedRate(timerTask, 0, 1000); //Empieza a contar el timer
	    }
	    
	    private void actualizarCont() {
			cont--;
			if (cont == 0) {
				timer.cancel(); //Se apaga el timer
				GestorTableros.getGestorTableros().getTablero().postExplosion(super.getY(), super.getX());
			}
			//System.out.println(cont);  //Muestra como va el contador
	    }
			
	    public boolean esDestructible() {
			return true;
		}
	    
	    @Override
	    public boolean eresExplosion() {
	    	return true;
	    }
	    public void pararTimer() {
	    	timer.cancel();
	    	
	    }

}
