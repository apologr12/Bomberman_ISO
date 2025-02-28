package Modelo;

public class BloqueBombaSimple extends BloqueBomba {
	
	
	

    public BloqueBombaSimple(int pX, int pY) {
    	super(pX, pY);											//Asignacion temporal de coordenadas
    }
    
    @Override
    protected void explotarBomba() {
        PersonajeBlanco.getPersonaje().explosionBombaSimple(getX(), getY());
    }
	
}
