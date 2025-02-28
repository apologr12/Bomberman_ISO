package Vista;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Modelo.Personaje;
import Modelo.Tablero;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Classic extends JFrame implements Observer {

	private JPanel contentPane;
	private JLabel[][] labels;
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
					GestorTableros.getGestorTableros().crearTableroClasico();
					GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().mostrarPersonaje();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Classic() {												//CONSEGUIR QUE EL CONTROLADOR SEA QUIEN LLAME AL MODELO Y NO LA VISTA
		setTitle("Classic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);								//Crea el panel principal sobre el que van a estar los distintos JLabel
		
		contentPane = new JPanel();
		contentPane.setToolTipText("");
	    contentPane.setOpaque(false); // Permite que se vea el fondo
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(11, 17, 0, 0));

		// Crear un JPanel personalizado para el fondo y no tengo ni idea de como funciona pero establece la foto como background
		JPanel backgroundPanel = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        super.paintComponent(g);
		        ImageIcon background = new ImageIcon(getClass().getResource("back.png"));
		        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		};

		backgroundPanel.setLayout(new GridLayout(1, 1)); // Evita modificar la estructura interna
	    
	    backgroundPanel.add(contentPane);// Añadir contentPane dentro de backgroundPanel

	    setContentPane(backgroundPanel);  // Establecer backgroundPanel como el contenedor principal
	    
	    
	    

		
	    this.addKeyListener(Controlador.getControlador());  		// Agregar el KeyListener al JFrame en lugar del JPanel (ns porque)
	    setFocusable(true); 										// Hacer que el JFrame reciba eventos de teclado
	    requestFocus();
		
		
		GestorTableros.getGestorTableros().getTablero().addObserver(this);		//ANADIMOS ASI EL OBSERVER O SE LO PASAMOS A UN METODO DEL GESTOR?
		GestorPersonajes.getGestorPersonajes().getPersonajeBlanco().addObserver(this);					//Anadimos el personaje como observable de la vista
		
		this.labels = new JLabel[11][17];							//Bucle que crea la matriz de JLabels de la vista
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				this.labels[i][j] = new JLabel();
				//this.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.contentPane.add(this.labels[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Object[] array = (Object[]) arg;
		int quienLlama = (int) array[0];
		
		if (quienLlama == 2) {
			this.crearTablero(array);
		} 
		else if (quienLlama == 1) {
			this.ponerBomba(array);	
		}
		else if (quienLlama == 3) {
			this.moverPersonaje(array);
		}
		else if (quienLlama == 4) {
			this.ponerPanelBlanco(array);
			System.out.println("Bien"); //Debugging
		}
		else if (quienLlama == 5) { //Por que se llama dos veces al mismo metodo?
			this.ponerPanelBlanco(array);
		}
		else if (quienLlama == 6) {
			this.mostrarExplosion(array);
		}
		else if (quienLlama == 7) {
			this.ponerPanelBomba(array);
		}
		else if (quienLlama == 8) {
			this.moverPersonajeUp(array);
		}
		else if (quienLlama == 9) {
			this.moverPersonajeLeft(array);
		}
		else if (quienLlama == 10) {
			this.moverPersonajeRight(array);
		}
	}

	private void crearTablero(Object[] array) {
		int numeroEntrada = (int) array[1];					//Esto daba error porque se le metia el primer elemento del array
		int y = (int) array[2];
		int x = (int) array[3];
		//System.out.println(numeroEntrada); //Debugging
	
		if (numeroEntrada == 0) {							//Bloque vacio
			this.labels[y][x].setBackground(Color.WHITE);
		}
		else if (numeroEntrada == 1) {						//Bloque blando
			//this.labels[y][x].setBackground(Color.GREEN);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("soft4.png")));
		}
		else if (numeroEntrada == 2) {						//Bloque duro
			//this.labels[y][x].setOpaque(true);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("hard5.png")));
		}
	}
	
	private void ponerBomba(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitewithbomb1.png")));
		this.labels[y][x].setBackground(Color.WHITE);
	}
	
	private void ponerPanelBlanco(Object[] array) { 
		int y = (int) array[2];
		int x = (int) array[1];
		
		//this.labels[y][x].setBackground(Color.WHITE);
		this.labels[y][x].setIcon(null);
	}
	
	private void moverPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			//Pintamos el jugador
			//this.labels[y][x].setBackground(Color.RED);
			this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitefront1.png")));
	}
	
	private void mostrarExplosion(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("blast.gif")));
		this.labels[y][x].setBackground(Color.WHITE);

	} 	
	private void ponerPanelBomba(Object[] array) { //Despues de que se mueva el personaje aparezca la bomba
		int y = (int) array[2];
		int x = (int) array[1];
		
		this.labels[y][x].setBackground(Color.WHITE);
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("bomb1.png")));
	}
	private void moverPersonajeUp(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup2.png")));
		
	}
	private void moverPersonajeLeft(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft2.png")));
	}
	private void moverPersonajeRight(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright2.png")));
		
	}
}
