package modelo;

public class GenEnemigos {

    private static GenEnemigos miGenEnemigos = new GenEnemigos();

    private GenEnemigos() {}

    public static GenEnemigos getGenEnemigos() {
        return miGenEnemigos;
    }

    public Bloque generar(String tipo, int y, int x) {
        if (tipo.equals("EnemigoClassic")) {
            return new EnemigoClassic(y, x);
        }
        else {
            return null;
        }
    }
}
