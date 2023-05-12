package Package_inventario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventario {
	
	ArrayList<Veicolo> listaVeicoli = new ArrayList<Veicolo>();
	
	public Inventario(ArrayList<Veicolo> listaVeicoli) {
		this.listaVeicoli = listaVeicoli;
	}

	/*
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
				Moto tempMoto = new Moto(marca, targa, modello, x);
				aggiungiVeicolo(tempMoto);
			}
			reader.close();
			in.close();
		}catch (IOException e) {
			System.out.println("Non esiste un inventario esistente");
		}
	}
	*/
	
	public void aggiungiVeicolo(Veicolo v) {
		listaVeicoli.add(v);
		
		try {
			FileWriter writer = new FileWriter("file.txt",false);
			PrintWriter out = new PrintWriter(writer);
			out.println(v.getTarga()+" "+v.getModello()+" "+v.getMarca());
			out.close();
		}catch(IOException e) {
			System.out.println("Non esiste il file");
		}
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
