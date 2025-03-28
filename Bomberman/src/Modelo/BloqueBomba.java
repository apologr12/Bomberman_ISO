package Modelo;

import java.util.Timer;
import java.util.TimerTask;

public class BloqueBomba extends Bloque{
	private int rangoEx;  // 1 para BomberMan blanco y X en un futuro para el negro
	private int cont=4; //Timer con un cont=4 que dura tres segundos, si se pone cont=3 el timer solo dura 2 segundos.
	private Timer timer = null;

    public BloqueBomba(int rangoEx) {
        this.rangoEx = rangoEx;
		//Cada segundo se ejectuta actualizarCont para actualizar el contador del semáforo
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
			System.out.println("BOOM"); //Cuando llega a cero explotar�a y se tendria que cambiar a un bloque vacio o explosion
			timer.cancel(); //Se apaga el timer
		}
		System.out.println(cont);  //Muestra como va el contador
		//De momento no se que meter en el notifyObservers sigo sin entender bien como funciona
		//Si el tipo de bloque no se cambia no se puede reiniciar el timer si se pone otra bomba en el mismo bloque
		//setChanged();
		//notifyObservers(new Object[] {});
		
		
		
	}
	
}
