package Package_inventario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Classe che compone la parte grafica del programma
 * 
 * @author ChurPeezZoli
 * @since 04/2023
 */
public class GUIVeicoli extends JFrame {

	private static final String essere = "\u00E9";
	private static final String FRECCIA_ELENCO = "\u2023";
	private static final int TONNELLATE_MASSIME = 50;
	private static final int CILINDRATA_MASSIMA = 8000;
	private static final int NUMERO_PORTE_MASSIMO = 5;
	private static final long serialVersionUID = 1L;
	// private static final String FILE = "file.txt";

	private JFrame frameIniziale;
	private JPanel pannelloCards;
	private JPanel pannelloIniziale;
	private JPanel pannelloInventario;
	private JButton bottoneAggiungiVeicolo;
	private JButton bottoneTrovaVeicolo;
	private JButton bottoneStampaVeicoli;
	private JButton bottoneRimuoviVeicolo;

	ImageIcon logo = new ImageIcon("veicolo.png"); // Creo il logo. Il file é all'interno del progetto
	Color coloreSfondo = new Color(105, 156, 180); // Imposto il colore dello sfondo
	Font font = new Font("Helvetica", Font.BOLD, 30); //Imposto il font dell'applicazione

	/**
	 * Costruttore dell'interfaccia utente, richiama al suo interno dei metodi di
	 * inizializzazione. E' implementata la sequenzialit� grafica
	 * 
	 * @param inventario
	 * @param file
	 */
	public GUIVeicoli(Inventario inventario, File file) {

		schermataIniziale();
		schermataInventario();
		schermataAddVeicolo(inventario, file);
		schermataFind(inventario);
		schermataStamp(inventario);
		schermataRimuovi(inventario, file);

	}

