package Vista;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Classic() {
		setTitle("Classic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);								//Crea el panel principal sobre el que van a estar los distintos JLabel
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.addKeyListener(Controlador.getControlador());	//Anade la detección de pulsación de teclas
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(11, 17, 0, 0));
		
		Tablero.getTablero().addObserver(this);						//Anadimos el tablero como observable de la vista
		
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
		int numeroEntrada = (int) array[0];
		int y = (int) array[1];
		int x = (int) array[2];
		//System.out.println(numeroEntrada); //Debugging
		
		if (numeroEntrada == 0) {
			this.labels[y][x].setOpaque(true);
		}
		else if (numeroEntrada == 1) {
			this.labels[y][x].setOpaque(true);
			this.labels[y][x].setBackground(Color.LIGHT_GRAY);
		}
		else if (numeroEntrada == 2) {
			this.labels[y][x].setOpaque(true);
			this.labels[y][x].setBackground(Color.BLACK);
		}
	}
}
