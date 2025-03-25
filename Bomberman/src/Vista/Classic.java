package Vista;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorJuego;
import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Modelo.Personaje;
import Modelo.Tablero;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Classic extends VistaJuego {
	/**
	 * Create the frame.
	 */
	public Classic() {
		setTitle("Classic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);								//Crea el panel principal sobre el que van a estar los distintos JLabel
		
		super.crearPaneles();

		// Crear un JPanel personalizado para el fondo y no tengo ni idea de como funciona pero establece la foto como background
		JPanel backgroundPanel = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        super.paintComponent(g);
		        ImageIcon background = new ImageIcon(getClass().getResource("stageBack1.png"));
		        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		};

		backgroundPanel.setLayout(new GridLayout(1, 1)); // Evita modificar la estructura interna
	    
	    super.setFondo(backgroundPanel);// Añadir contentPane dentro de backgroundPanel

	    setContentPane(backgroundPanel);  // Establecer backgroundPanel como el contenedor principal
	    
	    
	    

		
	    setFocusable(true); 										// Hacer que el JFrame reciba eventos de teclado
	    requestFocus();
		
		

		
		super.crearTablero();
	}

	
}
