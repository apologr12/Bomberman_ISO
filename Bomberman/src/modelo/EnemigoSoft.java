package modelo;
@SuppressWarnings("deprecation")

public class EnemigoSoft extends BloqueEnemigo {
        public EnemigoSoft(int pY, int pX) {
            super(pY, pX);
        }

        public void pintarEnemigo(int nuevaY, int nuevaX) {
            setChanged();
            notifyObservers(new Object[]{12, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
        }
}


