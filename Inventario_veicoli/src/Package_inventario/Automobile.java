package Package_inventario;

public class Automobile extends Veicolo {

	private int numero_porte;
	
	public Automobile(String marca, String targa, String modello, int numero_porte) {
		super(marca, targa, modello);
		this.numero_porte = numero_porte;
	}

	public int getNumero_porte() {
		return numero_porte;
	}

	public void setNumero_porte(int numero_porte) {
		this.numero_porte = numero_porte;
	}

	public String toString() {
		return "Automobile con "+ this.numero_porte + " porte. "+ super.toString();
	}
	
}
