package main;

import java.awt.EventQueue;

import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Vista.Classic;
import Vista.Menu;

public class Main {

	public static void main(String[] args) {
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
