package modelo;

@SuppressWarnings("deprecation")
public class EnemigoClassic extends BloqueEnemigo {

    public EnemigoClassic(int pY, int pX) {
        super(pY, pX);
    }

    

	protected void moverArriba() {
        int nuevaY = super.getY() - 1;
        int nuevaX = super.getX();
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    protected void moverAbajo() {
        int nuevaY = super.getY() + 1;
        int nuevaX = super.getX();
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    protected void moverIzquierda() {
        int nuevaY = super.getY();
        int nuevaX = super.getX() - 1;
        this.movimientoGeneral(nuevaY, nuevaX);
    }

    protected void moverDerecha() {
        int nuevaY = super.getY();
        int nuevaX = super.getX() + 1;
        this.movimientoGeneral(nuevaY, nuevaX);
    }
    
    private void movimientoGeneral(int nuevaY, int nuevaX) {
    	Tablero t = GestorTableros.getGestorTableros().getTablero();
    	if (t.puedoMovermeE(nuevaY, nuevaX)) {
            int antiguaY = super.getY();
            int antiguaX = super.getX();
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choqueEnemigo(nuevaY, nuevaX)) { //Revisar si el personaje esta en las nuevas coordenadas
            	System.out.println("Enemigo tocado.");
    			System.exit(1);
            }
            setChanged();
            notifyObservers(new Object[]{4, antiguaX, antiguaY}); //Despintar enemigo en posicion antigua
            super.setX(nuevaX);
            super.setY(nuevaY);
            t.moverEnemigo(this, antiguaY, antiguaX, nuevaY, nuevaX);

            setChanged();
            notifyObservers(new Object[]{11, nuevaX, nuevaY}); //Pintar enemigo en posicion nueva
            
        }
    }
}
