package Vista;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorTableros;
import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Modelo.Personaje;
import Modelo.Tablero;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Classic extends Vista {
	/**
	 * Create the frame.
	 */
	public Classic() {												//CONSEGUIR QUE EL CONTROLADOR SEA QUIEN LLAME AL MODELO Y NO LA VISTA
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
	    
	    
	    

		
	    this.addKeyListener(ControladorTableros.getControlador());  		// Agregar el KeyListener al JFrame en lugar del JPanel (ns porque)
	    setFocusable(true); 										// Hacer que el JFrame reciba eventos de teclado
	    requestFocus();
		
		
		GestorTableros.getGestorTableros().getTablero().addObserver(this);		//ANADIMOS ASI EL OBSERVER O SE LO PASAMOS A UN METODO DEL GESTOR?
		GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().addObserver(this);					//Anadimos el personaje como observable de la vista
		
		super.crearTablero();
	}

	
}
