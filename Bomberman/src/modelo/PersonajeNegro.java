package modelo;

@SuppressWarnings("deprecation")
public class PersonajeNegro extends Personaje {

	protected PersonajeNegro() {
		super(0, 0, 1);
	}

	@Override
	public void mostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {3, getX(), getY()});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, getX(), getY()});
	}
	@Override
	public void mostrarBomba() {
		setChanged();
		notifyObservers(new Object[] {7, getX(), getY()});
	}
	@Override
	public void mostrarPersonajeUp() {						
		setChanged();
		notifyObservers(new Object[] {8, getX(), getY()});	
	}
	@Override
	public void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, getX(), getY()});	
	}
	@Override
	public void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, getX(), getY()});	
	}

	@Override
	public void explosionBomba(int pY, int pX) {
		// TODO Auto-generated method stub
		
	}
}
