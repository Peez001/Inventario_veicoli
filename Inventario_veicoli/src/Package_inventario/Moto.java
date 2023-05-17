package Package_inventario;

public class Moto extends Veicolo {

	private int cilindrata;
	
	public Moto(String marca, String targa, String modello, int cilindrata) {
		super(marca, targa, modello);
		this.cilindrata = cilindrata;
	}

	public int getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	public String toString() {
		return "Questo veicolo \u00E9 una moto con una "
				+ "cilindrata di "+this.cilindrata+
		" cc. "+super.toString();
	}
	
}
