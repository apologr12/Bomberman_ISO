package main;

import java.awt.EventQueue;

import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Vista.Classic;

public class Main {

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Classic frame = new Classic();
					frame.setSize(720, 480);
					frame.setVisible(true);
					GestorTableros.getGestorTableros().crearTablero();
					GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().mostrarPersonaje();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
