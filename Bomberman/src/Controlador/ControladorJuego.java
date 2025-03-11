package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modelo.GestorPersonajes;

public class ControladorJuego implements KeyListener {
	
	public static ControladorJuego miControlador = new ControladorJuego();
	
	
	private ControladorJuego(){
	}
	
	public static ControladorJuego getControlador() {
		return miControlador;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().movimientoR();
		}
		else if(keyCode == KeyEvent.VK_LEFT) {
			GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().movimientoL();
		}
		else if(keyCode == KeyEvent.VK_UP) {
			GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().movimientoU();
		}
		else if(keyCode == KeyEvent.VK_DOWN) {
			GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().movimientoD();
		} 
		else if(keyCode == KeyEvent.VK_X) {
			GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().plantarBomba();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}