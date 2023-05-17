package Package_inventario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
	
	//private final static String FILE = "file.txt";
	
	ArrayList<Veicolo> listaVeicoli = new ArrayList<Veicolo>();
	
	public Inventario(ArrayList<Veicolo> listaVeicoli) {
		this.listaVeicoli = listaVeicoli;
	}

	// Aggiunge in automatico all'inizio del programma i Veicoli dal file di testo all'arrayList
	public void inizio(String file) {
		try {
			FileReader reader = new FileReader(file);
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
					aggiungiVeicolo(tempMoto);
				}
				if(tipo == "Automobile"){
					Automobile tempAuto = new Automobile(marca, targa, modello, x);
					aggiungiVeicolo(tempAuto);
				}
				if(tipo == "Camion"){
					Camion tempCamion = new Camion(marca, targa, modello, x);
					aggiungiVeicolo(tempCamion);
				}
			}
			reader.close();
			in.close();
		}catch (IOException e) {
			System.out.println("Non esiste un inventario esistente"+e);
		}	
	}
	
	// Aggiunge il veicolo nell'arrayList
	public void aggiungiVeicolo(Veicolo v) {
		listaVeicoli.add(v);
	}
	
	// Aggiunge il veicolo nel file di testo
	public void aggiungiVeicoloFile(Veicolo v, String file) {
		try {
			FileWriter writer = new FileWriter(file,false); // da controllare
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
	
	// Rimuove il veicolo dall'arrayList
	public void rimuoviVeicolo(Veicolo v) {
		if(listaVeicoli.contains(v))
			listaVeicoli.remove(v);
		else
			System.out.println("Veicolo non presente nell'inventario!");
	}
	
	// Rimuove il veicolo dal file di testo
	public void rimuoviVeicoloFile(Veicolo v, String file) {
		
	}
	
	// Ritorna una Lista di String con tutti i metodi toString dei veicoli nell'inventario
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
				return "Il veicolo \u00E9 presente.\n" + v.toString();
		}
		return "Veicolo non presente";
	}
}
