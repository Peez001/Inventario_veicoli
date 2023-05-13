package Package_inventario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUIVeicoli extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame frameIniziale;
	private JPanel pannelloCards;	
	private JPanel pannelloIniziale;
	private JPanel pannelloInventario;
	private JButton bottoneAggiungiVeicolo;
	private JButton bottoneTrovaVeicolo;
	private JButton bottoneStampaVeicoli;
	
	ImageIcon logo = new ImageIcon("veicolo.png"); //Creo il logo. Il file é all'interno del progetto
	Color coloreSfondo = new Color(0,94,131); //Imposto il colore dello sfondo	
	Font font = new Font("Helvetica", Font.BOLD, 30);
	
	public GUIVeicoli(Inventario inventario) {
		
		init(inventario);
		
	}
	
	private void init(Inventario inventario) {
		
		schermataIniziale();	
		schermataInventario();	
		schermataAddVeicolo(inventario);
		schermataFind(inventario);
		schermataStamp(inventario);
		
	}

	private void schermataIniziale() {
				
		CardLayout cardLayout = new CardLayout();
		pannelloCards = new JPanel(cardLayout);// pannello per le prime due card sequenziali
		
		frameIniziale = new JFrame("Inventario Veicoli"); //Creo istanza del frame e metto il titolo
		//frameIniziale.setExtendedState(JFrame.MAXIMIZED_BOTH); //inizialmente l'interfaccia compare a schermo intero
		frameIniziale.setSize(800,800); //Imposto la dimensione iniziale del frame
		//frameIniziale.setVisible(true);	//Rendo il frame visibile
		frameIniziale.getContentPane().setBackground(coloreSfondo); //Scelgo il colore dello sfondo
		frameIniziale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Alla pressione della X rossa chiudo l'applicazione
		//frameIniziale.setLayout(cardLayout); //Definisco il Layout manager del frame esterno
		frameIniziale.setIconImage(logo.getImage()); //Imposto l'icona del frame
			
		JLabel immagine = new JLabel();
		immagine.setIcon(logo);
		immagine.setHorizontalAlignment(JLabel.CENTER); 
		immagine.setVerticalAlignment(JLabel.CENTER);
		
		JLabel etichetta = new JLabel("INVENTARIO VEICOLI"); //Creo l'etichetta
		etichetta.setFont(font); //Imposto il font dell'etichetta
		etichetta.setHorizontalAlignment(JLabel.CENTER); //Imposto la posizione orizzontale del testo rispetto all'immagine
		etichetta.setVerticalAlignment(JLabel.CENTER); //Imposto la posizione verticale del testo rispetto all'immagine
		
		JButton bottoneInizio = new JButton("ACCEDI ALL'INVENTARIO"); //Creo il bottone
		bottoneInizio.setFocusable(false); //Tolgo la box attorno al testo del bottone
		bottoneInizio.setFont(font); //Imposto il font del bottone
		bottoneInizio.setAlignmentX(CENTER_ALIGNMENT); //Allineamento bottone
		bottoneInizio.setAlignmentY(CENTER_ALIGNMENT);
		
		pannelloIniziale = new JPanel(); //Creo pannello iniziale
		pannelloIniziale.setLayout(new BorderLayout()); //Gestisco pannello iniziale con BorderLayout
		
		JPanel pannelloCentrale = new JPanel();	//Creo pannello centrale
		pannelloCentrale.setLayout(new GridLayout(3,1)); //Gestisco pannello centrale con GridLayout
		pannelloCentrale.setBackground(coloreSfondo);
		pannelloCentrale.setBorder(new EmptyBorder(120, 120, 120, 120)); //Padding
		
		//Creo 4 pannelli che faranno da riempispazi (vecchia soluzione, lascio in caso ci servisse non lo stiamo a riscrivere)
//		JPanel p1 = new JPanel(); 
//		JPanel p2 = new JPanel(); 
//		JPanel p3 = new JPanel(); 
//		JPanel p4 = new JPanel(); 
//		
//		p1.setBackground(coloreSfondo);
//		p2.setBackground(coloreSfondo);
//		p3.setBackground(coloreSfondo);
//		p4.setBackground(coloreSfondo);
		
//		p1.setPreferredSize(new Dimension(200,200));
//		p2.setPreferredSize(new Dimension(200,200));
//		p3.setPreferredSize(new Dimension(200,200));
//		p4.setPreferredSize(new Dimension(200,200));
//		
//		pannelloIniziale.add(p1, BorderLayout.SOUTH);
//		pannelloIniziale.add(p2, BorderLayout.NORTH);
//		pannelloIniziale.add(p3, BorderLayout.EAST);
//		pannelloIniziale.add(p4, BorderLayout.WEST);
		
		
		pannelloIniziale.add(pannelloCentrale);
		
		//Aggiungo immagine etichetta e bottoneInizio al pannello centrale
		pannelloCentrale.add(immagine);
		pannelloCentrale.add(etichetta);
		pannelloCentrale.add(bottoneInizio);
		
		//Il pannello centrale é nel pannelloIniziale, che é nel pannelloCards, che a sua volta é nel frameIniziale.
		pannelloCards.add(pannelloIniziale);
		
		// LISTENERS
		bottoneInizio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(pannelloCards);
			}
		});
		
	}

	private void schermataInventario() {
		
		pannelloInventario = new JPanel();
		pannelloInventario.setLayout(new GridLayout(3,1,0,40));
		pannelloInventario.setBackground(coloreSfondo);
		pannelloInventario.setBorder(new EmptyBorder(80,80,80,80));

		bottoneTrovaVeicolo = new JButton("TROVA VEICOLO");
		bottoneTrovaVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneTrovaVeicolo.setFont(font);
		bottoneTrovaVeicolo.setFocusable(false);
		
		bottoneAggiungiVeicolo = new JButton("AGGIUNGI NUOVO VEICOLO");
		bottoneAggiungiVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneAggiungiVeicolo.setFont(font);
		bottoneAggiungiVeicolo.setFocusable(false);
		
		bottoneStampaVeicoli = new JButton("STAMPA VEICOLI");
		bottoneStampaVeicoli.setPreferredSize(new Dimension(200,200));
		bottoneStampaVeicoli.setFont(font);
		bottoneStampaVeicoli.setFocusable(false);
		
		pannelloInventario.add(bottoneTrovaVeicolo);
		pannelloInventario.add(bottoneAggiungiVeicolo);
		pannelloInventario.add(bottoneStampaVeicoli);
		
		pannelloCards.add(pannelloInventario);
		frameIniziale.add(pannelloCards);
		
	}

	private void schermataAddVeicolo(Inventario inventario) {
		//Creo il frame che conterrà tutta la gestione di aggiunta veicolo
		JFrame frameAggiungiVeicolo = new JFrame("Aggiungi nuovo veicolo");
		frameAggiungiVeicolo.setVisible(false);
		frameAggiungiVeicolo.setSize(1000,400); //Imposto la dimensione iniziale del frame
		frameAggiungiVeicolo.getContentPane().setBackground(coloreSfondo); //Scelgo il colore dello sfondo
		frameAggiungiVeicolo.setLayout(new BorderLayout()); //Definisco il Layout manager del frame esterno
		frameAggiungiVeicolo.setIconImage(logo.getImage()); //Imposto l'icona del frame
		
		//Contenitore più esterno che conterrà i dati generali e specifici
		JPanel contenitoreDati = new JPanel();
		contenitoreDati.setLayout(new GridLayout(2,1));
	
		//Definisco il pannello che contiene i dati generali (targa, marca, modello) dei veicoli
		JPanel pannelloDatiVeicolo = new JPanel(new GridLayout(4,2));
		
		//Label per scelta del veicolo
		JLabel sceltaVeicolo = new JLabel("SCEGLI VEICOLO");
		sceltaVeicolo.setFont(font);
		
		//Menù a tendina
		String[] options = {"AUTOMOBILE", "MOTO", "CAMION"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);
        JComboBox<String> menuATendina = new JComboBox<>(model);
        menuATendina.setEditable(false);
        menuATendina.setMaximumRowCount(3);
        menuATendina.setSelectedIndex(-1);
        menuATendina.getEditor().setItem("");
        menuATendina.setFont(font);
        
        //Label e TextField per la targa
		JLabel Targa = new JLabel("INSERISCI TARGA");
		Targa.setFont(font);
		JTextField testoTarga = new JTextField();		
		testoTarga.setFont(font);
		
        //Label e TextField per la marca
		JLabel Marca = new JLabel("INSERISCI MARCA");
		Marca.setFont(font);
		JTextField testoMarca = new JTextField();
		testoMarca.setFont(font);
		
        //Label e TextField per il modello
		JLabel Modello = new JLabel("INSERISCI MODELLO");
		Modello.setFont(font);
		JTextField testoModello = new JTextField();
		testoModello.setFont(font);
		
		//Aggiungo tutto al pannello dati generali
		pannelloDatiVeicolo.add(sceltaVeicolo);
		pannelloDatiVeicolo.add(menuATendina);
		pannelloDatiVeicolo.add(Targa);
		pannelloDatiVeicolo.add(testoTarga);
		pannelloDatiVeicolo.add(Marca);
		pannelloDatiVeicolo.add(testoMarca);
		pannelloDatiVeicolo.add(Modello);
		pannelloDatiVeicolo.add(testoModello);
		
		//Creo il pannello che conterrà le specifiche per i differenti veicoli
		//Automobile ---> Numero Porte
		//Moto 		 ---> Cilindrata
		//Camion	 ---> Portata Massima		
		JPanel pannelloSpecifiche = new JPanel();
		pannelloSpecifiche.setLayout(new GridLayout(1,2));
		
		//Label e TextField per il numero porte
		JLabel numeroPorte = new JLabel("INSERISCI NUMERO PORTE");
		numeroPorte.setFont(font);
		JTextField  testoNumeroPorte = new JTextField();
		testoNumeroPorte.setFont(font);

		//Label e TextField per la cilindrata
		JLabel Cilindrata = new JLabel("INSERISCI CILINDRATA");
		Cilindrata.setFont(font);
		JTextField testoCilindrata = new JTextField();
		testoCilindrata.setFont(font);

		//Label e TextField per la portata massima
		JLabel portataMassima = new JLabel("INSERISCI PORTATA MASSIMA");
		portataMassima.setFont(font);
		JTextField  testoPortataMassima= new JTextField();
		testoPortataMassima.setFont(font);
		
		//Creo un contenitore sottostante che avrà dentro il pannello delle specifiche. 
		//Lo divido in 4 righe così la dimensione della Label e TextField dei parametri specifici sarà sempre uguale agli altri dati.
		JPanel contenitoreSpecifiche = new JPanel();
		contenitoreSpecifiche.setLayout(new GridLayout(4,1));
		
		//Creo un listener per il menù a tendina. Si aggiorna quando ci si interagisce, e in base alla scelta aggiungo al pannelloSpecifiche.
		//Quando scelgo un nuovo elemento del menù tolgo quello che c'era prima con il metodo rimuoviContenuto.
		menuATendina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
				rimuoviContenuto(pannelloSpecifiche);
				int numeroScelto = menuATendina.getSelectedIndex();
				if(numeroScelto == 0) {
					pannelloSpecifiche.add(numeroPorte);
					pannelloSpecifiche.add(testoNumeroPorte);
				}   else if (numeroScelto == 1) {
					pannelloSpecifiche.add(Cilindrata);
					pannelloSpecifiche.add(testoCilindrata);
				}	else if (numeroScelto == 2) {
					pannelloSpecifiche.add(portataMassima);
					pannelloSpecifiche.add(testoPortataMassima);
				}
				pannelloSpecifiche.validate();
				pannelloSpecifiche.repaint();
			}

			private void rimuoviContenuto(JPanel pannello) {
				Component[] listaComponenti = pannello.getComponents();
				for(Component c : listaComponenti){
				    if(c instanceof JLabel || c instanceof JTextField){
				        pannello.remove(c);
				    }
				}
			}
			});
		
		//aggiungo il pannelloSpecifiche al contenitoreSpecifiche. Sarà nella posizione più in alto
		contenitoreSpecifiche.add(pannelloSpecifiche);
	
		//aggiungo al contenitore esterno tutti i dati. Per le specifiche generali ho solo un pannello, gestito sempre allo stesso modo.
		//Per le specifiche particolari devo aggiungere il contenitoreSpecifiche.
		contenitoreDati.add(pannelloDatiVeicolo);
		contenitoreDati.add(contenitoreSpecifiche);
		
		//Creo il bottone che aggiunge il veicolo
		JButton bottoneAggiungi = new JButton ("AGGIUNGI VEICOLO");
		bottoneAggiungi.setFocusable(false); //Tolgo la box attorno al testo del bottone
		bottoneAggiungi.setFont(font); //Imposto il font del bottone
		bottoneAggiungi.setPreferredSize(new Dimension(100,100));
		
		//aggiungo tutto al frame, gestito con BorderLayout
		frameAggiungiVeicolo.add(contenitoreDati, BorderLayout.CENTER);
		frameAggiungiVeicolo.add(bottoneAggiungi, BorderLayout.SOUTH);
	
		//Controllo se tutti i campi testo sono riempiti o meno (non funziona ancora)
		boolean campiRiempiti = false;
		while(frameAggiungiVeicolo.isShowing()) {
			ArrayList<JTextField> listaCampiTesto = new ArrayList<>();
			Component[] listaComponenti = contenitoreDati.getComponents();
			
			for(Component c : listaComponenti){
				if(c instanceof JTextField){
					listaCampiTesto.add((JTextField) c);
				}	
			}
			
			for(JTextField campo : listaCampiTesto) {
			  if (campo.getText().trim().equals("")) {
			    	      campiRiempiti = false;
			    } else campiRiempiti = true;
			}
		}
		bottoneAggiungi.setEnabled(campiRiempiti);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Disattivo i bottoni del frame sottostante
		disable_enabled_buttons(frameAggiungiVeicolo, bottoneAggiungiVeicolo);
		
	}

	private void schermataFind(Inventario inventario) {
		
		// creo un frame per contenere l'esecuzione iniziale del comando Find		
		JFrame pannelloTarga = new JFrame("Trova un veicolo");
		pannelloTarga.setSize(350, 160);
		pannelloTarga.getContentPane().setBackground(coloreSfondo);
		pannelloTarga.setLayout(new GridLayout(3, 1)); //Definisco il Layout manager del frame esterno
		pannelloTarga.setIconImage(logo.getImage()); //Imposto l'icona del frame
		
		// creo un area di testo per far comparire il messaggio di richiesta di inserimento
		JTextField messagioRichiestaTarga = new JTextField("Inserisci targa:");
		messagioRichiestaTarga.setFont(font);
		messagioRichiestaTarga.setEditable(false); // faccio in modo che non posso essere editabile
        
		// creo un area di testo per far comparire l'are di inserimento
		JTextField inserisciTarga = new JTextField();
		inserisciTarga.setFont(font);
		
		// bottone per confermare l'inserimento
		JButton bottoneConferma = new JButton("Ok");
		bottoneConferma.setFocusable(false);

		// aggiungo gli elementi al pannelloTarga
		pannelloTarga.add(messagioRichiestaTarga);
		pannelloTarga.add(inserisciTarga);
		pannelloTarga.add(bottoneConferma);
		
		// richiamo la funzione per abilitare e disabilare i bottoni
		disable_enabled_buttons(pannelloTarga, bottoneTrovaVeicolo);
		
		// Listener per la ricerca al click del pulsante ok
		bottoneConferma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, inventario.trovaVeicolo(inserisciTarga.getText()), "Risposta", JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
	}
	
	private void schermataStamp(Inventario inventario) {
		
		// creo un frame per contenere l'esecuzione iniziale del comando Stamp
		JFrame frameStamp = new JFrame("Stampa la lista dei veicoli");
		
		//Creo un'area di testo per stampare l'elenco
		JTextArea listaVeicoli = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(listaVeicoli);
		
		frameStamp.setVisible(false);
		frameStamp.setSize(700, 700);
		frameStamp.getContentPane().setBackground(coloreSfondo);
		frameStamp.setLayout(new GridLayout(1, 1)); //Definisco il Layout manager del nuovo frame
		frameStamp.setIconImage(logo.getImage()); //Imposto l'icona del frame

		List<String> myList = inventario.stampaLista();
		
		for (String e : myList) {
			listaVeicoli.append(e+"\n");
		}
		// aggiungo l'elemento al frameStamp
		frameStamp.add(listaVeicoli);
				
		// richiamo la funzione per abilitare e disabilare i bottoni
		disable_enabled_buttons(frameStamp, bottoneStampaVeicoli);
				
		// LISTENERS				
		bottoneStampaVeicoli.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frameStamp.setVisible(true);
			}
		});
	}
	
	// Metodo per disabilitare i bottoni della pagina principale e poi riattivarli al ritorno in essa
	private void disable_enabled_buttons(JFrame frameCorrente, JButton bottoneSelezionato) {
		// Listener per la disabilitazione di pagina precedente
		bottoneSelezionato.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frameCorrente.setVisible(true);
				// disabilito i bottoni una volta cliccato il bottone precedente
				bottoneAggiungiVeicolo.setEnabled(false);
				bottoneStampaVeicoli.setEnabled(false);
				bottoneTrovaVeicolo.setEnabled(false);
				}
		});
		
		// listener della chiusura pagina per la riabilitazione
		frameCorrente.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        // Riabilita i bottoni precedenti quando la finestra attuale viene chiusa
				bottoneAggiungiVeicolo.setEnabled(true);
				bottoneStampaVeicoli.setEnabled(true);
				bottoneTrovaVeicolo.setEnabled(true);
		        }
		 });
	}
	
	public static void main(String[] args) {
		
		ArrayList<Veicolo> listaVeicoli = new ArrayList<>();
		Moto moto1 = new Moto("Skoda", "ES652JJ", "Hybrid_Sium", 13456);
		Moto moto2 = new Moto("vecio", "XD666LO", "motorola", 6);
		Inventario inventario = new Inventario(listaVeicoli);
		inventario.aggiungiVeicolo(moto1);
		inventario.aggiungiVeicolo(moto2);
		Inventario inventario_prova = new Inventario(listaVeicoli);
		//inventario.inizio();
		GUIVeicoli gui = new GUIVeicoli(inventario_prova);
		gui.frameIniziale.setVisible(true);
		
	}
}



