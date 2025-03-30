package modelo;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BloqueEnemigo extends Bloque {

    private Timer timer;

    public BloqueEnemigo(int y, int x) {
        super(y, x);
    }
    public void iniciarMovimiento() { //Inicia el timer de los enemigos a movimiento por segundo
        TimerTask moverTask = new TimerTask() {
            @Override
            public void run() {
                mover();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(moverTask, 0, 1000);
    }
    @Override
    public boolean puedoMoverme() {
        return false;
    }

    @Override
    public boolean esDestructible() {
        return true;
    }

    public abstract void mover(); // Cada tipo de enemigo implementa su propio movimiento

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
