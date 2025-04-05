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
		setResizable(false);
	}
	
	protected JLabel[][] getLabels() {
		return this.labels;
	}
					
	private void quitarIcono(Object[] array) { 
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
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/soft4.png")));
		}
		else if (numeroEntrada == 2) {						//Enemigo globo
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/baloon1.png")));
		}
		else if (numeroEntrada == 3) {						//Enemigo azul
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/doria2.png")));
		}
		else if (numeroEntrada == 4) {						//Enemigo morado
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/pass2.png")));
		}
		else if (numeroEntrada == 5) {						//Bloque duro
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/hard5.png")));
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
			this.quitarIcono(array);
			System.out.println("Bien"); //Debugging
		}
		else if (quienLlama == 5) {
			this.quitarIcono(array);
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
		else if (quienLlama == 11) {
			System.out.println("movimientoenemigo");
			this.moverEnemigoClassic(array);
		}
		else if (quienLlama == 12) {
			System.out.println("movimientoenemigo");
			this.moverEnemigoSoft(array);
		}
		else if (quienLlama == 13) {
			System.out.println("movimientoenemigo");
			this.moverEnemigoEmpty(array);
		}
		else if (quienLlama == 15) {
			GestorTableros.getGestorTableros().getTablero().addObserverEnemigos(this);
			GestorTableros.getGestorTableros().getTablero().addObserverEstrategia(this);
		}
		else if (quienLlama == 16) {
			this.jugadorMuerto(array);
		}

	}
	
	
	private void moverPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];

			//Pintamos el jugador
		Random random = new Random();
		int numAleat = random.nextInt(4) + 1;
		if(personaje == 1) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitedown1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitedown2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitedown3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitedown4.png")));
			}
		} else if(personaje == 2) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackdown1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackdown2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackdown3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackdown4.png")));
			}	
		}
	}
	
	private void moverPersonajeUp(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;

		if(personaje == 1) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteup1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteup2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteup3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteup4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteup5.png")));
			}
		} else if(personaje == 2) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackup1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackup2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackup3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackup4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackup5.png")));
			}	
		}
		
	}
	private void moverPersonajeLeft(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;

		if(personaje == 1) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteleft1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteleft2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteleft3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteleft4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteleft5.png")));
			}
		} else if(personaje == 2) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackleft1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackleft2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackleft3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackleft4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackleft5.png")));
			}	
		}
	}
	private void moverPersonajeRight(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];

		Random random = new Random();
		int numAleat = random.nextInt(5) + 1;

		if(personaje == 1) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteright1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteright2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteright3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteright4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whiteright5.png")));
			}
		} else if(personaje == 2) {
			if (numAleat == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackright1.png")));
			}
			else if (numAleat == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackright2.png")));
			}
			else if (numAleat == 3) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackright3.png")));
			}
			else if (numAleat == 4) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackright4.png")));
			}
			else if (numAleat == 5) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackright5.png")));
			}	
		}
		
	}
	
	private void mostrarExplosion(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int bomba = (int) array[3];
		
		if(bomba == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/bombas/miniBlast1.gif")));
		} else if(bomba == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/bombas/miniBlast3.gif")));
		}
	} 
	
	private void ponerBombaPersonaje(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];
		
		if(personaje == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitewithbomb1.png")));
		} else if(personaje == 2) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackwithbomb2.png")));
		}
	}
	
	private void ponerPanelBomba(Object[] array) { //Despues de que se mueva el personaje aparezca la bomba
		int y = (int) array[2];
		int x = (int) array[1];
		int bomba = (int) array[3];
		
		if(bomba == 1) {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/bombas/bomb1.png")));
		} else {
			this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/bombas/bomb2.png")));
		}
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

	private void moverEnemigoClassic(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/baloon1.png")));
	}

	private void moverEnemigoSoft(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/doria2.png")));
	}

	private void moverEnemigoEmpty(Object[] array) {
		int y = (int) array[2];
		int x = (int) array[1];
		this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/pass2.png")));
	}
	private void jugadorMuerto(Object[] array) {
		this.removeKeyListener(ControladorJuego.getControlador()); //Eliminamos al jugador la posibilidad de moverse (no rompe MVC, no?)
		int y = (int) array[2];
		int x = (int) array[1];
		int personaje = (int) array[3];
		int motivo = (int) array[4]; //Motivo por el que se muere el personaje
		
		if (motivo == 2) { //Se ha muerto por explosion de bomba
			if (personaje == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/onFire2.png")));
			} else if (personaje == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/onFire4.png")));
			}
		}
		else if (motivo == 1) { //Se ha muerto por enemigo
			if (personaje == 1) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeBlanco/whitehappy1.png")));
			} else if (personaje == 2) {
				this.labels[y][x].setIcon(new ImageIcon(this.getClass().getResource("imagenes/personajeNegro/blackhappy1.png")));
			}
		}
		
	}


}
