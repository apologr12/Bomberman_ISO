package modelo;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class PersonajeBlanco extends Personaje {
	
	public PersonajeBlanco(int mapaSelect) {
		super(mapaSelect, 10);
	}
	
	public void explosionBomba(int pY, int pX) {
		boolean heMuerto = false;
		super.sumarBomba();
		if (super.coincideX(pX) && super.coincideY(pY)) {
			heMuerto = true;
		}
		else {
			if (super.coincideX(pX + 1) && super.coincideY(pY)) {
				heMuerto = true;
			}
			else if (super.coincideX(pX - 1) && super.coincideY(pY)) {
				heMuerto = true;
			}
			else if (super.coincideX(pX) && super.coincideY(pY + 1)) {
				heMuerto = true;
			}
			else if (super.coincideX(pX) && super.coincideY(pY - 1)) {
				heMuerto = true;
			}
		}

		GestorTableros.getGestorTableros().getTablero().compExplosion(pY, pX); //Si no se ha muerto se tienen que eliminar todos los bloques que destruya la bomba
		
		if (heMuerto) {
			this.meHeMuerto(1);
		}
	}
	
	
	@Override
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {3, getX(), getY(),1});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, getX(), getY(), 1});
	}
	@Override
	public void mostrarBomba() {
		setChanged();
		notifyObservers(new Object[] {7, getX(), getY(), 1});
	}
	@Override
	public void mostrarPersonajeUp() {						
		setChanged();
		notifyObservers(new Object[] {8, getX(), getY(), 1});	
	}
	@Override
	public void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, getX(), getY(), 1});	
	}
	@Override
	public void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, getX(), getY(), 1});	
	}

	@Override
	protected void meHeMuerto(int motivo) { //Si le llega como parametro 1 quiere decir que se ha muerto por enemigo, si le llega un 2, por bomba
		GestorTableros.getGestorTableros().getTablero().detenerTimersEnemigosYExplosiones(); //Detenemos el movimiento de los enemigos
		dejarDeMostrarPersonaje(); //Dejamos de mostrar al personaje
		setChanged();
		notifyObservers(new Object[] {16, getX(), getY(), 1, motivo}); //El 1 indica que es el personaje 1
		
		
		TimerTask timerTask = new TimerTask() { //Se ha utilizado el timer en vez del Thread.slee() porque nos daba problemas
			@Override
			public void run() {
				System.exit(1);
			}		
		};
		
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 3000, 5); //Empieza a contar el timer
	}
}
