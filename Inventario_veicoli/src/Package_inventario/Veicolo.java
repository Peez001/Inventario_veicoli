package Package_inventario;

/**
 * Classe che rappresenta un Veicolo generico
 * @author ChurPeezZoli
 * @since 04/2023
 */
public abstract class Veicolo {
	
	private static final String essere = "\u00E9";
	private String marca;
	private String targa;
	private String modello;

	/**
	 * metodo che ritorna la marca del veicolo
	 * @return
	 */
	public String getMarca() {
		return marca;
	}
	
	/**
	 * metodo che permette di settare la marca del veicolo
	 * @param marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/**
	 * metodo che ritorna la targa del veicolo
	 * @return
	 */
	public String getTarga() {
		return targa;
	}
	
	/**
	 * metodo che permette di settare la targa del veicolo
	 * @param targa
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}
	
	/**
	 * metodo che ritorna il modello del veicolo
	 * @return
	 */
	public String getModello() {
		return modello;
	}
	
	/**
	 * metodo che permette di settare il modello del veicolo
	 * @param modello
	 */
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	/**
	 * Costruttore non istanziabile, risparmio codice nelle figlie
	 * @param marca
	 * @param targa
	 * @param modello
	 */
	public Veicolo(String marca, String targa, String modello) {
		this.marca = marca;
		this.targa = targa;
		this.modello = modello;
	}
	
	//\u00E9 = Ã©. 
	/**
	 * metodo che ritorna la stringa che esplicita le proprietà dell'oggetto Veicolo
	 */
	public String toString() {
		return "Ha la targa " + this.targa + ", " + essere + " un modello " + this.modello + ", della marca " + this.marca + ".";
	}
	
}
