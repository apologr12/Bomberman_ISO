package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modelo.GestorPersonajes;
import Modelo.MenuModelo;

public class ControladorMenu implements KeyListener {
	
	public static ControladorMenu miControlador = new ControladorMenu();
	
	private ControladorMenu() {
	}
	
	public static ControladorMenu getControlador() {
		return miControlador;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			MenuModelo.getMenu().seleccionarPersonaje(1);
		}
		else if(keyCode == KeyEvent.VK_LEFT) {
			MenuModelo.getMenu().seleccionarPersonaje(-1);
		} 
		else if(keyCode == KeyEvent.VK_SPACE) {
			//TODO Iniciar juego
			MenuModelo.getMenu().IniciarJuego();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
