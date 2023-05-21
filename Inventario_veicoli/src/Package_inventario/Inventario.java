package Package_inventario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe che rappresenta l'inventario e contiene i metodi per lavorare sulla
 * lista di veicoli
 * 
 * @author ChurPeezZoli
 * @since 04/2023
 */
public class Inventario {

	private static final String essere = "\u00E9";
	// private final static String FILE = "file.txt";

	private ArrayList<Veicolo> listaVeicoli = new ArrayList<Veicolo>();

	public Inventario(ArrayList<Veicolo> listaVeicoli) {
		this.listaVeicoli = listaVeicoli;
	}

	public Inventario() {
		this.listaVeicoli = new ArrayList<Veicolo>();
	}

	/**
	 * Metodo che aggiunge all'inizio del programma i Veicoli dal file di testo
	 * all'ArrayList
	 * 
	 * @param file
	 */
	public void inizio(File file) {
		try {
			FileReader reader = new FileReader(file);
			Scanner in = new Scanner(reader);
			while (in.hasNextLine()) {
				// System.out.println(in.nextLine());
				String targa = in.next();
				String modello = in.next();
				String marca = in.next();
				int x = Integer.parseInt(in.next());
				String tipo = in.next();
				if (tipo.equals("Moto")) {
					Moto tempMoto = new Moto(marca, targa, modello, x);
					aggiungiVeicolo(tempMoto);
				} else if (tipo.equals("Automobile")) {
					Automobile tempAuto = new Automobile(marca, targa, modello, x);
					aggiungiVeicolo(tempAuto);
				} else if (tipo.equals("Camion")) {
					Camion tempCamion = new Camion(marca, targa, modello, x);
					aggiungiVeicolo(tempCamion);
				} else
					throw new IllegalArgumentException();
			}
			in.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File non esistente" + e);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Why dont you funzioni");
		} catch (Exception e) {
			// System.out.println(e.getMessage());
		}

		finally {
			System.out.println("Inizializzazione Terminata...");
		}
	}

	/**
	 * metodo che aggiunge il veicolo passato nell'ArrayList
	 * 
	 * @param v
	 */
	public void aggiungiVeicolo(Veicolo v) {
		listaVeicoli.add(v);
	}

	/**
	 * metodo che stampa le propriet� del veicolo passato nel file
	 * 
	 * @param v
	 * @param file
	 */
	public void aggiungiVeicoloFile(Veicolo v, File file) {
		try {
			FileWriter writer = new FileWriter(file, true); // da controllare
			PrintWriter out = new PrintWriter(writer);
			if (v.getClass() == Moto.class) {
				Moto m = (Moto) v;
				out.println(
						m.getTarga() + " " + m.getModello() + " " + m.getMarca() + " " + m.getCilindrata() + " Moto");
			} else if (v.getClass() == Automobile.class) {
				Automobile a = (Automobile) v;
				out.println(a.getTarga() + " " + a.getModello() + " " + a.getMarca() + " " + a.getNumero_porte()
						+ " Automobile");
			} else if (v.getClass() == Camion.class) {
				Camion c = (Camion) v;
				out.println(c.getTarga() + " " + c.getModello() + " " + c.getMarca() + " " + c.getPotata_carico()
						+ " Camion");
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Non esiste il file");
		}
	}

	/**
	 * metodo che rimuove il veicolo associato alla targa passata
	 * 
	 * @param targa
	 * @return boolean che rappresenta se l'operazione � andata a buon fine
	 */
	public boolean rimuoviVeicolo(String targa) {
		for (Veicolo v : listaVeicoli) {
			if (v.getTarga().toLowerCase().equals(targa.toLowerCase())) {
				listaVeicoli.remove(v);
				return true;
			}
		}
		return false;
	}

	/**
	 * metodo che ristampa l'ArrayList aggiornata senza l'elemento rimosso nel file
	 * 
	 * @param file
	 */
	public void rimuoviVeicoloFile(File file) {

		try {
			PrintWriter out = new PrintWriter(file);
			out.println("");
			out.close();
		} catch (IOException e) {
			System.out.println("Non esiste il file");
		}

		try {
			FileWriter writer = new FileWriter(file, true); // da controllare
			PrintWriter out = new PrintWriter(writer);
			for (Veicolo v : listaVeicoli)
				aggiungiVeicoloFile(v, file);
			out.close();
		} catch (IOException e) {
			System.out.println("Non esiste il file");
		}

	}

	/**
	 * Metodo che ritorna una List<String> con tutti i metodi toString dei veicoli
	 * nell'inventario
	 * 
	 * @return List<String> dei toString
	 */
	public List<String> stampaLista() {
		List<String> stringList = new ArrayList<String>();
		for (Veicolo v : listaVeicoli)
			stringList.add(v.toString());
		return stringList;
	}

	/**
	 * metodo per trovare un veicolo data la targa --> ritorna la stringa
	 * 
	 * @param targa
	 * @return String con il risultato
	 */
	public String trovaVeicolo(String targa) {
		for (Veicolo v : listaVeicoli) {
			if (v.getTarga().toLowerCase().equals(targa.toLowerCase()))
				return "Il veicolo " + essere + " presente.\n" + v.toString();
		}
		return "Veicolo non presente";
	}

}
