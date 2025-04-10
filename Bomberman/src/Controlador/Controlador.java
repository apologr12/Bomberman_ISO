package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modelo.Personaje;

public class Controlador implements KeyListener {
	
	public static Controlador miControlador = new Controlador();
	
	
	private Controlador(){
		
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_RIGHT) {
			Personaje.getPersonaje().movimientoR();
		}
		else if(keyCode == KeyEvent.VK_LEFT) {
			Personaje.getPersonaje().movimientoL();
		}
		else if(keyCode == KeyEvent.VK_UP) {
			Personaje.getPersonaje().movimientoU();
		}
		else if(keyCode == KeyEvent.VK_DOWN) {
			Personaje.getPersonaje().movimientoD();
		} 
		else if(keyCode == KeyEvent.VK_X) {
			Personaje.getPersonaje().plantarBomba();
		}
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}