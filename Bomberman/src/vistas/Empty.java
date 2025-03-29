package vistas;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class Empty extends VistaJuego {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3165415603720433876L;

	/**
	 * Create the frame.
	 */
	public Empty() {
		setTitle("Empty");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);								//Crea el panel principal sobre el que van a estar los distintos JLabel
		
		super.crearPaneles();

		// Crear un JPanel personalizado para el fondo y no tengo ni idea de como funciona pero establece la foto como background
		JPanel backgroundPanel = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        super.paintComponent(g);
		        ImageIcon background = new ImageIcon(getClass().getResource("imagenes/stageBack2.png"));
		        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		};

		backgroundPanel.setLayout(new GridLayout(1, 1)); // Evita modificar la estructura interna
	    
	    super.setFondo(backgroundPanel);// A�adir contentPane dentro de backgroundPanel

	    setContentPane(backgroundPanel);  // Establecer backgroundPanel como el contenedor principal
	    
	    setFocusable(true); 										// Hacer que el JFrame reciba eventos de teclado
	    requestFocus();
		
		super.crearTablero();
	}

	
}