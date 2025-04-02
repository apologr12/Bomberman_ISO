package modelo;
@SuppressWarnings("deprecation")

public class EnemigoEmpty extends BloqueEnemigo {
        public EnemigoEmpty(int pY, int pX) {
            super(pY, pX);
        }

        public void pintarEnemigo(int nuevaY, int nuevaX) {
            setChanged();
            notifyObservers(new Object[]{13, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
        }
}


