package Package_inventario;

/**
 * Classe che rappresenta l'oggetto Automobile
 * 
 * @author ChurPeezZoli
 * @since 04/2023
 */
public class Automobile extends Veicolo {

	private int numero_porte;

	/**
	 * Costruttore della classe Automobile
	 * 
	 * @param marca
	 * @param targa
	 * @param modello
	 * @param numero_porte
	 */
	public Automobile(String marca, String targa, String modello, int numero_porte) {
		super(marca, targa, modello);
		this.numero_porte = numero_porte;
	}

	/**
	 * metodo che ritorna il numero delle porte dell'automobile
	 * 
	 * @return numero_porte
	 */
	public int getNumero_porte() {
		return numero_porte;
	}

	/**
	 * metodo che permette di settare il numero di porte dell'automobile
	 * 
	 * @param numero_porte
	 */
	public void setNumero_porte(int numero_porte) {
		this.numero_porte = numero_porte;
	}

	/**
	 * metodo che ritorna la stringa che esplicita le proprietà dell'oggetto
	 * Automobile
	 */
	public String toString() {
		return "Automobile con " + this.numero_porte + " porte. " + super.toString();
	}

}
