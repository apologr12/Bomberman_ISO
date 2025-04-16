package modelo;

public class GenEnemigos {

    private static GenEnemigos miGenEnemigos = new GenEnemigos();

    private GenEnemigos() {}

    public static GenEnemigos getGenEnemigos() {
        return miGenEnemigos;
    }

    public BloqueEnemigo generar(String tipo, int y, int x) {
        if (tipo.equals("EnemigoClassic")) {
            return new EnemigoClassic(y, x);
        }
        else if (tipo.equals("EnemigoSoft")) {
            return new EnemigoSoft(y, x);
        }
        else if (tipo.equals("EnemigoEmpty")) {
            return new EnemigoEmpty(y, x);
        }
        else if (tipo.equals("EnemigoBoss")) {
        	return new EnemigoBoss(y, x);
        }
        else {
            return null;
        }
    }
}
