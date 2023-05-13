package Package_inventario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
	
	ArrayList<Veicolo> listaVeicoli = new ArrayList<Veicolo>();
	
	public Inventario(ArrayList<Veicolo> listaVeicoli) {
		this.listaVeicoli = listaVeicoli;
	}

	// metodo che aggiunge in automatico all'inizio del programma le cose dal file all'arrayList
	public void inizio() {
		try {
			FileReader reader = new FileReader("file.txt");
			Scanner in = new Scanner(reader);
			while(in.hasNextLine()) {
				//System.out.println(in.nextLine());
				String targa = in.next();
				String modello = in.next();
				String marca = in.next();
				int x = Integer.parseInt(in.next());
				String tipo = in.next();
				if(tipo == "Moto"){
					Moto tempMoto = new Moto(marca, targa, modello, x);
					aggiungiVeicolo(tempMoto, false);
				}
				if(tipo == "Automobile"){
					Automobile tempAuto = new Automobile(marca, targa, modello, x);
					aggiungiVeicolo(tempAuto, false);
				}
				if(tipo == "Camion"){
					Camion tempCamion = new Camion(marca, targa, modello, x);
					aggiungiVeicolo(tempCamion, false);
				}
			}
			reader.close();
			in.close();
		}catch (IOException e) {
			System.out.println("Non esiste un inventario esistente"+e);
		}
	}
	
	// lascio la scelta se salvarlo anche su file (true lo salva anche sul file)
	public void aggiungiVeicolo(Veicolo v, boolean file) {
		listaVeicoli.add(v);
		
		if(file) {
			// quando aggiungo un oggetto all'arrayList lo stampo anche sul file
			try {
				FileWriter writer = new FileWriter("file.txt",false);
				PrintWriter out = new PrintWriter(writer);
				if(v.getClass() == Moto.class) {
					Moto m = (Moto)v;
					out.println(m.getTarga()+" "+m.getModello()+" "+m.getMarca()+" "+m.getCilindrata()+" Moto");
				}
				if(v.getClass() == Automobile.class) {
					Automobile a = (Automobile)v;
					out.println(a.getTarga()+" "+a.getModello()+" "+a.getMarca()+" "+a.getNumero_porte()+" Automobile");
				}
				if(v.getClass() == Camion.class) {
					Camion c = (Camion)v;
					out.println(c.getTarga()+" "+c.getModello()+" "+c.getMarca()+" "+c.getPotata_carico()+" Camion");
				}
				out.close();
			}catch(IOException e) {
				System.out.println("Non esiste il file");
			}
		}
	}
	
	public void rimuoviVeicolo(Veicolo v) {
		if(listaVeicoli.contains(v))
			listaVeicoli.remove(v);
		else
			System.out.println("Veicolo non presente nell'inventario!");
	}
	
	public List<String> stampaLista() {
		List<String> stringList = new ArrayList<String>();
		for(Veicolo v : listaVeicoli)
			stringList.add(v.toString());
		return stringList;
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
