package modelo;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class MenuModelo extends Observable {
	
	private static MenuModelo miMenu = new MenuModelo();
	private int tipoPersonajeSelec = 1;
	private static final int maxPersonajes = 2;
	private static final int maxTableros = 4;
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
		cambioPersonaje();
	}
	
	public void seleccionarTablero(int pSeleccionTablero) {
		if (pSeleccionTablero == -1) {
			if (this.tipoMapaSelec == 1) {
				this.tipoMapaSelec = maxTableros;
			}
			else {
				this.tipoMapaSelec--;

			}
		}
		else if (pSeleccionTablero == 1) {
			if (this.tipoMapaSelec == maxTableros) {
				this.tipoMapaSelec = 1;
			}
			else {
				this.tipoMapaSelec++;
			}
		}
		cambioTablero();
	}
	private void cambioPersonaje() {
	    setChanged();
	    notifyObservers(new Object[] {1,this.tipoPersonajeSelec});
	}
	
	private void cambioTablero() {
		setChanged();
	    notifyObservers(new Object[] {3,this.tipoMapaSelec});
	}
	public void iniciarJuego() {
		 //El orden de que primero se cree el personaje, luego se notifique y luego se cree el tablero es importante, por como manejamos la creacion del tablero
		//y el mostrado del personaje
		 GestorPersonajes.getGestorPersonajes().crearInstanciaPersonaje(this.tipoMapaSelec, this.tipoPersonajeSelec);
		 GestorTableros.getGestorTableros().crearInstanciaTablero(this.tipoMapaSelec, this.tipoPersonajeSelec);
		 setChanged();
		 notifyObservers(new Object[] {2,this.tipoPersonajeSelec,this.tipoMapaSelec});
		 GestorTableros.getGestorTableros().crearTablero();
		 GestorPersonajes.getGestorPersonajes().getPersonaje().mostrarPersonaje();
	}
}
