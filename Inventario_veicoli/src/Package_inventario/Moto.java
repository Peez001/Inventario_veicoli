package Package_inventario;

/**
 * Classe che rappresenta l'oggetto Moto
 * 
 * @author ChurPeezZoli
 * @since 04/2023
 */
public class Moto extends Veicolo {

	private int cilindrata;

	/**
	 * Costruttore della classe Moto
	 * 
	 * @param marca
	 * @param targa
	 * @param modello
	 * @param cilindrata
	 */
	public Moto(String marca, String targa, String modello, int cilindrata) {
		super(marca, targa, modello);
		this.cilindrata = cilindrata;
	}

	/**
	 * metodo che ritorna la cilindrata della moto
	 * 
	 * @return cilindrata
	 */
	public int getCilindrata() {
		return cilindrata;
	}

	/**
	 * metodo che permette di settare la cilindrata della moto
	 * 
	 * @param cilindrata
	 */
	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}

	/**
	 * metodo che ritorna la stringa che esplicita le proprietà dell'oggetto Moto
	 */
	public String toString() {
		return "Moto con cilindrata di " + this.cilindrata + " cc. " + super.toString();
	}

}
