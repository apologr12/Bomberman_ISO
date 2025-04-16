package main;

import java.awt.EventQueue;
import java.io.IOException;

import modelo.LlamadasIA;
import vistas.Menu;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//System.out.println(LlamadasIA.getLlamadasIA().movimientoIA(5, 0, 5, 15)); //Debugging IA
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu();
					menu.setSize(720, 480);
					menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
