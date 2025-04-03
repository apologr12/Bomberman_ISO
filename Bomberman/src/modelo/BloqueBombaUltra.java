package modelo;

public class BloqueBombaUltra extends BloqueBomba {
	
	
	

    public BloqueBombaUltra(int pY, int pX) {
    	super(pY, pX);											//Asignacion temporal de coordenadas
    }
    
    @Override
    protected void explotarBomba() {
    	GestorPersonajes.getGestorPersonajes().getPersonaje().explosionBomba(super.getY(), super.getX());
    }
	
}