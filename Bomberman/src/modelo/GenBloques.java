package modelo;

public class GenBloques {
	private static GenBloques miGenBombas=new GenBloques();
	private GenBloques() {}
	public static GenBloques getGenBloques(){return miGenBombas;}
	
	public Bloque generar(String pTipo,int pY,int pX) {
		if (pTipo.equals("Vacio")) {
			BloqueVacio nuevo=new BloqueVacio(pY,pX);
			System.out.print("Vacio generado");
			return nuevo;
		}
		else if (pTipo.equals("Blando")) {
			BloqueBlando nuevo=new BloqueBlando(pY,pX);
			return nuevo;
		}
		else if (pTipo.equals("Duro")) {
			BloqueDuro nuevo=new BloqueDuro(pY,pX);
			return nuevo;
		}
		else if (pTipo.equals("BombaSimple")) {
			BloqueBombaSimple nuevo=new BloqueBombaSimple(pY,pX);
			return nuevo;
		}
		else if (pTipo.equals("BombaUltra")) {
			BloqueBombaUltra nuevo=new BloqueBombaUltra(pY,pX);
			return nuevo;
		}
		else if (pTipo.equals("Explosion")) {
			BloqueExplosion nuevo=new BloqueExplosion(pY,pX);
			return nuevo;
		}
		else return null;
	}
	
}
