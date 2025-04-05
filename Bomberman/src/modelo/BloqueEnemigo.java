package modelo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public abstract class BloqueEnemigo extends Bloque {

    private Timer timer;

    public BloqueEnemigo(int y, int x) {
        super(y, x);
    }
    public void iniciarMovimiento() { //Inicia el timer de los enemigos a movimiento cada dos segundos
        TimerTask moverTask = new TimerTask() {
            @Override
            public void run() {
                mover();
            }
        };
        Random rand = new Random();
        int retardoInicial = rand.nextInt(301);
        int periodo = 500 + rand.nextInt(201);

        timer = new Timer();
        timer.scheduleAtFixedRate(moverTask,  retardoInicial, periodo);
    }
    @Override
    public boolean puedoMoverme() {
        return false;
    }

    @Override
    public boolean esDestructible() {
        return true;
    }

    public void moverArriba() {
        int nuevaY = super.getY() - 1;
        int nuevaX = super.getX();
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    public void moverAbajo() {
        int nuevaY = super.getY() + 1;
        int nuevaX = super.getX();
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    public void moverIzquierda() {
        int nuevaY = super.getY();
        int nuevaX = super.getX() - 1;
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    public void moverDerecha() {
        int nuevaY = super.getY();
        int nuevaX = super.getX() + 1;
        this.movimientoGeneral(nuevaY, nuevaX);
    }
    
    public void mover() {
        Random rand = new Random();
        int direccion = rand.nextInt(4); // 0: arriba, 1: abajo, 2: izquierda, 3: derecha

        if (direccion == 0) {
            moverArriba();
        } else if (direccion == 1) {
            moverAbajo();
        } else if (direccion == 2) {
            moverIzquierda();
        } else if (direccion == 3) {
            moverDerecha();
        }
    }

    @Override
    public void pararTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public boolean esEnemigo() {
        return true;
    }

	private void movimientoGeneral(int nuevaY, int nuevaX) {
		boolean heMatado = false;
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        if (t.puedoMovermeE(nuevaY, nuevaX)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(nuevaY, nuevaX)) { //Revisar si el personaje esta en las nuevas coordenadas
                heMatado = true;
            }
            setChanged();
            notifyObservers(new Object[]{4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setX(nuevaX);
            super.setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            pintarEnemigo(nuevaY, nuevaX);
            
            if (heMatado) {
            	System.out.println("Enemigo tocado.");
                GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1); //Se le indica al personaje que se ha muerto porque lo ha matado un enemigo
            }
            

        }
    }

    protected abstract void pintarEnemigo(int nuevaY, int nuevaX);
}
