package modelo;

@SuppressWarnings("deprecation")
public class EnemigoClassic extends BloqueEnemigo {

    public EnemigoClassic(int pY, int pX) {
        super(pY, pX);
    }

    public void pintarEnemigo(int nuevaY, int nuevaX) {
        setChanged();
        notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
    }
}
