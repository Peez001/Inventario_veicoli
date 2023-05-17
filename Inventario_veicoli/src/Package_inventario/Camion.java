package Package_inventario;

public class Camion extends Veicolo {

	private int potata_carico;
	
	public Camion(String marca, String targa, String modello, int potata_carico) {
		super(marca, targa, modello);
		this.potata_carico = potata_carico;
	}

	public int getPotata_carico() {
		return potata_carico;
	}

	public void setPotata_carico(int potata_carico) {
		this.potata_carico = potata_carico;
	}
	
	public String toString() {
		return "Questo veicolo \u00E9 una camion con una "
				+ "portata di carico di "+this.potata_carico+
		" kg. "+super.toString();
	}
	
}
