package controladores;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import modelo.GestorPersonajes;

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
			GestorPersonajes.getGestorPersonajes().getPersonaje().movimientoR();
		}
		else if(keyCode == KeyEvent.VK_LEFT) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().movimientoL();
		}
		else if(keyCode == KeyEvent.VK_UP) { 
			GestorPersonajes.getGestorPersonajes().getPersonaje().movimientoU();
		}
		else if(keyCode == KeyEvent.VK_DOWN) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().movimientoD();
		} 
		else if(keyCode == KeyEvent.VK_X) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().plantarBomba();
		}
		else if (keyCode == KeyEvent.VK_A) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().disparar("izquierda");
		}
		else if (keyCode == KeyEvent.VK_W) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().disparar("Arriba");
		}
		else if (keyCode == KeyEvent.VK_S) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().disparar("Abajo");
		}
		else if (keyCode == KeyEvent.VK_D) {
			GestorPersonajes.getGestorPersonajes().getPersonaje().disparar("Derecha");
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}