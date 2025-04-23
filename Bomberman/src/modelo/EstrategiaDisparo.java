package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaDisparo extends EstrategiaAtaque {
    public EstrategiaDisparo() {
    }


    @Override
    public boolean atacar(int pY, int pX, Bloque[][] tablero, String orientacion) {
        System.out.println("Atacando personaje");
        System.out.println(orientacion);
        if (orientacion.equals("Arriba") && !tablero[pY-1][pX].eresDisparo() && tablero[pY-1][pX].puedoMoverme()) {
            tablero[pY-1][pX] = GenBloques.getGenBloques().generar("Disparo", pY-1, pX, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX, pY-1, 0});
            return true;
        }
        else if (orientacion.equals("Abajo") && !tablero[pY+1][pX].eresDisparo() && tablero[pY+1][pX].puedoMoverme()) {
            tablero[pY+1][pX] = GenBloques.getGenBloques().generar("Disparo", pY+1, pX, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX, pY+1, 0});
            return true;
        }
        else if (orientacion.equals("Izquierda") && !tablero[pY][pX-1].eresDisparo() && tablero[pY][pX-1].puedoMoverme()) {
            tablero[pY][pX-1] = GenBloques.getGenBloques().generar("Disparo", pY, pX-1, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX-1, pY, 0});
            return true;
        }
        else if (orientacion.equals("Derecha") && !tablero[pY][pX+1].eresDisparo() && tablero[pY][pX+1].puedoMoverme()) {
            tablero[pY][pX+1] = GenBloques.getGenBloques().generar("Disparo", pY, pX+1, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX+1, pY, 0});
            return true;
        }
        return false;

    }

    @Override
    protected void explotarCelda(int pY, int pX, Bloque[][] tablero, String orientacion) {
        System.out.println("La orientacion es: " + orientacion);
        /* 1.  Si la celda es un Boss, aplicamos daño */
        if (tablero[pY][pX].esBoss()) {
            System.out.println("Boss");
            EnemigoBoss boss = (EnemigoBoss) tablero[pY][pX];
            boss.recibirDanio();       // quita un punto de vida
            return;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            // mas abajo por una explosion donde esta el boss
        }

        /* 2.  Si es un enemigo lo matamos directamente */
        if (tablero[pY][pX].eresExplosion() || tablero[pY][pX].esEnemigo()) {
            tablero[pY][pX].pararTimer();
        }
        tablero[pY][pX] = GenBloques.getGenBloques().generar("Disparo", pY, pX, orientacion);
        // Notificar a la vista que muestre explosi�n
        setChanged();
        notifyObservers(new Object[] {17, pX, pY, 0});
    }


    @Override
    public void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion) {
        tablero[pY][pX] = GenBloques.getGenBloques().generar("Vacio", pY, pX, "");
        setChanged();
        notifyObservers(new Object[] {4, pX, pY, 0}); //Notificamos a la vista de que quite el bloque que habia en las coordenadas (el disparo)

        if (orientacion.equals("Arriba") && !tablero[pY-1][pX].eresDisparo() && tablero[pY-1][pX].puedoMoverme()) {		//En funcion de la orientacion se mueve a un sitio u otro
            //tablero[pY-1][pX] = GenBloques.getGenBloques().generar("Disparo", pY-1, pX, orientacion);
            this.explotarCelda(pY-1,pX,tablero, orientacion);
            //System.out.println("Disparo"); //Debugging
            //setChanged();
            //notifyObservers(new Object[] {17, pX, pY-1, 0});
        }
        else if (orientacion.equals("Abajo") && !tablero[pY+1][pX].eresDisparo() && tablero[pY+1][pX].puedoMoverme()) {
            this.explotarCelda(pY+1,pX,tablero, orientacion);
            /*
            tablero[pX+1][pX] = GenBloques.getGenBloques().generar("Disparo", pY+1, pX, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX, pY+1, 0});
            */
        }
        else if (orientacion.equals("Izquierda") && !tablero[pY][pX-1].eresDisparo() && tablero[pY][pX-1].puedoMoverme()) {
            this.explotarCelda(pY,pX-1,tablero, orientacion);
         /*
            tablero[pY][pX-1] = GenBloques.getGenBloques().generar("Disparo", pY, pX-1, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX-1, pY, 0});
         */
        }
        else if (orientacion.equals("Derecha") && !tablero[pY][pX+1].eresDisparo() && tablero[pY][pX+1].puedoMoverme()) {
            this.explotarCelda(pY,pX+1,tablero, orientacion);
          /*  tablero[pY][pX+1] = GenBloques.getGenBloques().generar("Disparo", pY, pX+1, orientacion);
            System.out.println("Disparo"); //Debugging
            setChanged();
            notifyObservers(new Object[] {17, pX+1, pY, 0});
           */
        }
        else {
            GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
        }

    }

}
