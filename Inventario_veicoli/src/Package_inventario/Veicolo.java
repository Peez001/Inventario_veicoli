package Package_inventario;

public abstract class Veicolo {
	
	private static final String É = "\u00E9";
	private String marca;
	private String targa;
	private String modello;
	
	// getters e setters
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	// costruttore non istanziabile, risparmio codice nelle figlie
	public Veicolo(String marca, String targa, String modello) {
		this.marca = marca;
		this.targa = targa;
		this.modello = modello;
	}
	
	//\u00E9 = é. 
	public String toString() {
		return "Ha la targa " + this.targa + ", " + É + " un modello " + this.modello + ", della marca " + this.marca + ".";
	}
	
}
