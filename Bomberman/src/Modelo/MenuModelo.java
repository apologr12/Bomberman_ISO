package Modelo;
import java.util.Observable;


@SuppressWarnings("deprecation")
public class MenuModelo extends Observable {
	
	private static MenuModelo miMenu = new MenuModelo();
	private int tipoPersonajeSelec = 1;
	private static final int maxPersonajes = 2;
	
	private MenuModelo() {
	}
	
	public static MenuModelo getMenu() {
		return miMenu;
	}
	
	public void seleccionarPersonaje(int pSeleccion) {
		if (pSeleccion == -1) {
			if (this.tipoPersonajeSelec == 1) {
				this.tipoPersonajeSelec = maxPersonajes;
			}
			else {
				this.tipoPersonajeSelec--;

			}
		}
		else if (pSeleccion == 1) {
			if (this.tipoPersonajeSelec == maxPersonajes) {
			}
			else {
				this.tipoPersonajeSelec++;
			}
		}
	}
}
