package Vista;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Personaje;
import Modelo.Tablero;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.KeyAdapter;

public class Classic extends JFrame implements Observer {

	private JPanel contentPane;
	private JLabel[][] labels;
	private int p1,p2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Classic frame = new Classic();
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
	@SuppressWarnings("deprecation")
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
				this.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
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
			int numeroEntrada = (int) array[0];
			int y = (int) array[1];
			int x = (int) array[2];
			//System.out.println(numeroEntrada); //Debugging
		
			if (numeroEntrada == 0) {							//Bloque vacio
				this.labels[y][x].setOpaque(true);
			}
			else if (numeroEntrada == 1) {						//Bloque blando
				this.labels[y][x].setOpaque(true);
				this.labels[y][x].setBackground(Color.GREEN);
			}
			else if (numeroEntrada == 2) {						//Bloque duro
				this.labels[y][x].setOpaque(true);
				this.labels[y][x].setBackground(Color.BLACK);
			}
			else if (numeroEntrada == 3) {						//Bomba
			    this.labels[y][x].setOpaque(true);
			    this.labels[y][x].setBackground(Color.ORANGE);
				}
			
		} else if (quienLlama == 1) {		
			int y = (int) array[0];
			int x = (int) array[1];
			
			// si hay bomba se deja el color de bomba.
			if (Tablero.getTablero().hayBombaEn(p1, p2)) {
			    labels[p1][p2].setBackground(Color.ORANGE);  // bomba
			} else {
			    labels[p1][p2].setBackground(Color.WHITE);   // vacío
			}
			
			// pintamos el jugador
			labels[y][x].setOpaque(true);
			labels[y][x].setBackground(Color.RED);
			
			p1=y;
			p2=x;			
		}
		
	}
}
