package Package_inventario;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Veicolo> listaVeicoli = new ArrayList<>();
		Moto moto1 = new Moto("Skoda", "ES652JJ", "Hybrid Sium", 13456);
		Inventario inventario = new Inventario(listaVeicoli);
		inventario.aggiungiVeicolo(moto1);
		inventario.stampaLista();
	}

}
