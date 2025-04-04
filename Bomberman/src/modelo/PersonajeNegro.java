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
		if (super.coincideX(pX) && super.coincideY(pY)) { //Si el personaje esta sobre la bomba
			System.exit(1);
		}
		else if (super.coincideX(pX)) { //Si esta en la misma columna pero no en la misma fila que la bomba
			if (GestorTableros.getGestorTableros().getTablero().comprobarColumna(super.getY(), pY, pX)) { //Comprobar si la explosion llega hasta el personaje en la columna
				System.exit(1);
			}
		}
		else if (super.coincideY(pY)) { //Si esta en la misma fila pero no en la misma columna
			if (GestorTableros.getGestorTableros().getTablero().comprobarFila(super.getX(), pY, pX)) { //Comprobar si la explosion llega hasta el personaje en la fila
				System.exit(1);
			}
		}
		GestorTableros.getGestorTableros().getTablero().compExplosion(pY, pX);
	}
}
