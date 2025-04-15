package modelo;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class PersonajeNegro extends Personaje {

	protected PersonajeNegro(int mapaSelect) {
		super(mapaSelect, 1);
	}

	@Override
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {3, getX(), getY(),2});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, getX(), getY(),2});
	}
	@Override
	public void mostrarBomba() {
		setChanged();
		notifyObservers(new Object[] {7, getX(), getY(),2});
	}
	@Override
	public void mostrarPersonajeUp() {						
		setChanged();
		notifyObservers(new Object[] {8, getX(), getY(),2});	
	}
	@Override
	public void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, getX(), getY(),2});	
	}
	@Override
	public void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, getX(), getY(),2});	
	}

	@Override
	public void explosionBomba(int pY, int pX) {
		super.sumarBomba();	
		boolean heMuerto = false;
		if (super.coincideX(pX) && super.coincideY(pY)) { //Si el personaje esta sobre la bomba
			heMuerto = true;
		}
		else if (super.coincideX(pX)) { //Si esta en la misma columna pero no en la misma fila que la bomba
			if (GestorTableros.getGestorTableros().getTablero().comprobarColumna(super.getY(), pY, pX)) { //Comprobar si la explosion llega hasta el personaje en la columna
				heMuerto = true;
			}
		}
		else if (super.coincideY(pY)) { //Si esta en la misma fila pero no en la misma columna
			if (GestorTableros.getGestorTableros().getTablero().comprobarFila(super.getX(), pY, pX)) { //Comprobar si la explosion llega hasta el personaje en la fila
				heMuerto = true;
			}
		}
		GestorTableros.getGestorTableros().getTablero().compExplosion(pY, pX);
		
		if (heMuerto) {
			this.meHeMuerto(1);
		}
	}

	@Override
	protected void meHeMuerto(int motivo) { //Si le llega como parametro 1 quiere decir que se ha muerto por enemigo, si le llega un 2, por bomba
		GestorTableros.getGestorTableros().getTablero().detenerTimersEnemigosYExplosiones(); //Detenemos el movimiento de los enemigos
		dejarDeMostrarPersonaje(); //Dejamos de mostrar al personaje
		setChanged();
		notifyObservers(new Object[] {16, getX(), getY(), 2, motivo}); //Notificamos a la vista que hemos muerto
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
