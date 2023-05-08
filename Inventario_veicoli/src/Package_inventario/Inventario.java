package Package_inventario;

import java.util.ArrayList;

public class Inventario {
	
	ArrayList<Veicolo> listaVeicoli = new ArrayList<Veicolo>();
	
	public Inventario(ArrayList<Veicolo> listaVeicoli) {
		this.listaVeicoli = listaVeicoli;
	}

	public void aggiungiVeicolo(Veicolo v) {
		listaVeicoli.add(v);
	}
	
	public void rimuoviVeicolo(Veicolo v) {
		if(listaVeicoli.contains(v))
			listaVeicoli.remove(v);
		else
			System.out.println("Veicolo non presente nell'inventario!");
	}
	
	public void stampaLista() {
		for(Veicolo v : listaVeicoli)
			System.out.println(v.toString());
	}
	
	// trovare un veicolo data la targa --> ritorna la stringa 
	public String trovaVeicolo(String targa) {
		for(Veicolo v : listaVeicoli) {
			if(v.getTarga().toLowerCase().equals(targa.toLowerCase()))
				return "Il veicolo è presente.\n" + v.toString();
		}
		return "Veicolo non presente";
	}
}
