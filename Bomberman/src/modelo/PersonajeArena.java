package modelo;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class PersonajeArena extends Personaje {
	private int disparos;
	
	protected PersonajeArena(int mapaSelect) {
		super(mapaSelect, 0);
		this.disparos = 4;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void disparar(String orientacion) {
		if (this.disparos > 0) { //Quedan disparos
			boolean seHaPodido = GestorTableros.getGestorTableros().getTablero().disparar(super.getY(), super.getX(), orientacion);
			
		}
	}
	
	@Override
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {3, getX(), getY(),3});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, getX(), getY(), 3});
	}
	@Override
	public void mostrarBomba() {
		setChanged();
		notifyObservers(new Object[] {7, getX(), getY(), 3});
	}
	@Override
	public void mostrarPersonajeUp() {						
		setChanged();
		notifyObservers(new Object[] {8, getX(), getY(), 3});	
	}
	@Override
	public void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, getX(), getY(), 3});	
	}
	@Override
	public void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, getX(), getY(), 3});	
	}


	@Override
	public void explosionBomba(int pY, int pX) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void meHeMuerto(int motivo) {
		GestorTableros.getGestorTableros().getTablero().detenerTimersEnemigosYExplosiones(); //Detenemos el movimiento de los enemigos
		dejarDeMostrarPersonaje(); //Dejamos de mostrar al personaje
		setChanged();
		notifyObservers(new Object[] {16, getX(), getY(), 3, motivo}); //El 1 indica que es el personaje 3
		
		
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
