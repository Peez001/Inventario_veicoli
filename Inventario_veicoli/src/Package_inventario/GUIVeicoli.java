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

import java.awt.event.WindowListener;
import java.io.File;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUIVeicoli extends JFrame{

	private static final int TONNELLATE_MASSIME = 50;
	private static final int CILINDRATA_MASSIMA = 8000;
	private static final int NUMERO_PORTE_MASSIMO = 5;
	private static final long serialVersionUID = 1L;
	//private static final String FILE = "file.txt";
	
	private JFrame frameIniziale;
	private JPanel pannelloCards;	
	private JPanel pannelloIniziale;
	private JPanel pannelloInventario;
	private JButton bottoneAggiungiVeicolo;
	private JButton bottoneTrovaVeicolo;
	private JButton bottoneStampaVeicoli;
	private JButton bottoneRimuoviVeicolo;
	
	ImageIcon logo = new ImageIcon("veicolo.png"); //Creo il logo. Il file é all'interno del progetto
	Color coloreSfondo = new Color(0,94,131); //Imposto il colore dello sfondo	
	Font font = new Font("Helvetica", Font.BOLD, 30);
	
	public GUIVeicoli(Inventario inventario, File file) {
		
		schermataIniziale();	
		schermataInventario();	
		schermataAddVeicolo(inventario, file);
		schermataFind(inventario);
		schermataStamp(inventario);
		schermataRimuovi(inventario, file);
		
	}
	
	//schermata all'avvio del programma
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
		
		JButton bottoneInizio = new JButton("ACCEDI ALL'INVENTARIO"); //Creo il bottone inizio
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

	//schermata selezione azioni
	private void schermataInventario() {
		
		pannelloInventario = new JPanel();
		pannelloInventario.setLayout(new GridLayout(4,1,0,40));
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
		
		bottoneRimuoviVeicolo = new JButton("RIMUOVI VEICOLO");
		bottoneRimuoviVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneRimuoviVeicolo.setFont(font);
		bottoneRimuoviVeicolo.setFocusable(false);
		
		pannelloInventario.add(bottoneTrovaVeicolo);
		pannelloInventario.add(bottoneAggiungiVeicolo);
		pannelloInventario.add(bottoneStampaVeicoli);
		pannelloInventario.add(bottoneRimuoviVeicolo);
		
		pannelloCards.add(pannelloInventario);
		frameIniziale.add(pannelloCards);
		
	}

	//schermata inserimento dati e aggiunta veicolo
	private void schermataAddVeicolo(Inventario inventario, File file) {
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
		JLabel Cilindrata = new JLabel("INSERISCI CILINDRATA (CC)");
		Cilindrata.setFont(font);
		JTextField testoCilindrata = new JTextField();
		testoCilindrata.setFont(font);

		//Label e TextField per la portata massima
		JLabel portataMassima = new JLabel("INSERISCI PORTATA MASSIMA (t)");
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
	
		//Controllo se tutti i campi testo sono riempiti o meno 
		bottoneAggiungi.setEnabled(false);
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateButtonState();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateButtonState();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateButtonState();
			}

			private void updateButtonState() throws NullPointerException{
				try {
					boolean dati = (!testoTarga.getText().isEmpty() && !testoMarca.getText().isEmpty() && !testoModello.getText().isEmpty()); 
					boolean specifiche = (menuATendina.getSelectedItem().toString().equals("AUTOMOBILE") && 
							!testoNumeroPorte.getText().isEmpty()) || (menuATendina.getSelectedItem().toString().equals("MOTO") && 
									!testoCilindrata.getText().isEmpty() || (menuATendina.getSelectedItem().toString().equals("CAMION") && 
											!testoPortataMassima.getText().isEmpty()));
					bottoneAggiungi.setEnabled(dati && specifiche);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Prima di inserire i dati, seleziona un tipo di veicolo!", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
        };
        
        //aggiungo lo stesso documentListener a tutti i JTextField (possibile trovare un modo piu' carino per farlo :))
        testoMarca.getDocument().addDocumentListener(documentListener);
        testoModello.getDocument().addDocumentListener(documentListener);
        testoTarga.getDocument().addDocumentListener(documentListener);
        testoNumeroPorte.getDocument().addDocumentListener(documentListener);
        testoCilindrata.getDocument().addDocumentListener(documentListener);
        testoPortataMassima.getDocument().addDocumentListener(documentListener);
        
		//Aggiungo il veicolo all'inventario 		
        bottoneAggiungi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//controllo che la specifica sia un numero
				String cifre = "[0-9]+";
				boolean isNumero = (testoNumeroPorte.getText().matches(cifre) || 
						testoCilindrata.getText().matches(cifre) || 
						testoPortataMassima.getText().matches(cifre));
				
				
				//controllo che non sia troppo grande (bruttissimo)
				boolean numeroCorretto = false;
				try {
				
				if(!testoNumeroPorte.getText().isEmpty()) {
					int numeroInserito = Integer.parseInt(testoNumeroPorte.getText());
					if(numeroInserito <= NUMERO_PORTE_MASSIMO && numeroInserito > 0) {
						numeroCorretto = true;
					}else {
						numeroCorretto = false;
					}
				}
				
				if(!testoCilindrata.getText().isEmpty()) {
					int numeroInserito = Integer.parseInt(testoCilindrata.getText());
					if(numeroInserito <= CILINDRATA_MASSIMA && numeroInserito > 0) {
						numeroCorretto = true;
					}else {
						numeroCorretto = false;
					}
				}
				
				if(!testoPortataMassima.getText().isEmpty()) {
					int numeroInserito = Integer.parseInt(testoPortataMassima.getText());
					if(numeroInserito <= TONNELLATE_MASSIME && numeroInserito > 0) {
						numeroCorretto = true;
					}else {
						numeroCorretto = false;
					}
				}
				} catch (NumberFormatException ex) {
					//oops
				}
				
				//se ho inserito un numero valido, allora aggiungo il veicolo
				if(isNumero && numeroCorretto) {
					menuATendina.setSelectedIndex(menuATendina.getSelectedIndex());
					if(menuATendina.getSelectedItem().toString().equals("AUTOMOBILE")) {
						 if (!testoNumeroPorte.getText().isEmpty()) { //controllo che serve per il parseInt
							 Automobile auto = new Automobile(testoMarca.getText(), testoTarga.getText(), testoModello.getText(), Integer.parseInt(testoNumeroPorte.getText()));
							 inventario.aggiungiVeicolo(auto);
							 inventario.aggiungiVeicoloFile(auto, file);
							 JOptionPane.showMessageDialog(null, auto.toString() + "\n Automobile aggiunta correttamente all'inventario.", "Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
						 	}
					} else if(menuATendina.getSelectedItem().toString().equals("MOTO")) {
						if(!testoCilindrata.getText().isEmpty()){
							Moto moto = new Moto(testoMarca.getText(), testoTarga.getText(), testoModello.getText(), Integer.parseInt(testoCilindrata.getText()));
							inventario.aggiungiVeicolo(moto);
							inventario.aggiungiVeicoloFile(moto, file);
							JOptionPane.showMessageDialog(null, moto.toString() + "\n Moto aggiunta correttamente all'inventario.", "Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
							}
					} else {
						if(!testoPortataMassima.getText().isEmpty()){
							Camion camion = new Camion(testoMarca.getText(), testoTarga.getText(), testoModello.getText(), Integer.parseInt(testoPortataMassima.getText()));
							inventario.aggiungiVeicolo(camion);
							inventario.aggiungiVeicoloFile(camion, file);
							JOptionPane.showMessageDialog(null, camion.toString() + "\n Camion aggiunto correttamente all'inventario.", "Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					pulisciTextFields();
					} else {
					JOptionPane.showMessageDialog(null, "L'ultimo campo deve essere un numero! Inoltre, deve essere maggiore di zero e rispettare questi parametri:"
							+ " \n Numero porte massimo ---> 5 "
							+ " \n Cilindrata massima 	---> 8000 cc"
							+ " \n Portata massima 		---> 50 t"
							+ "", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}

			private void pulisciTextFields() {
				ArrayList<Component> componenti = new ArrayList<>();
				for(Component c : pannelloDatiVeicolo.getComponents()) {
					componenti.add(c);
				}
				for(Component c : pannelloSpecifiche.getComponents()) {
					componenti.add(c);
				}
				for(Component c : componenti) {
					if(c instanceof JTextField) {
						((JTextField) c).setText("");
					}
				}
			}
        });
		
		//Disattivo i bottoni del frame sottostante
		disable_enabled_buttons(frameAggiungiVeicolo, bottoneAggiungiVeicolo);
		
	}

	//schermata per trovare un veicolo
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
	
	//schermata di stampa dei veicoli
	private void schermataStamp(Inventario inventario) {
		
		// creo un frame per contenere l'esecuzione iniziale del comando Stamp
		JFrame frameStamp = new JFrame("Stampa la lista dei veicoli");
		
		//Creo un'area di testo per stampare l'elenco
		JTextArea areaStampa = new JTextArea();
		areaStampa.setFont(new Font("Helvetica", Font.PLAIN, 15));
		//JScrollPane scrollPane = new JScrollPane(listaVeicoli);
		
		frameStamp.setVisible(false);
		frameStamp.setSize(700, 700);
		frameStamp.getContentPane().setBackground(coloreSfondo);
		frameStamp.setLayout(new GridLayout(1, 1)); // Definisco il Layout manager del nuovo frame
		frameStamp.setIconImage(logo.getImage()); // Imposto l'icona del frame
		//listaVeicoli.setFont(font); // Non so se conviene, non si legge nulla dopo lol
		
		
				
		// richiamo la funzione per abilitare e disabilare i bottoni
		disable_enabled_buttons(frameStamp, bottoneStampaVeicoli);
				
		// LISTENERS				
		bottoneStampaVeicoli.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frameStamp.setVisible(true);
				areaStampa.setText("");
				List<String> myList = inventario.stampaLista();
				for (String s : myList) {
					areaStampa.append(s+"\n");
				}
				// aggiungo l'elemento al frameStamp
				frameStamp.add(areaStampa);
			}
		});
	}
	
	private void schermataRimuovi(Inventario inventario, File file) {
		
		// creo un frame per contenere l'esecuzione iniziale del comando Rimuovi
		JFrame pannelloRimuovi = new JFrame("Rimuovi un veicolo");
		pannelloRimuovi.setSize(350,160);
		pannelloRimuovi.getContentPane().setBackground(coloreSfondo);
		pannelloRimuovi.setLayout(new GridLayout(3,1));
		pannelloRimuovi.setIconImage(logo.getImage());
		
		// creo un area di testo per far comparire il messaggio di richiesta di inserimento		
		JTextField messagioRichiestaTarga = new JTextField("Inserisci targa:");
		messagioRichiestaTarga.setFont(font);
		messagioRichiestaTarga.setEditable(false); // faccio in modo che non posso essere editabile
		
		// creo un area di testo per far comparire l'are di inserimento
		JTextField inserisciTarga = new JTextField();
		inserisciTarga.setFont(font);
				
		// bottone per confermare l'inserimento
		JButton bottoneRimuovi = new JButton("Rimuovi");
		bottoneRimuovi.setFocusable(false);
	
		// aggiungo gli elementi al pannello Rimuovi
		pannelloRimuovi.add(messagioRichiestaTarga);
		pannelloRimuovi.add(inserisciTarga);
		pannelloRimuovi.add(bottoneRimuovi);
		
		// richiamo la funzione per abilitare e disabilare i bottoni
		disable_enabled_buttons(pannelloRimuovi, bottoneRimuoviVeicolo);
		
		// Listener per la ricerca al click del pulsante rimuovi
		bottoneRimuovi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
//				if(inventario.trovaVeicolo(inserisciTarga.getText()).equals("Veicolo non presente")) {
//					
//					System.out.println("Non � stato possibile rimuovere il veicolo in quanto non esiste");
//				}
//				else {
//					
//					System.out.println("Veicolo rimosso correttamente");
//					inventario.rimuoviVeicolo(inserisciTarga.getText()); // BIG PROBLEMA
//					inventario.rimuoviVeicoloFile(file);
//				}
				
				if(inventario.rimuoviVeicolo(inserisciTarga.getText())) {
					inventario.rimuoviVeicoloFile(file);
					JOptionPane.showMessageDialog(null,"Veicolo rimosso correttamente","Risposta",JOptionPane.YES_NO_CANCEL_OPTION);
				}
				else
					JOptionPane.showMessageDialog(null,"Non � stato possibile rimuovere il veicolo in quanto non esiste","Risposta",JOptionPane.YES_NO_CANCEL_OPTION);
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
				bottoneRimuoviVeicolo.setEnabled(false);
				}
		});
		
		// listener della chiusura pagina per la riabilitazione
		frameCorrente.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
		        // Riabilita i bottoni precedenti quando la finestra attuale viene chiusa
				bottoneAggiungiVeicolo.setEnabled(true);
				bottoneStampaVeicoli.setEnabled(true);
				bottoneTrovaVeicolo.setEnabled(true);
				bottoneRimuoviVeicolo.setEnabled(true);
		        }
		 });
	}

	public static void main(String[] args) {
		
		Inventario inventario = new Inventario();
		File file = new File("file.txt");
		inventario.inizio(file);
		GUIVeicoli gui = new GUIVeicoli(inventario, file);
		gui.frameIniziale.setVisible(true);
		
		
	}
}



