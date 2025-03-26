package vistas;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorJuego;
import modelo.GestorPersonajes;
import modelo.GestorTableros;

@SuppressWarnings("deprecation")
public abstract class VistaJuego extends JFrame implements Observer {
	private static final long serialVersionUID = -5000978209518964435L;
	private JPanel contentPane;
	private JLabel[][] labels;
	
	protected VistaJuego() {
	    this.addKeyListener(ControladorJuego.getControlador());  		// Agregar el KeyListener al JFrame en lugar del JPanel
		GestorTableros.getGestorTableros().getTablero().addObserver(this);
		GestorPersonajes.getGestorPersonajes().getPersonaje().addObserver(this);
	}
	
	protected JLabel[][] getLabels() {
		return this.labels;
	}
					
	private void ponerPanelBlanco(Object[] array) { 
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(null);
	}
	
	protected void crearPaneles() {
		contentPane = new JPanel();
		contentPane.setToolTipText("");
	    contentPane.setOpaque(false); // Permite que se vea el fondo
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(11, 17, 0, 0));
	}
	
	protected void setFondo(JPanel pPanel) {
		pPanel.add(contentPane);
	}
	
	protected void pintarTablero(Object[] array) {
		int numeroEntrada = (int) array[1];
		int y = (int) array[2];
		int x = (int) array[3];
		//System.out.println(numeroEntrada); //Debugging
	
		if (numeroEntrada == 0) {							//Bloque vacio
		}
		else if (numeroEntrada == 1) {						//Bloque blando
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("soft4.png")));
		}
		else if (numeroEntrada == 2) {						//Bloque duro
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("hard5.png")));
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Object[] array = (Object[]) arg;
		int quienLlama = (int) array[0];
		
		if (quienLlama == 1) {
			this.ponerBombaPersonaje(array);
		}
		else if (quienLlama == 2) {
			this.pintarTablero(array);
		} 
		else if (quienLlama == 3) {
			this.moverPersonaje(array);
		}
		else if (quienLlama == 4) {
			this.ponerPanelBlanco(array);
			System.out.println("Bien"); //Debugging
		}
		else if (quienLlama == 5) {
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
	
	
	private void moverPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			//Pintamos el jugador
		Random random = new Random();
		int numAleat = random.nextInt(4) + 1;
		if (numAleat == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitedown1.png")));
		}
		else if (numAleat == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitedown2.png")));
		}
		else if (numAleat == 3) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitedown3.png")));
		}
		else if (numAleat == 4) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitedown4.png")));
		}
	}
	
	private void moverPersonajeUp(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;
		if (numAleat == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup1.png")));
		}
		else if (numAleat == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup2.png")));
		}
		else if (numAleat == 3) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup3.png")));
		}
		else if (numAleat == 4) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup4.png")));
		}
		else if (numAleat == 5) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteup5.png")));
		}
		
		
	}
	private void moverPersonajeLeft(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;
		if (numAleat == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft1.png")));
		}
		else if (numAleat == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft2.png")));
		}
		else if (numAleat == 3) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft3.png")));
		}
		else if (numAleat == 4) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft4.png")));
		}
		else if (numAleat == 5) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteleft5.png")));
		}
	}
	private void moverPersonajeRight(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;
		if (numAleat == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright1.png")));
		}
		else if (numAleat == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright2.png")));
		}
		else if (numAleat == 3) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright3.png")));
		}
		else if (numAleat == 4) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright4.png")));
		}
		else if (numAleat == 5) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whiteright5.png")));
		}
		
	}
	
	private void mostrarExplosion(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("miniBlast1.gif")));
	} 
	
	private void ponerBombaPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitewithbomb1.png")));
		this.labels[y][x].setBackground(Color.WHITE);
	}
	
	private void ponerPanelBomba(Object[] array) { //Despues de que se mueva el personaje aparezca la bomba
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("bomb1.png")));
	}
	
	protected void crearTablero() {
		this.labels = new JLabel[11][17];							//Bucle que crea la matriz de JLabels de la vista
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 17; j++) {
				this.labels[i][j] = new JLabel();
				this.contentPane.add(this.labels[i][j]);
			}
		}
	}

}
