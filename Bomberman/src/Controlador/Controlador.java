package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controlador implements KeyListener {
	
	public static Controlador miControlador = new Controlador();
	
	
	private Controlador(){
		
	}
	
	public static Controlador getControlador() {
		return miControlador;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
			
			
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}