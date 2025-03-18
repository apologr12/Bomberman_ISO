package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.ControladorJuego;
import Controlador.ControladorMenu;
import Modelo.GestorPersonajes;
import Modelo.GestorTableros;
import Modelo.MenuModelo;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class Menu extends JFrame implements Observer {
	private static final long serialVersionUID = -9171935669482541465L;
	private JPanel panelMenu;
	private JLabel titulo;
	private JLabel bombermanBlanco;
	private JLabel bombermanNegro;
	private JLabel boss2;
	private JLabel boss4;
	private JLabel boss3;
	private JLabel textoMenu;

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("Menu");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		panelMenu = new JPanel();								//Crear el panel
		panelMenu.setToolTipText("");
	    panelMenu.setOpaque(false); // Permite que se vea el fondo
		panelMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMenu.setLayout(null);
		panelMenu.add(getTitulo());
		
		
		panelMenu.add(getBombermanBlanco());
		panelMenu.add(getBombermanNegro());
		panelMenu.add(getBoss2());
		panelMenu.add(getBoss4());
		panelMenu.add(getBoss3());
		panelMenu.add(getTextoMenu());
		this.ponerFondo();
		
		MenuModelo.getMenu().addObserver(this);
		this.addKeyListener(ControladorMenu.getControlador());
		
	}
	
	private void ponerFondo() {
		JPanel backgroundPanel = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        super.paintComponent(g);
		        ImageIcon background = new ImageIcon(getClass().getResource("back.png"));
		        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		};
		
		backgroundPanel.setLayout(new GridLayout(1, 1)); // Evita modificar la estructura interna
		backgroundPanel.add(panelMenu);
		setContentPane(backgroundPanel);
	}
	private JLabel getTitulo() {
		if (titulo == null) {
			titulo = new JLabel("");
			titulo.setBounds(163, 11, 384, 107);
			this.titulo.setIcon(new ImageIcon(this.getClass().getResource("title.png")));
			
		}
		return titulo;
	}
	private JLabel getBombermanBlanco() {
		if (bombermanBlanco == null) {
			bombermanBlanco = new JLabel();
			bombermanBlanco.setBounds(98, 218, 60, 82);
			this.bombermanBlanco.setIcon(new ImageIcon(this.getClass().getResource("bomber1.png")));
		}
		return bombermanBlanco;
	}
	private JLabel getBombermanNegro() {
		if (bombermanNegro == null) {
			bombermanNegro = new JLabel("");
			bombermanNegro.setBounds(537, 205, 58, 107);
			this.bombermanNegro.setIcon(new ImageIcon(this.getClass().getResource("bomber2.png")));
			this.bombermanNegro.enable(false);
		}
		return bombermanNegro;
	}
	private JLabel getBoss2() {
		if (boss2 == null) {
			boss2 = new JLabel("");
			boss2.setBounds(249, 105, 216, 228);
			this.boss2.setIcon(new ImageIcon(this.getClass().getResource("boss2.png")));
		}
		return boss2;
	}
	private JLabel getBoss4() {
		if (boss4 == null) {
			boss4 = new JLabel("");
			boss4.setBounds(-12, 324, 84, 180);
			this.boss4.setIcon(new ImageIcon(this.getClass().getResource("boss4.png")));
		}
		return boss4;
	}
	private JLabel getBoss3() {
		if (boss3 == null) {
			boss3 = new JLabel("");
			boss3.setBounds(627, 105, 118, 238);
			this.boss3.setIcon(new ImageIcon(this.getClass().getResource("boss3.png")));
		}
		return boss3;
	}
	private JLabel getTextoMenu() {
		if (textoMenu == null) {
			textoMenu = new JLabel("<HTML>Choose your character!<br>Press spacebar to start!</HTML>");
			textoMenu.setFont(new Font("Consolas", Font.BOLD, 25));
			textoMenu.setHorizontalAlignment(SwingConstants.CENTER);
			textoMenu.setBounds(27, 353, 667, 47);
		}
		return textoMenu;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Object[] array = (Object[]) arg;
		int quienLlama = (int) array[0];
		
		if (quienLlama == 1) {
			this.cambiarPersonaje(array);
		} 
		else if (quienLlama == 2) {
			this.IniciarJuego(array);
		}
	}
	private void cambiarPersonaje(Object[] array) {
		int personaje = (int) array[1];
		if (personaje == 1) {
			System.out.println("1");
			this.bombermanBlanco.enable(true);
			this.bombermanNegro.enable(false);
		} else if (personaje == 2) {
			System.out.println("2");
			this.bombermanBlanco.enable(false);
			this.bombermanNegro.enable(true);
		}
		this.repaint();
	}
	private void IniciarJuego(Object[] array) {
		int personaje = (int) array[1];
		int tablero = (int) array[2];
		
		if(tablero == 1) {
			Classic frame = new Classic();
			frame.setVisible(true);
			this.setVisible(false);
		}

	}
}
