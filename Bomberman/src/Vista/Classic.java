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
					Tablero.getTablero().crearTablero();
					Personaje.getPersonaje().mostrarPersonaje();
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(11, 17, 0, 0));

		setContentPane(contentPane);
		
	    this.addKeyListener(Controlador.getControlador());  		// Agregar el KeyListener al JFrame en lugar del JPanel (ns porque)
	    setFocusable(true); 										// Hacer que el JFrame reciba eventos de teclado
	    requestFocus();
		
		
		Tablero.getTablero().addObserver(this);						//Anadimos el tablero como observable de la vista
		Personaje.getPersonaje().addObserver(this);					//Anadimos el personaje como observable de la vista
		
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
		
		
		if (quienLlama == 2) {									//El apano este habria que solucionarlo llamando a distintos metodos privados en esta clase
			this.crearTablero(array);
		} 
		else if (quienLlama == 1) {
			this.ponerBomba(array);	
		}
		else if (quienLlama == 3) {
			this.moverPersonaje(array);
		}
		else if (quienLlama == 4) {
			this.dejarDeMostrarPersonaje(array);
		}
		else if (quienLlama == 5) {
			this.ponerPanelBlanco(array);
		}
	}
	
	private void crearTablero(Object[] array) {
		int numeroEntrada = (int) array[1];					//Esto daba error porque se le metia el primer elemento del array
		int y = (int) array[2];
		int x = (int) array[3];
		//System.out.println(numeroEntrada); //Debugging
	
		if (numeroEntrada == 0) {							//Bloque vacio
			this.labels[y][x].setOpaque(true);
			this.labels[y][x].setBackground(Color.WHITE);
		}
		else if (numeroEntrada == 1) {						//Bloque blando
			this.labels[y][x].setOpaque(true);
			//this.labels[y][x].setBackground(Color.GREEN);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("soft4.png")));
		}
		else if (numeroEntrada == 2) {						//Bloque duro
			//this.labels[y][x].setOpaque(true);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("hard5.png")));
		}
		//else if (numeroEntrada == 3) {						//Cual es el sentido de este else if de bomba?
		    //this.labels[y][x].setOpaque(true);
		    //this.labels[y][x].setBackground(Color.ORANGE);
		//}
	}
	
	private void ponerBomba(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		
		// si hay bomba se deja el color de bomba.
		//if (Tablero.getTablero().hayBombaEn(p1, p2)) {
		  //labels[p1][p2].setBackground(Color.ORANGE);  // bomba
		//} 
		//else {
		    //labels[p1][p2].setBackground(Color.WHITE);   // vacio
		//}
		
		//Creo que esta comprobacion realmente no es necesaria porque ya se comprueba en el tablero
		//Ademas, no podemos acceder al tablero desde la vista sin pasar por el controlador.
		//Creo que basta unicamente con actualizar su color y ya.
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("bomb1.png")));
		this.labels[y][x].setBackground(Color.WHITE);
		//this.labels[y][x].setBackground(Color.ORANGE);
	}
	
	private void ponerPanelBlanco(Object[] array) { //TENEMOS POR DUPLICADO ESTE METODO. HABRIA QUE QUITAR UNO DE LOS 2
		int y = (int) array[2];
		int x = (int) array[1];
		
		//this.labels[y][x].setBackground(Color.WHITE);
		this.labels[y][x].setIcon(null);
		this.labels[y][x].setBackground(Color.WHITE);
		this.labels[y][x].setIcon(null);
	}
	
	private void moverPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

		//Pintamos el jugador
			this.labels[y][x].setOpaque(true);
			//this.labels[y][x].setBackground(Color.RED);
			this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("blackfront1.png")));
	}
	
	private void dejarDeMostrarPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setOpaque(true);
		this.labels[y][x].setBackground(Color.WHITE);
		this.labels[y][x].setIcon(null);
	}
}
