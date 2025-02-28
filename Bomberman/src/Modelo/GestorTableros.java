package Modelo;

public class GestorTableros {
	private static GestorTableros miGestor = new GestorTableros();
	private TableroClasico tableroClasico = null; //En un futuro como atributos estaran los demas tableros
	
	private GestorTableros() {
	}
	
	public static GestorTableros getGestorTableros() {
		return miGestor;
	}
	
	public TableroClasico getTableroClasico() {		//Esto no me convence nada, pero ahora mismo no se me ocurre otra forma de hacerlo
		if (this.tableroClasico == null) {
            crearTableroClasico();
        }
		return this.tableroClasico;					//(hay que preguntarle a Ander)
	}
	
	public void crearTableroClasico() { // METODO TEMPORAL ANTES DE TENER EL MENU PRINCIPAL
	    this.tableroClasico = TableroClasico.getInstancia();
	    tableroClasico.crearTablero();
	}

}
