package Modelo;

@SuppressWarnings("deprecation")
public class PersonajeBlanco extends Personaje {
	public static PersonajeBlanco miPersonaje = new PersonajeBlanco();

	
	private PersonajeBlanco() {
		super(0, 0, 10);
		
	}
	public static PersonajeBlanco getPersonaje() {
		return miPersonaje;
	}
	

	
	public void plantarBomba() {
	    if (super.quedanBombas()) { // si tenemos bombas disponibles las colocamos en nuestra posicion.
	        GestorTableros.getGestorTableros().getTableroClasico().ponerBloqueBombaSimple(super.getX(), super.getY());
	        System.out.println("Bomba");
	        super.restarBomba();
	    }
	}
	
	public void explosionBomba(int pX, int pY, int pTipo) {
		super.sumarBomba();
		if (super.coincideX(pX) && super.coincideY(pY)) {
			System.exit(1);
		}
		else if (pTipo == 1) {								//Bomba basica (tipo 1)
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
		else if (pTipo == 2) {
			//TODO Tener en cuenta que si el personaje esta al  otro lado de un bloque duro la bomba no le mata
		}
		GestorTableros.getGestorTableros().getTableroClasico().compExplosion(pX, pY, pTipo);
		
	}
	
	@Override
	public void mostrarPersonaje() {						//TODAS ESTAS NOTIFICACIONES A LA VISTA NO SE COMO HACERLA PARA REUTILIZAR EL METODO PARA LOS 2 PERSONAJES
		setChanged();
		notifyObservers(new Object[] {3, x, y});	
	}
	
	@Override
	public void dejarDeMostrarPersonaje() {
		setChanged();
		notifyObservers(new Object[] {4, x, y});
	}
	@Override
	public void dejarDeMostrarPersonajePeroBomba() {
		setChanged();
		notifyObservers(new Object[] {7, x, y});
	}
	@Override
	public void mostrarPersonajeUp() {						
		setChanged();
		notifyObservers(new Object[] {8, x, y});	
	}
	@Override
	public void mostrarPersonajeLeft() {
		setChanged();
		notifyObservers(new Object[] {9, x, y});	
	}
	@Override
	public void mostrarPersonajeRight() {
		setChanged();
		notifyObservers(new Object[] {10, x, y});	
	}

}
