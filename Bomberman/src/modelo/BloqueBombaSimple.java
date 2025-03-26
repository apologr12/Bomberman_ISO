package modelo;

public class BloqueBombaSimple extends BloqueBomba {
	
	
	

    public BloqueBombaSimple(int pY, int pX) {
    	super(pY, pX);											//Asignacion temporal de coordenadas
    }
    
    @Override
    protected void explotarBomba() {
    	GestorPersonajes.getGestorPersonajes().getPersonaje().explosionBomba(super.getX(), super.getY());
    }
	
}
