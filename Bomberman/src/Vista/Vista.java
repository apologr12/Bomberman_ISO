package Vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class Vista extends JFrame implements Observer {
	
	private JPanel contentPane;
	private JLabel[][] labels;
	
	protected JLabel[][] getLabels() {
		return this.labels;
	}
					
	private void ponerPanelBlanco(Object[] array) { 
		int y = (int) array[2];
		int x = (int) array[1];
		
		//this.labels[y][x].setBackground(Color.WHITE);
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
	
	protected void crearTablero(Object[] array) {
		int numeroEntrada = (int) array[1];					//Esto daba error porque se le metia el primer elemento del array
		int y = (int) array[2];
		int x = (int) array[3];
		//System.out.println(numeroEntrada); //Debugging
	
		if (numeroEntrada == 0) {							//Bloque vacio
			//this.labels[y][x].setBackground(Color.WHITE);
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
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Object[] array = (Object[]) arg;
		int quienLlama = (int) array[0];
		
		if (quienLlama == 2) {
			this.crearTablero(array);
		} 
		else if (quienLlama == 1) {
			this.ponerBombaPersonaje(array);	
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
	
	
	private void moverPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];

			//Pintamos el jugador
			//this.labels[y][x].setBackground(Color.WHITE);
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("whitefront1.png")));
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
	
	private void mostrarExplosion(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("blast.gif")));
		//this.labels[y][x].setBackground(Color.WHITE);

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
				//this.labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				this.contentPane.add(this.labels[i][j]);
			}
		}
	}

}
