package modelo;

import java.util.Random;

@SuppressWarnings("deprecation")
public class EnemigoClassic extends BloqueEnemigo {

    public EnemigoClassic(int pY, int pX) {
        super(pY, pX);
    }

    @Override
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

	private void moverArriba() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = super.getY() - 1;
        int nuevaX = super.getX();

        if (t.puedoMovermeE(nuevaX, nuevaY)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();

            setChanged();
            notifyObservers(new Object[]{4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
            
        }
    }

    private void moverAbajo() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = super.getY() + 1;
        int nuevaX = super.getX();

        if (t.puedoMovermeE(nuevaX, nuevaY)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();

            setChanged();
            notifyObservers(new Object[] {4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
            
        }
    }

    private void moverIzquierda() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = super.getY();
        int nuevaX = super.getX() - 1;

        if (t.puedoMovermeE(nuevaX, nuevaY)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();

            setChanged();
            notifyObservers(new Object[]{4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setX(nuevaX);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
            
        }
    }

    private void moverDerecha() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = super.getY();
        int nuevaX = super.getX() + 1;

        if (t.puedoMovermeE(nuevaX, nuevaY)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();

            setChanged();
            notifyObservers(new Object[]{4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setX(nuevaX);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
            
        }
    }
}
