package Package_inventario;

/**
 * Classe che rappresenta l'oggetto Automobile
 * @author ChurPeezZoli
 * @since 04/2023
 */
public class Camion extends Veicolo {

	private int potata_carico;
	
	/**
	 * Costruttore della classe Automobile
	 * @param marca
	 * @param targa
	 * @param modello
	 * @param potata_carico
	 */
	public Camion(String marca, String targa, String modello, int potata_carico) {
		super(marca, targa, modello);
		this.potata_carico = potata_carico;
	}

	/**
	 * metodo che ritorna la portata carico dell'automobile
	 * @return potata_carico
	 */
	public int getPotata_carico() {
		return potata_carico;
	}

	/**
	 * metodo che permette di settare la portata carico dell'automobile
	 * @param potata_carico
	 */
	public void setPotata_carico(int potata_carico) {
		this.potata_carico = potata_carico;
	}
	
	/**
	 * metodo che ritorna la stringa che esplicita le proprietà dell'oggetto Automobile
	 */
	public String toString() {
		return "Camion con una portata massima di " + this.potata_carico + " tonnellate. " + super.toString();
	}
	
}
