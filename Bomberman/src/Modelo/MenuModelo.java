package Modelo;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class MenuModelo extends Observable {
	
	private static MenuModelo miMenu = new MenuModelo();
	private int tipoPersonajeSelec = 1;
	private static final int maxPersonajes = 2;
	private int tipoMapaSelec = 1;
	
	private MenuModelo() {
	}
	
	public static MenuModelo getMenu() {
		return miMenu;
	}
	
	public void seleccionarPersonaje(int pSeleccionPersonaje) {
		if (pSeleccionPersonaje == -1) {
			if (this.tipoPersonajeSelec == 1) {
				this.tipoPersonajeSelec = maxPersonajes;
			}
			else {
				this.tipoPersonajeSelec--;

			}
		}
		else if (pSeleccionPersonaje == 1) {
			if (this.tipoPersonajeSelec == maxPersonajes) {
				this.tipoPersonajeSelec = 1;
			}
			else {
				this.tipoPersonajeSelec++;
			}
		}
		cambioPersonaje(this.tipoPersonajeSelec);
	}
	
	public void SeleccionarTablero(int pSeleccionTablero) {
		
	}
	private void cambioPersonaje(int pTipoPersonajeSelec) {
	    setChanged();
	    notifyObservers(new Object[] {1,pTipoPersonajeSelec});
	}
	public void IniciarJuego() {
		 setChanged();
		 notifyObservers(new Object[] {2,tipoPersonajeSelec,tipoMapaSelec});
		 GestorTableros.getGestorTableros().crearTablero();
		 GestorPersonajes.getGestorPersonajes().getPersonaje().mostrarPersonaje();
	}
}
