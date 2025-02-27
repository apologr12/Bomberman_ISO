package Modelo;

import java.util.Timer;
import java.util.TimerTask;

public class BloqueExplosion extends Bloque{
	
		private int cont = 3; //Timer con un cont=3 que dura dos segundos.
		private Timer timer = null;
		private int x;
		private int y;

	    public BloqueExplosion(int pX, int pY) {
	    	this.x = pX;
	    	this.y = pY;												//Asignacion temporal de coordenadas
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
				GestorTableros.getGestorTableros().getTableroClasico().postExplosion(y, x);
			}
			//System.out.println(cont);  //Muestra como va el contador
	    }
			


}
