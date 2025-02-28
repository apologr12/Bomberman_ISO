package Modelo;

@SuppressWarnings("deprecation")
public class PersonajeBlanco extends Personaje {
	
	public PersonajeBlanco() {
		super(0, 0, 10);
		
	}
	
	public void plantarBomba() {
	    if (super.quedanBombas()) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        GestorTableros.getGestorTableros().getTablero().ponerBloqueBombaSimple(super.getY(), super.getX());
	        System.out.println("Bomba");
	        super.restarBomba();
	    }
	}
	
	public void explosionBomba(int pX, int pY) {
		super.sumarBomba();
		if (super.coincideX(pX) && super.coincideY(pY)) {
			System.exit(1);
		}
		else {
			if (super.coincideX(pX + 1) && super.coincideY(pY)) {
				System.exit(1);
			}
			else if (super.coincideX(pX - 1) && super.coincideY(pY)) {
				System.exit(1);
			}
			else if (super.coincideX(pX) && super.coincideY(pY + 1)) {
				System.exit(1);														//Los SYSTEM.EXIT() son temporales hasta hacer la pantalla de muerte
			}
			else if (super.coincideX(pX) && super.coincideY(pY - 1)) {
				System.exit(1);
			}
		}

		GestorTableros.getGestorTableros().getTablero().compExplosionSimple(pX, pY);
		
	}
	
	@Override
	public void mostrarPersonaje() {						//TODAS ESTAS NOTIFICACIONES A LA VISTA NO SE COMO HACERLA PARA REUTILIZAR EL METODO PARA LOS 2 PERSONAJES
		setChanged();
		notifyObservers(new Object[] {3, getX(), getY()});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, getX(), getY()});
	}
	@Override
	public void dejarDeMostrarPersonajePeroBomba() {
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

}
