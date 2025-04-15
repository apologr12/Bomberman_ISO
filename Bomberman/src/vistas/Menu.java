package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controladores.ControladorMenu;
import modelo.MenuModelo;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


@SuppressWarnings("deprecation")
public class Menu extends JFrame implements Observer {
	private static final long serialVersionUID = -9171935669482541465L;
	private JPanel panelMenu;
	private JPanel fondo;
	private JLabel titulo;
	private JLabel subTitulo;
	private JLabel bombermanBlanco;
	private JLabel bombermanNegro;
	private JLabel boss2;
	private JLabel boss4;
	private JLabel boss3;
	private JLabel textoMenu;
	private String pFondo = "imagenes/fondos/stageBack1.png";

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
		panelMenu.add(getSubTitulo());
		
		
		panelMenu.add(getBombermanBlanco());
		panelMenu.add(getBombermanNegro());
		panelMenu.add(getBoss2());
		panelMenu.add(getBoss4());
		panelMenu.add(getBoss3());
		panelMenu.add(getTextoMenu());
		this.ponerFondo();
		this.startMultiColorText(); //Cambia el color del texto
		
		MenuModelo.getMenu().addObserver(this);
		this.addKeyListener(ControladorMenu.getControlador());
		setResizable(false);
		
	}
	
	private void ponerFondo() {
		fondo = new JPanel() {
		    @Override
		    protected void paintComponent(java.awt.Graphics g) {
		        super.paintComponent(g);
		        ImageIcon background = new ImageIcon(getClass().getResource(pFondo));
		        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
		    }
		};
		
		fondo.setLayout(new GridLayout(1, 1)); // Evita modificar la estructura interna
		fondo.add(panelMenu);
		setContentPane(fondo);
	}
	private JLabel getTitulo() {
		if (titulo == null) {
			titulo = new JLabel("");
			titulo.setBounds(163, 11, 384, 107);
			this.titulo.setIcon(new ImageIcon(this.getClass().getResource("imagenes/title.png")));
			
		}
		return titulo;
	}
	private JLabel getSubTitulo() {
		if (subTitulo == null) {
			subTitulo = new JLabel("");
			subTitulo.setBounds(443, 43, 202, 140);
			this.subTitulo.setIcon(new ImageIcon(this.getClass().getResource("imagenes/classic1.png")));
			
		}
		return subTitulo;
	}
	private JLabel getBombermanBlanco() {
		if (bombermanBlanco == null) {
			bombermanBlanco = new JLabel();
			bombermanBlanco.setBounds(98, 218, 60, 82);
			this.bombermanBlanco.setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/bomber1.png")));
		}
		return bombermanBlanco;
	}
	private JLabel getBombermanNegro() {
		if (bombermanNegro == null) {
			bombermanNegro = new JLabel("");
			bombermanNegro.setBounds(537, 205, 58, 107);
			this.bombermanNegro.setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/bomber2.png")));
			this.bombermanNegro.enable(false);
		}
		return bombermanNegro;
	}
	private JLabel getBoss2() {
		if (boss2 == null) {
			boss2 = new JLabel("");
			boss2.setBounds(249, 105, 216, 228);
			this.boss2.setIcon(new ImageIcon(this.getClass().getResource("imagenes/boss2.png")));
		}
		return boss2;
	}
	private JLabel getBoss4() {
		if (boss4 == null) {
			boss4 = new JLabel("");
			boss4.setBounds(-12, 324, 84, 180);
			this.boss4.setIcon(new ImageIcon(this.getClass().getResource("imagenes/boss4.png")));
		}
		return boss4;
	}
	private JLabel getBoss3() {
		if (boss3 == null) {
			boss3 = new JLabel("");
			boss3.setBounds(627, 105, 118, 238);
			this.boss3.setIcon(new ImageIcon(this.getClass().getResource("imagenes/boss3.png")));
		}
		return boss3;
	}
	private JLabel getTextoMenu() {
		if (textoMenu == null) {
			textoMenu = new JLabel("<html><div style='text-align: center;'>Choose your map and character!<br>Press spacebar to start!</div></html>");
			textoMenu.setFont(new Font("Consolas", Font.BOLD, 25));
			textoMenu.setHorizontalAlignment(SwingConstants.CENTER);
			textoMenu.setBounds(27, 353, 667, 60);
		}
		return textoMenu;
	}
	private void startMultiColorText() { //Solo cambia el color del texto
	    Timer timer = new Timer();
	    Color[] colors = {Color.RED, Color.BLUE, Color.BLACK};
	    final int[] index = {0}; // Usamos un array para modificar la variable dentro del Timer

	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	            SwingUtilities.invokeLater(() -> {
	                textoMenu.setForeground(colors[index[0]]);
	                index[0] = (index[0] + 1) % colors.length; // Cicla entre los colores
	            });
	        }
	    }, 0, 500); // Cambia de color cada 500ms
	}


	@Override
	public void update(Observable o, Object arg) {
		Object[] array = (Object[]) arg;
		int quienLlama = (int) array[0];
		
		if (quienLlama == 1) {
			this.cambiarPersonaje(array);
		} 
		else if (quienLlama == 2) {
			this.IniciarJuego(array);
		}
		else if (quienLlama == 3) {
			this.cambiarTablero(array);
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
		
		if (tablero == 1) {
			Classic frame = new Classic();
			frame.setVisible(true);
			this.setVisible(false);
		} else 	if (tablero == 2) {
			Soft frame = new Soft();
			frame.setVisible(true);
			this.setVisible(false);
		} else 	if (tablero == 3) {
			Empty frame = new Empty();
			frame.setVisible(true);
			this.setVisible(false);
		}
		else if (tablero == 4) {
			Arena frame = new Arena();
			frame.setVisible(true);
			this.setVisible(false);
		}
		

	}
	
	private void cambiarTablero(Object[] array) {
		int tablero = (int) array[1];
		if (tablero == 1) {
			System.out.println("1");
			this.pFondo = "imagenes/fondos/stageBack1.png";
			fondo.repaint(); // Redibujar el panel con la nueva imagen
			this.getSubTitulo().setIcon(new ImageIcon(this.getClass().getResource("imagenes/classic1.png"))); //cambio de titulo indicando el tablero
		} else if (tablero == 2) {
			System.out.println("2");
			this.pFondo = "imagenes/fondos/stageBack3.png"; //En orden del enunciado de egela
			fondo.repaint(); // Redibujar el panel con la nueva imagen
			this.getSubTitulo().setIcon(new ImageIcon(this.getClass().getResource("imagenes/soft1.png"))); //cambio de titulo indicando el tablero
		}else if (tablero == 3) {
			System.out.println("3");
			this.pFondo = "imagenes/fondos/stageBack2.png"; //En orden del enunciado de egela
			fondo.repaint(); // Redibujar el panel con la nueva imagen
			this.getSubTitulo().setIcon(new ImageIcon(this.getClass().getResource("imagenes/empty1.png"))); //cambio de titulo indicando el tablero
		}
		else if (tablero == 4) {
			System.out.println("4");
			this.pFondo = "imagenes/fondos/arenaBack.png";
			fondo.repaint(); // Redibujar el panel con la nueva imagen
			this.getSubTitulo().setIcon(new ImageIcon(this.getClass().getResource("imagenes/empty1.png"))); //cambio de titulo indicando el tablero
		}
		this.repaint();
	}
}