	/**
	 * Rende visibile la schermata introduttiva all'inerfaccia grafica Possiede un
	 * pulsante di inizio che, tramite un'interfaccia a CardLayout, switcha alla
	 * card successiva, ossia il menu' principale della GUI.
	 */
	private void schermataIniziale() {

		CardLayout cardLayout = new CardLayout();
		pannelloCards = new JPanel(cardLayout);// pannello per le prime due card sequenziali

		frameIniziale = new JFrame("Inventario Veicoli"); // Creo istanza del frame e metto il titolo
		frameIniziale.setSize(800, 800); // Imposto la dimensione iniziale del frame
		frameIniziale.getContentPane().setBackground(coloreSfondo); // Scelgo il colore dello sfondo
		frameIniziale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Alla pressione della X rossa chiudo
																		// l'applicazione
		frameIniziale.setIconImage(logo.getImage()); // Imposto l'icona del frame

		JLabel immagine = new JLabel(); //Creo la label che contiene l'immagine
		immagine.setIcon(logo);
		immagine.setHorizontalAlignment(JLabel.CENTER);
		immagine.setVerticalAlignment(JLabel.CENTER);

		JLabel etichetta = new JLabel("INVENTARIO VEICOLI"); // Creo l'etichetta
		etichetta.setFont(new Font("Helvetica", Font.BOLD, 40)); // Font un po' piu grosso
		etichetta.setHorizontalAlignment(JLabel.CENTER); // Imposto la posizione orizzontale del testo rispetto
															// all'immagine
		etichetta.setVerticalAlignment(JLabel.CENTER); // Imposto la posizione verticale del testo rispetto all'immagine

		JButton bottoneInizio = new JButton("ACCEDI ALL'INVENTARIO"); // Creo il bottone inizio
		bottoneInizio.setFocusable(false); // Tolgo la box attorno al testo del bottone
		bottoneInizio.setFont(new Font("Helvetica", Font.BOLD, 40)); // Font un po' piu grosso
		bottoneInizio.setAlignmentX(CENTER_ALIGNMENT); // Allineamento bottone
		bottoneInizio.setAlignmentY(CENTER_ALIGNMENT);
		bottoneInizio.setForeground(new Color(50, 50, 50));

		pannelloIniziale = new JPanel() { // pannello iniziale con colore gradiente

			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				int width = getWidth();
				int height = getHeight();
				Graphics2D g2d = (Graphics2D) g;
				Color color1 = coloreSfondo;
				Color color2 = new Color(80, 80, 148);
				;
				GradientPaint gradient = new GradientPaint(0, 0, color2, width, height, color1);
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, width, height);

			}

		}; // Creo pannello iniziale

		pannelloIniziale.setLayout(new BorderLayout(5, 1)); // Gestisco pannello iniziale con BorderLayout

		JPanel pannelloCentrale = new JPanel(); // Creo pannello centrale
		pannelloCentrale.setOpaque(false);
		pannelloCentrale.setLayout(new GridLayout(4, 1)); // Gestisco pannello centrale con GridLayout
		pannelloCentrale.setBorder(new EmptyBorder(120, 120, 120, 120)); // Padding

		// Aggiungo immagine etichetta e bottoneInizio al pannello centrale
		pannelloCentrale.add(immagine);
		pannelloCentrale.add(etichetta);
		pannelloCentrale.add(bottoneInizio);

		pannelloIniziale.add(pannelloCentrale);

		// Il pannello centrale é nel pannelloIniziale, che é nel pannelloCards, che a
		// sua volta é nel frameIniziale.
		pannelloCards.add(pannelloIniziale);

		// Quando viene premuto il bottone di inizio passo alla prossima schermata del CardLayout
		bottoneInizio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(pannelloCards);
			}
		});
	}

	/**
	 * Metodo per schermata selezione azioni: trova veicolo, aggiungi veicolo,
	 * stampa lista, rimuovi veicolo.
	 */
	private void schermataInventario() {

		pannelloInventario = new JPanel() { // pannello inventario con colore gradiente
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				int width = getWidth();
				int height = getHeight();
				Graphics2D g2d = (Graphics2D) g;
				Color color1 = coloreSfondo;
				Color color2 = new Color(80, 80, 148);
				GradientPaint gradient = new GradientPaint(0, 0, color2, width, height, color1);
				g2d.setPaint(gradient);
				g2d.fillRect(0, 0, width, height);
			}

		};
		
		//definisco layout e bordi del pannello dell'inventario
		pannelloInventario.setLayout(new GridLayout(4, 1, 0, 40));
		pannelloInventario.setBorder(new EmptyBorder(80, 80, 80, 80));
		
		//bottone trova veicolo
		//setto dimensione, font e not focusable per tutti
		bottoneTrovaVeicolo = new JButton("TROVA VEICOLO");
		bottoneTrovaVeicolo.setPreferredSize(new Dimension(200, 200));
		bottoneTrovaVeicolo.setFont(font);
		bottoneTrovaVeicolo.setFocusable(false);

		//bottone aggiungi veicolo
		bottoneAggiungiVeicolo = new JButton("AGGIUNGI NUOVO VEICOLO");
		bottoneAggiungiVeicolo.setPreferredSize(new Dimension(200, 200));
		bottoneAggiungiVeicolo.setFont(font);
		bottoneAggiungiVeicolo.setFocusable(false);
		
		//bottone stampa veicolo
		bottoneStampaVeicoli = new JButton("STAMPA VEICOLI");
		bottoneStampaVeicoli.setPreferredSize(new Dimension(200, 200));
		bottoneStampaVeicoli.setFont(font);
		bottoneStampaVeicoli.setFocusable(false);

		//bottone rimuovi veicolo
		bottoneRimuoviVeicolo = new JButton("RIMUOVI VEICOLO");
		bottoneRimuoviVeicolo.setPreferredSize(new Dimension(200, 200));
		bottoneRimuoviVeicolo.setFont(font);
		bottoneRimuoviVeicolo.setFocusable(false);

		//aggiungo i bottoni al pannello
		pannelloInventario.add(bottoneAggiungiVeicolo);
		pannelloInventario.add(bottoneStampaVeicoli);
		pannelloInventario.add(bottoneTrovaVeicolo);
		pannelloInventario.add(bottoneRimuoviVeicolo);
		
		//aggiungo al pannelloCards
		pannelloCards.add(pannelloInventario);
		frameIniziale.add(pannelloCards);

	}

	/**
	 * Metodo per schermata inserimento dati e aggiunta veicolo. Controllo sul
	 * riempimento di tutti i campi prima dell'abilitazione dell'aggiunta. Controllo
	 * sulla correttezza dei dati specifici del veicolo (ma non della targa).
	 * 
	 * @param inventario dei veicoli
	 * @param file       di testo
	 */
	private void schermataAddVeicolo(Inventario inventario, File file) {
		// Creo il frame che conterrà tutta la gestione di aggiunta veicolo
		JFrame frameAggiungiVeicolo = new JFrame("Aggiungi nuovo veicolo");
		frameAggiungiVeicolo.setVisible(false);
		frameAggiungiVeicolo.setSize(1000, 400); // Imposto la dimensione iniziale del frame
		frameAggiungiVeicolo.getContentPane().setBackground(coloreSfondo); // Scelgo il colore dello sfondo
		frameAggiungiVeicolo.setLayout(new BorderLayout()); // Definisco il Layout manager del frame esterno
		frameAggiungiVeicolo.setIconImage(logo.getImage()); // Imposto l'icona del frame

		// Contenitore più esterno che conterrà i dati generali e specifici
		JPanel contenitoreDati = new JPanel();
		contenitoreDati.setLayout(new GridLayout(2, 1));

		// Definisco il pannello che contiene i dati generali (targa, marca, modello)
		// dei veicoli
		JPanel pannelloDatiVeicolo = new JPanel(new GridLayout(4, 2));
		pannelloDatiVeicolo.setBackground(coloreSfondo);
		pannelloDatiVeicolo.setBorder(null);

		// Label per scelta del veicolo
		JLabel sceltaVeicolo = new JLabel("SCEGLI VEICOLO");
		sceltaVeicolo.setFont(font);

		// Menù a tendina
		String[] options = { "AUTOMOBILE", "MOTO", "CAMION" };
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);
		JComboBox<String> menuATendina = new JComboBox<>(model);
		menuATendina.setEditable(false);
		menuATendina.setMaximumRowCount(3);
		menuATendina.setSelectedIndex(-1);
		menuATendina.getEditor().setItem("");
		menuATendina.setFont(font);

		// Label e TextField per la targa
		JLabel Targa = new JLabel("INSERISCI TARGA");
		Targa.setFont(font);
		JTextField testoTarga = new JTextField();
		testoTarga.setFont(font);

		// Label e TextField per la marca
		JLabel Marca = new JLabel("INSERISCI MARCA");
		Marca.setFont(font);
		JTextField testoMarca = new JTextField();
		testoMarca.setFont(font);

		// Label e TextField per il modello
		JLabel Modello = new JLabel("INSERISCI MODELLO");
		Modello.setFont(font);
		JTextField testoModello = new JTextField();
		testoModello.setFont(font);

		// Aggiungo tutto al pannello dati generali
		pannelloDatiVeicolo.add(sceltaVeicolo);
		pannelloDatiVeicolo.add(menuATendina);
		pannelloDatiVeicolo.add(Targa);
		pannelloDatiVeicolo.add(testoTarga);
		pannelloDatiVeicolo.add(Marca);
		pannelloDatiVeicolo.add(testoMarca);
		pannelloDatiVeicolo.add(Modello);
		pannelloDatiVeicolo.add(testoModello);

		// Creo il pannello che conterrà le specifiche per i differenti veicoli
		// Automobile ---> Numero Porte
		// Moto ---> Cilindrata
		// Camion ---> Portata Massima
		JPanel pannelloSpecifiche = new JPanel();
		pannelloSpecifiche.setLayout(new GridLayout(1, 2));
		pannelloSpecifiche.setBackground(coloreSfondo);

		// Label e TextField per il numero porte
		JLabel numeroPorte = new JLabel("INSERISCI NUMERO PORTE");
		numeroPorte.setFont(font);
		JTextField testoNumeroPorte = new JTextField();
		testoNumeroPorte.setFont(font);

		// Label e TextField per la cilindrata
		JLabel Cilindrata = new JLabel("INSERISCI CILINDRATA (CC)");
		Cilindrata.setFont(font);
		JTextField testoCilindrata = new JTextField();
		testoCilindrata.setFont(font);

		// Label e TextField per la portata massima
		JLabel portataMassima = new JLabel("INSERISCI PORTATA MASSIMA (t)");
		portataMassima.setFont(font);
		JTextField testoPortataMassima = new JTextField();
		testoPortataMassima.setFont(font);

		// Creo un contenitore sottostante che avrà dentro il pannello delle
		// specifiche.
		// Lo divido in 4 righe così la dimensione della Label e TextField dei
		// parametri specifici sarà sempre uguale agli altri dati.
		JPanel contenitoreSpecifiche = new JPanel();
		contenitoreSpecifiche.setLayout(new GridLayout(4, 1));
		contenitoreSpecifiche.setBackground(coloreSfondo);

		// Creo un listener per il menù a tendina. Si aggiorna quando ci si
		// interagisce, e in base alla scelta aggiungo al pannelloSpecifiche.
		// Quando scelgo un nuovo elemento del menù tolgo quello che c'era prima con il
		// metodo rimuoviContenuto.
		menuATendina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rimuoviContenuto(pannelloSpecifiche);
				testoTarga.setText(""); // quando cambio la selezione del menù a tendina resetto i campi
				testoMarca.setText("");
				testoModello.setText("");
				int numeroScelto = menuATendina.getSelectedIndex();
				if (numeroScelto == 0) {
					pannelloSpecifiche.add(numeroPorte);
					pannelloSpecifiche.add(testoNumeroPorte);
				} else if (numeroScelto == 1) {
					pannelloSpecifiche.add(Cilindrata);
					pannelloSpecifiche.add(testoCilindrata);
				} else if (numeroScelto == 2) {
					pannelloSpecifiche.add(portataMassima);
					pannelloSpecifiche.add(testoPortataMassima);
				}
				pannelloSpecifiche.validate(); // "ricostruisco" il pannello
				pannelloSpecifiche.repaint();
			}

			private void rimuoviContenuto(JPanel pannello) { // rimuovo i componenti (JLabel e JTextField) che sono nel
																// pannello passato come argomento
				Component[] listaComponenti = pannello.getComponents();
				for (Component c : listaComponenti) {
					if (c instanceof JLabel || c instanceof JTextField) {
						pannello.remove(c);
					}
				}
			}
		});

		// aggiungo il pannelloSpecifiche al contenitoreSpecifiche. Sarà nella
		// posizione più in alto
		contenitoreSpecifiche.add(pannelloSpecifiche);

		// aggiungo al contenitore esterno tutti i dati. Per le specifiche generali ho
		// solo un pannello, gestito sempre allo stesso modo.
		// Per le specifiche particolari devo aggiungere il contenitoreSpecifiche.
		contenitoreDati.add(pannelloDatiVeicolo);
		contenitoreDati.add(contenitoreSpecifiche);

		// Creo il bottone che aggiunge il veicolo
		JButton bottoneAggiungi = new JButton("AGGIUNGI VEICOLO");
		bottoneAggiungi.setFocusable(false); // Tolgo la box attorno al testo del bottone
		bottoneAggiungi.setFont(font); // Imposto il font del bottone
		bottoneAggiungi.setPreferredSize(new Dimension(100, 100));

		// aggiungo tutto al frame, gestito con BorderLayout
		frameAggiungiVeicolo.add(contenitoreDati, BorderLayout.CENTER);
		frameAggiungiVeicolo.add(bottoneAggiungi, BorderLayout.SOUTH);

		// Controllo se tutti i campi testo sono riempiti o meno
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

			private void updateButtonState() throws NullPointerException {
				try {
					boolean dati = (!testoTarga.getText().isEmpty() && !testoMarca.getText().isEmpty()
							&& !testoModello.getText().isEmpty());
					boolean specifiche = (menuATendina.getSelectedItem().toString().equals("AUTOMOBILE")
							&& !testoNumeroPorte.getText().isEmpty())
							|| (menuATendina.getSelectedItem().toString().equals("MOTO")
									&& !testoCilindrata.getText().isEmpty()
									|| (menuATendina.getSelectedItem().toString().equals("CAMION")
											&& !testoPortataMassima.getText().isEmpty()));
					bottoneAggiungi.setEnabled(dati && specifiche);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Prima di inserire i dati, seleziona un tipo di veicolo!",
							"Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		// aggiungo lo stesso documentListener a tutti i JTextField (possibile trovare
		// un modo piu' carino per farlo :))
		testoMarca.getDocument().addDocumentListener(documentListener);
		testoModello.getDocument().addDocumentListener(documentListener);
		testoTarga.getDocument().addDocumentListener(documentListener);
		testoNumeroPorte.getDocument().addDocumentListener(documentListener);
		testoCilindrata.getDocument().addDocumentListener(documentListener);
		testoPortataMassima.getDocument().addDocumentListener(documentListener);

		// Aggiungo il veicolo all'inventario
		bottoneAggiungi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// controllo che la specifica sia un numero
				String cifre = "[0-9]+";
				boolean isNumero = (testoNumeroPorte.getText().matches(cifre)
						|| testoCilindrata.getText().matches(cifre) || testoPortataMassima.getText().matches(cifre));

				// controllo che non sia troppo grande (bruttissimo)
				boolean numeroCorretto = false;
				try {

					if (!testoNumeroPorte.getText().isEmpty()) {
						int numeroInserito = Integer.parseInt(testoNumeroPorte.getText());
						if (numeroInserito <= NUMERO_PORTE_MASSIMO && numeroInserito > 0) {
							numeroCorretto = true;
						} else {
							numeroCorretto = false;
						}
					}

					if (!testoCilindrata.getText().isEmpty()) {
						int numeroInserito = Integer.parseInt(testoCilindrata.getText());
						if (numeroInserito <= CILINDRATA_MASSIMA && numeroInserito > 0) {
							numeroCorretto = true;
						} else {
							numeroCorretto = false;
						}
					}

					if (!testoPortataMassima.getText().isEmpty()) {
						int numeroInserito = Integer.parseInt(testoPortataMassima.getText());
						if (numeroInserito <= TONNELLATE_MASSIME && numeroInserito > 0) {
							numeroCorretto = true;
						} else {
							numeroCorretto = false;
						}
					}
				} catch (NumberFormatException ex) { //eccezione nel caso in cui il campo delle specifiche non sia riempito da un numero.
					System.out.println("Formato numero non valido. " + ex.getMessage());
				}

				boolean targaValida = false;
				if (!inventario.trovaVeicolo(testoTarga.getText()).equals("Veicolo non presente")) {
					testoTarga.setText("");
					targaValida = false;
				} else {
					targaValida = true;
				}

				// se ho inserito un numero valido, allora aggiungo il veicolo
				if (isNumero && numeroCorretto && targaValida) {
					// menuATendina.setSelectedIndex(menuATendina.getSelectedIndex());
					if (menuATendina.getSelectedItem().toString().equals("AUTOMOBILE")) {
						if (!testoNumeroPorte.getText().isEmpty()) { // controllo che serve per il parseInt
							Automobile auto = new Automobile(spaziToUnderscore(testoMarca.getText()), testoTarga.getText(),
									spaziToUnderscore(testoModello.getText()), Integer.parseInt(testoNumeroPorte.getText()));
							inventario.aggiungiVeicolo(auto);
							inventario.aggiungiVeicoloFile(auto, file);
							JOptionPane.showMessageDialog(null,
									auto.toString() + "\nAutomobile aggiunta correttamente all'inventario.",
									"Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
						}
					} else if (menuATendina.getSelectedItem().toString().equals("MOTO")) {
						if (!testoCilindrata.getText().isEmpty()) {
							Moto moto = new Moto(spaziToUnderscore(testoMarca.getText()), testoTarga.getText(), spaziToUnderscore(testoModello.getText()),
									Integer.parseInt(testoCilindrata.getText()));
							inventario.aggiungiVeicolo(moto);
							inventario.aggiungiVeicoloFile(moto, file);
							JOptionPane.showMessageDialog(null,
									moto.toString() + "\nMoto aggiunta correttamente all'inventario.",
									"Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						if (!testoPortataMassima.getText().isEmpty()) {
							Camion camion = new Camion(spaziToUnderscore(testoMarca.getText()), testoTarga.getText(),
									spaziToUnderscore(testoModello.getText()), Integer.parseInt(testoPortataMassima.getText()));
							inventario.aggiungiVeicolo(camion);
							inventario.aggiungiVeicoloFile(camion, file);
							JOptionPane.showMessageDialog(null,
									camion.toString() + "\nCamion aggiunto correttamente all'inventario.",
									"Operazione terminata", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					pulisciTextFields(); // se l'aggiunta è andata a buon fine, tolgo tutto dai campi di testo
				} else {
					if (!isNumero) { // se non ho inserito un numero
						JOptionPane.showMessageDialog(null, "L'ultimo campo deve essere un numero!", "Errore",
								JOptionPane.ERROR_MESSAGE);
					} else if (!numeroCorretto) { // se il numero inserito non rispetta i limiti
						JOptionPane.showMessageDialog(null,
								"La specifica deve rispettare questi parametri:" + "\n Numero porte massimo ---> "
										+ NUMERO_PORTE_MASSIMO + "\n Cilindrata massima 	---> " + CILINDRATA_MASSIMA
										+ " cc" + "\n Portata massima 		---> " + TONNELLATE_MASSIME + " t",
								"Errore", JOptionPane.ERROR_MESSAGE);
					} else { // se la targa esiste già (\u00E0 = à)
						JOptionPane.showMessageDialog(null,
								"Esiste gi\u00E0 un veicolo con questa targa. Inserirne un'altra.", "Errore",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			private void pulisciTextFields() {
				ArrayList<Component> componenti = new ArrayList<>();
				for (Component c : pannelloDatiVeicolo.getComponents()) {
					componenti.add(c);
				}
				for (Component c : pannelloSpecifiche.getComponents()) {
					componenti.add(c);
				}
				for (Component c : componenti) {
					if (c instanceof JTextField) {
						((JTextField) c).setText("");
					}
				}
			}
		});

		// Disattivo i bottoni del frame sottostante
		abilitaDisabilitaPulsanti(frameAggiungiVeicolo, bottoneAggiungiVeicolo);
	}

	/**
	 * Metodo che implementa la funzionalit� grafica del metodo trovaVeicolo.
	 * prende come parametro l'inventario, in modo tale da agire sui suoi veicoli
	 * utilizzando i metodi offerti dalla sua classe.
	 * 
	 * @param inventario
	 */
	private void schermataFind(Inventario inventario) {

		// creo un frame per contenere l'esecuzione iniziale del comando Find
		JFrame frameTarga = new JFrame("Trova un veicolo");
		frameTarga.setSize(560, 260);
		frameTarga.setIconImage(logo.getImage()); // Imposto l'icona del frame

		JPanel pannelloTarga = new JPanel();
		pannelloTarga.setBackground(coloreSfondo);
		pannelloTarga.setLayout(new GridLayout(3, 1)); // Definisco il Layout manager del frame esterno
		pannelloTarga.setBorder(new EmptyBorder(30, 30, 30, 30)); // Padding

		// creo un area di testo per far comparire il messaggio di richiesta di
		// inserimento
		JTextField messaggioRichiestaTarga = new JTextField("Inserisci targa:");
		messaggioRichiestaTarga.setFont(font);
		messaggioRichiestaTarga.setEditable(false); // faccio in modo che non posso essere editabile
		messaggioRichiestaTarga.setOpaque(false);
		messaggioRichiestaTarga.setBorder(null);

		// creo un area di testo per far comparire l'are di inserimento
		JTextField inserisciTarga = new JTextField();
		inserisciTarga.setFont(font);

		// bottone per confermare l'inserimento
		JButton bottoneConferma = new JButton("Ok");
		bottoneConferma.setFocusable(false);
		bottoneConferma.setFont(font);

		// aggiungo gli elementi al pannelloTarga
		pannelloTarga.add(messaggioRichiestaTarga);
		pannelloTarga.add(inserisciTarga);
		pannelloTarga.add(bottoneConferma);

		frameTarga.add(pannelloTarga);

		// richiamo la funzione per abilitare e disabilare i bottoni
		abilitaDisabilitaPulsanti(frameTarga, bottoneTrovaVeicolo);

		// Listener per la pulizia dei JTextField alla chiusura del panel
		frameTarga.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				inserisciTarga.setText("");
			}
		});

		// Listener per la ricerca al click del pulsante ok
		bottoneConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, inventario.trovaVeicolo(inserisciTarga.getText()), "Risposta",
						JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});
	}

	/**
	 * Metodo che implementa la funzionalit� del metodoStamp.
	 * Nella schermata si pu� leggere la lista dei veicoli presenti nell'inventario.
	 * 
	 * @param inventario
	 */
	private void schermataStamp(Inventario inventario) {

		// creo un frame per contenere l'esecuzione iniziale del comando Stamp
		JFrame frameStamp = new JFrame("Lista dei veicoli");

		// Creo un'area di testo per stampare l'elenco
		JTextArea areaStampa = new JTextArea();
		areaStampa.setFont(new Font("Helvetica", Font.PLAIN, 20));
		areaStampa.setEditable(false);
		// JScrollPane scrollPane = new JScrollPane(listaVeicoli);

		frameStamp.setVisible(false);
		frameStamp.setSize(1000, 700);
		frameStamp.getContentPane().setBackground(coloreSfondo);
		frameStamp.setLayout(new GridLayout(1, 1)); // Definisco il Layout manager del nuovo frame
		frameStamp.setIconImage(logo.getImage()); // Imposto l'icona del frame
		// listaVeicoli.setFont(font); // Non so se conviene, non si legge nulla dopo
		// lol

		// richiamo la funzione per abilitare e disabilitare i bottoni
		abilitaDisabilitaPulsanti(frameStamp, bottoneStampaVeicoli);

		// LISTENERS
		bottoneStampaVeicoli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameStamp.setVisible(true);
				areaStampa.setText("");
				List<String> myList = inventario.stampaLista();
				for (String s : myList) {
					areaStampa.append(FRECCIA_ELENCO + s + "\n");
				}
			}
		});
		// aggiungo l'elemento al frameStamp
		frameStamp.add(areaStampa);
	}

	/**
	 * Metodo che implementa la funzionalit� del metodo rimuoviVeicolo.
	 * Nella schermata c'� la possibilit� di rimuovere un veicolo data la targa.
	 * Restituisce una risponsta dell'esito.
	 * 
	 * @param inventario
	 * @param file
	 */
	private void schermataRimuovi(Inventario inventario, File file) {

		// creo un frame per contenere l'esecuzione iniziale del comando Rimuovi
		JFrame frameRimuovi = new JFrame("Rimuovi un veicolo");
		frameRimuovi.setSize(560, 260);
		frameRimuovi.setIconImage(logo.getImage());

		JPanel pannelloRimuovi = new JPanel();
		pannelloRimuovi.setBackground(coloreSfondo);
		pannelloRimuovi.setLayout(new GridLayout(3, 1));
		pannelloRimuovi.setBorder(new EmptyBorder(30, 30, 30, 30)); // Padding

		// creo un area di testo per far comparire il messaggio di richiesta di
		// inserimento
		JTextField messaggioRichiestaTarga = new JTextField("Inserisci targa:");
		messaggioRichiestaTarga.setFont(font);
		messaggioRichiestaTarga.setEditable(false); // faccio in modo che non posso essere editabile
		messaggioRichiestaTarga.setOpaque(false);
		messaggioRichiestaTarga.setBorder(null);

		// creo un area di testo per far comparire l'are di inserimento
		JTextField inserisciTarga = new JTextField();
		inserisciTarga.setFont(font);

		// bottone per confermare l'inserimento
		JButton bottoneRimuovi = new JButton("Rimuovi");
		bottoneRimuovi.setFocusable(false);
		bottoneRimuovi.setFont(font);

		// aggiungo gli elementi al pannello Rimuovi
		pannelloRimuovi.add(messaggioRichiestaTarga);
		pannelloRimuovi.add(inserisciTarga);
		pannelloRimuovi.add(bottoneRimuovi);

		frameRimuovi.add(pannelloRimuovi);

		// richiamo la funzione per abilitare e disabilare i bottoni
		abilitaDisabilitaPulsanti(frameRimuovi, bottoneRimuoviVeicolo);

		// Listener per la pulizia dei JTextField alla chiusura del panel
		frameRimuovi.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				inserisciTarga.setText("");
			}
		});

		// Listener per la ricerca al click del pulsante rimuovi
		bottoneRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inventario.rimuoviVeicolo(inserisciTarga.getText())) {
					inventario.rimuoviVeicoloFile(file);
					JOptionPane.showMessageDialog(null,
							"Veicolo con targa \"" + inserisciTarga.getText() + "\" rimosso correttamente.", "Risposta",
							JOptionPane.YES_NO_CANCEL_OPTION);
				} else
					JOptionPane.showMessageDialog(null,
							"Non " + essere + " stato possibile rimuovere il veicolo in quanto non esiste", "Risposta",
							JOptionPane.YES_NO_CANCEL_OPTION);
			}
		});

	}

	/**
	 * Metodo per attivare il frameCorrente e disabilitare i bottoni della pagina
	 * principale e poi riattivarli al ritorno in essa.
	 * 
	 * @param frameCorrente
	 * @param bottoneSelezionato
	 */
	private void abilitaDisabilitaPulsanti(JFrame frameCorrente, JButton bottoneSelezionato) {
		// Listener per la disabilitazione di pagina precedente
		bottoneSelezionato.addActionListener(new ActionListener() {
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

	/**
	 * Metodo per consentire inserimenti con spazi (per non dare problemi in lettura del file)
	 * @param stringaDaCambiare
	 * @return
	 */
	private String spaziToUnderscore(String stringaDaCambiare) {
		String nuovaStringa = "";
		for(int i = 0; i < stringaDaCambiare.length(); i++) {
			if(stringaDaCambiare.charAt(i) == ' ')
				nuovaStringa += "_";
			else
				nuovaStringa += stringaDaCambiare.charAt(i);
		}
		return nuovaStringa;
	}
	
	/**
	 * Metodo main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Inventario inventario = new Inventario();
		File file = new File("file.txt");
		inventario.inizio(file);
		GUIVeicoli gui = new GUIVeicoli(inventario, file);
		gui.frameIniziale.setVisible(true);

	}
}
