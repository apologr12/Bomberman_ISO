package modelo;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
        timer = new Timer();
        timer.scheduleAtFixedRate(moverTask, 0, 2000);
    }
    @Override
    public boolean puedoMoverme() {
        return false;
    }

    @Override
    public boolean esDestructible() {
        return true;
    }

    protected abstract void moverArriba();
    protected abstract void moverAbajo();
    protected abstract void moverIzquierda();
    protected abstract void moverDerecha();
    
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

}
