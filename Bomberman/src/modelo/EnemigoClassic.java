package modelo;

import java.util.Random;

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
        int nuevaY = getY() - 1;
        int nuevaX = getX();

        if (t.puedoMoverme(nuevaY, nuevaX)) {
            int antiguaY = getY();
            int antiguaX = getX();

            setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY});
        }
    }

    private void moverAbajo() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = getY() + 1;
        int nuevaX = getX();

        if (t.puedoMoverme(nuevaY, nuevaX)) {
            int antiguaY = getY();
            int antiguaX = getX();

            setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{12, nuevaX, nuevaY});
        }
    }

    private void moverIzquierda() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = getY();
        int nuevaX = getX() - 1;

        if (t.puedoMoverme(nuevaY, nuevaX)) {
            int antiguaY = getY();
            int antiguaX = getX();

            setX(nuevaX);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{13, nuevaX, nuevaY});
        }
    }

    private void moverDerecha() {
        Tablero t = GestorTableros.getGestorTableros().getTablero();
        int nuevaY = getY();
        int nuevaX = getX() + 1;

        if (t.puedoMoverme(nuevaY, nuevaX)) {
            int antiguaY = getY();
            int antiguaX = getX();

            setX(nuevaX);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{14, nuevaX, nuevaY});
        }
    }
}
