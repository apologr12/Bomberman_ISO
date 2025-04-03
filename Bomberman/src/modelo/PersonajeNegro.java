package modelo;

@SuppressWarnings("deprecation")
public class PersonajeNegro extends Personaje {

	protected PersonajeNegro() {
		super(0, 0, 1);
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
		if (super.coincideX(pX) && super.coincideY(pY)) { //Esto hay que revisarlo ya que de momento el que revisa si el personaje coincide con la explosion es la estrategia
			System.exit(1);
		}
		GestorTableros.getGestorTableros().getTablero().compExplosion(pY, pX);
	}
}
