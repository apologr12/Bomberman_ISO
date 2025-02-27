package Modelo;

import java.util.Timer;
import java.util.TimerTask;

public class BloqueBombaSimple extends BloqueBomba {
	
	
	

    public BloqueBombaSimple(int pY, int pX) {
    	super(pX, pY);											//Asignacion temporal de coordenadas
    }
    
    private void actualizarCont() {
		cont--;
		if (cont == 0) {
			System.out.println("BOOM"); //Cuando llega a cero explotaría y se tendria que cambiar a un bloque vacio o explosion
			timer.cancel(); //Se apaga el timer
			PersonajeBlanco.getPersonaje().explosionBomba(this.x, this.y);
		}
		System.out.println(cont);  //Muestra como va el contador
		//De momento no se que meter en el notifyObservers sigo sin entender bien como funciona
		//Si el tipo de bloque no se cambia no se puede reiniciar el timer si se pone otra bomba en el mismo bloque
		//setChanged();
		//notifyObservers(new Object[] {});
		
		
		
	}
	
}
