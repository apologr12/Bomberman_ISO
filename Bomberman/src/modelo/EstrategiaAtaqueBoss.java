package modelo;

@SuppressWarnings("deprecation")
public class EstrategiaAtaqueBoss extends EstrategiaAtaque {
	@Override
    public boolean atacar(int pY, int pX, Bloque[][] tablero, String orientacion) {
        /* //DEBUGGING
        System.out.println("Atacando personaje");
        System.out.println(orientacion);
        System.out.println("Disparo: "+ !tablero[pY][pX-1].eresDisparo());
        System.out.println("Moverme: "+ tablero[pY][pX-1].puedoMoverme());
        System.out.println("Direccion:"+ orientacion.equals("izquierda"));
        System.out.println("Direccion2:"+ orientacion.equals("Izquierda"));
         */

        if (orientacion.equals("Arriba") && !tablero[pY-1][pX].eresDisparo() && tablero[pY-1][pX].puedoMoverme()) {
        	/* Si es el personaje lo matamos directamente */
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY-1, pX)) {
            	GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1);
            } else if (tablero[pY-1][pX].esBoss()) {               /*Si la celda es un Boss, aplicamos danio */
                System.out.println("Boss");
                EnemigoBoss boss = (EnemigoBoss) tablero[pY-1][pX];
                boss.recibirDanio();       // quita un punto de vida
                GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
                return true;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            } else {
            	tablero[pY-1][pX] = GenBloques.getGenBloques().generar("DisparoBoss", pY-1, pX, orientacion);
            	//System.out.println("Disparo"); //Debugging
            	setChanged();
            	notifyObservers(new Object[] {17, pX, pY-1, 1});
            	return true;
            }
        }
        else if (orientacion.equals("Abajo") && !tablero[pY+1][pX].eresDisparo() && tablero[pY+1][pX].puedoMoverme()) {
        	/* Si es el personaje lo matamos directamente */
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY+1, pX)) {
            	GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1);
            } else if (tablero[pY+1][pX].esBoss()) {               /*Si la celda es un Boss, aplicamos danio */
                System.out.println("Boss");
                EnemigoBoss boss = (EnemigoBoss) tablero[pY+1][pX];
                boss.recibirDanio();       // quita un punto de vida
                GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
                return true;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            } else {
            	tablero[pY+1][pX] = GenBloques.getGenBloques().generar("DisparoBoss", pY+1, pX, orientacion);
            	//System.out.println("Disparo"); //Debugging
            	setChanged();
            	notifyObservers(new Object[] {17, pX, pY+1, 1});
            	return true;
            }
        }
        else if (orientacion.equals("Izquierda") && !tablero[pY][pX-1].eresDisparo() && tablero[pY][pX-1].puedoMoverme()) {
        	/* Si es el personaje lo matamos directamente */
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, pX-1)) {
            	GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1);
            } else if (tablero[pY][pX-1].esBoss()) {               /*Si la celda es un Boss, aplicamos danio */
                System.out.println("Boss");
                EnemigoBoss boss = (EnemigoBoss) tablero[pY][pX-1];
                boss.recibirDanio();       // quita un punto de vida
                GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
                return true;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            } else {
            	tablero[pY][pX-1] = GenBloques.getGenBloques().generar("DisparoBoss", pY, pX-1, orientacion);
            	//System.out.println("Disparo"); //Debugging
            	setChanged();
            	notifyObservers(new Object[] {17, pX-1, pY, 1});
            	return true;
            }
        }
        else if (orientacion.equals("Derecha") && !tablero[pY][pX+1].eresDisparo() && tablero[pY][pX+1].puedoMoverme()) {
        	 /* Si es el personaje lo matamos directamente */
            if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, pX+1)) {
            	GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1);
            } else if (tablero[pY][pX+1].esBoss()) {               /*Si la celda es un Boss, aplicamos danio */
                System.out.println("Boss");
                EnemigoBoss boss = (EnemigoBoss) tablero[pY][pX+1];
                boss.recibirDanio();       // quita un punto de vida
                GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
                return true;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            } else {
            	tablero[pY][pX+1] = GenBloques.getGenBloques().generar("DisparoBoss", pY, pX+1, orientacion);
            	//System.out.println("Disparo"); //Debugging
            	setChanged();
            	notifyObservers(new Object[] {17, pX+1, pY, 1});
            	return true;
            }
        }
        return false;

    }

    @Override
    protected void explotarCelda(int pY, int pX, Bloque[][] tablero, String orientacion) {
        //System.out.println("La orientacion es: " + orientacion);
        /* 1.  Si la celda es un Boss, aplicamos danio */
        if (tablero[pY][pX].esBoss()) {
            System.out.println("Boss");
            EnemigoBoss boss = (EnemigoBoss) tablero[pY][pX];
            boss.recibirDanio();       // quita un punto de vida
            GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
            return;             // es para que no se siga ejecutando el codigo y se acabe sustituyendo
            // mas abajo por una explosion donde esta el boss
        }
        else if (tablero[pY][pX].eresDisparo()) { //Si es un disparo hay que parar el timer de ese disparo y que el actual no siga avanzando
        	tablero[pY][pX].pararTimer(); //Paramos el timer del disparo con el que ha colisionado
        	GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
        	return;
        }
        /* 2.  Si es un enemigo lo matamos directamente */
        else if (tablero[pY][pX].eresExplosion() || tablero[pY][pX].esEnemigo()) {
            tablero[pY][pX].pararTimer();
        }
        /* 3.  Si es el personaje lo matamos directamente */
        else if (GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, pX)) {
        	GestorPersonajes.getGestorPersonajes().getPersonaje().meHeMuerto(1);
        } 
        else {
        	tablero[pY][pX] = GenBloques.getGenBloques().generar("DisparoBoss", pY, pX, orientacion); //En caso de que el disparo pueda avanzar, se crea en la nueva posicion
        	// Notificar a la vista que muestre explosi�n
        	setChanged();
        	notifyObservers(new Object[] {17, pX, pY, 1});
        }
    }


    @Override
    public void compAtaque(int pY, int pX, Bloque[][] tablero, String orientacion) {
        tablero[pY][pX] = GenBloques.getGenBloques().generar("Vacio", pY, pX, "");
        
        if (!GestorPersonajes.getGestorPersonajes().getPersonaje().choque(pY, pX)) {
        	setChanged();
            notifyObservers(new Object[] {4, pX, pY, 0}); //Notificamos a la vista de que quite el bloque que habia en las coordenadas (el disparo)
        }
        

        if (orientacion.equals("Arriba") && tablero[pY-1][pX].puedoMoverme()) {		//En funcion de la orientacion se mueve a un sitio u otro
            this.explotarCelda(pY-1,pX,tablero, orientacion);
        }
        else if (orientacion.equals("Abajo") && tablero[pY+1][pX].puedoMoverme()) {
            this.explotarCelda(pY+1,pX,tablero, orientacion);
        }
        else if (orientacion.equals("Izquierda") && tablero[pY][pX-1].puedoMoverme()) {
            this.explotarCelda(pY,pX-1,tablero, orientacion);
        }
        else if (orientacion.equals("Derecha") && tablero[pY][pX+1].puedoMoverme()) {
            this.explotarCelda(pY,pX+1,tablero, orientacion);
        }
        else {
            GestorPersonajes.getGestorPersonajes().getPersonaje().disparoFinalizado();
        }

    }
}
