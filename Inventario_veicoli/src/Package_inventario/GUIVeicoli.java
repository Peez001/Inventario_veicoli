package Package_inventario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	Font font = new Font("Helvetica", Font.BOLD, 25);
	
	public GUIVeicoli() {
		
		init();
		
	}
	
	private void init() {
		
		schermataIniziale();	
		schermataInventario();	
		schermataAddVeicolo();
		schermataFind();
		
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
		bottoneInizio.setAlignmentX(CENTER_ALIGNMENT);
		bottoneInizio.setAlignmentY(CENTER_ALIGNMENT);
		
		pannelloIniziale = new JPanel();
		pannelloIniziale.setLayout(new BorderLayout());
		
		JPanel pannelloCentrale = new JPanel();
		pannelloCentrale.setLayout(new GridLayout(3,1));
		
		JPanel p1 = new JPanel(); 
		JPanel p2 = new JPanel(); 
		JPanel p3 = new JPanel(); 
		JPanel p4 = new JPanel(); 
		
		p1.setBackground(coloreSfondo);
		p2.setBackground(coloreSfondo);
		p3.setBackground(coloreSfondo);
		p4.setBackground(coloreSfondo);
		pannelloCentrale.setBackground(coloreSfondo);
		
		p1.setPreferredSize(new Dimension(200,200));
		p2.setPreferredSize(new Dimension(200,200));
		p3.setPreferredSize(new Dimension(200,200));
		p4.setPreferredSize(new Dimension(200,200));
		
		pannelloIniziale.add(p1, BorderLayout.SOUTH);
		pannelloIniziale.add(p2, BorderLayout.NORTH);
		pannelloIniziale.add(p3, BorderLayout.EAST);
		pannelloIniziale.add(p4, BorderLayout.WEST);
		pannelloIniziale.add(pannelloCentrale);
		
		pannelloCentrale.add(immagine);
		pannelloCentrale.add(etichetta);
		pannelloCentrale.add(bottoneInizio);
		
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
		pannelloInventario.setLayout(new GridLayout());
				
		bottoneAggiungiVeicolo = new JButton("AGGIUNGI NUOVO VEICOLO");
		bottoneAggiungiVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneAggiungiVeicolo.setFont(font);
		bottoneAggiungiVeicolo.setFocusable(false);
		
		bottoneTrovaVeicolo = new JButton("TROVA VEICOLO");
		bottoneTrovaVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneTrovaVeicolo.setFont(font);
		bottoneTrovaVeicolo.setFocusable(false);
		
		bottoneStampaVeicoli = new JButton("STAMPA VEICOLI");
		bottoneStampaVeicoli.setPreferredSize(new Dimension(200,200));
		bottoneStampaVeicoli.setFont(font);
		bottoneStampaVeicoli.setFocusable(false);
		
		pannelloInventario.setLayout(new GridLayout(3,1));
		pannelloInventario.add(bottoneTrovaVeicolo);
		pannelloInventario.add(bottoneAggiungiVeicolo);
		pannelloInventario.add(bottoneStampaVeicoli);
		
		pannelloCards.add(pannelloInventario);
		frameIniziale.add(pannelloCards);
		
	}

	private void schermataAddVeicolo() {
		JFrame frameAggiungiVeicolo = new JFrame("Aggiungi nuovo veicolo");
		frameAggiungiVeicolo.setVisible(false);
		frameAggiungiVeicolo.setSize(600,400); //Imposto la dimensione iniziale del frame
		frameAggiungiVeicolo.getContentPane().setBackground(coloreSfondo); //Scelgo il colore dello sfondo
		//frameAggiungiVeicolo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Alla pressione della X rossa chiudo l'applicazione
		frameAggiungiVeicolo.setLayout(new BorderLayout()); //Definisco il Layout manager del frame esterno
		frameAggiungiVeicolo.setIconImage(logo.getImage()); //Imposto l'icona del frame

		JPanel pannelloDatiVeicolo = new JPanel(new GridLayout(6,2));
		
		JLabel sceltaVeicolo = new JLabel("SCEGLI VEICOLO");
		sceltaVeicolo.setFont(font);
		
		String[] options = {"AUTOMOBILE", "MOTO", "CAMION"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(options);
        JComboBox<String> menuATendina = new JComboBox<>(model);
        menuATendina.setEditable(false);
        menuATendina.setMaximumRowCount(3);
        menuATendina.setSelectedIndex(-1);
        menuATendina.getEditor().setItem("");

		JLabel Targa = new JLabel("INSERISCI TARGA");
		Targa.setFont(font);
		JTextField testoTarga = new JTextField();		
		
		JLabel Marca = new JLabel("INSERISCI MARCA");
		Marca.setFont(font);
		JTextField testoMarca = new JTextField();
		
		JLabel Modello = new JLabel("INSERISCI MODELLO");
		Modello.setFont(font);
		JTextField testoModello = new JTextField();
		
		pannelloDatiVeicolo.add(sceltaVeicolo);
		pannelloDatiVeicolo.add(menuATendina);
		pannelloDatiVeicolo.add(Targa);
		pannelloDatiVeicolo.add(testoTarga);
		pannelloDatiVeicolo.add(Marca);
		pannelloDatiVeicolo.add(testoMarca);
		pannelloDatiVeicolo.add(Modello);
		pannelloDatiVeicolo.add(testoModello);
		
		JLabel numeroPorte = new JLabel("INSERISCI NUMERO PORTE");
		numeroPorte.setFont(font);
		JTextField  testoNumeroPorte = new JTextField();
		
		JLabel Cilindrata = new JLabel("INSERISCI CILINDRATA");
		Cilindrata.setFont(font);
		JTextField testoCilindrata = new JTextField();
		
		JLabel portataMassima = new JLabel("INSERISCI PORTATA MASSIMA");
		portataMassima.setFont(font);
		JTextField  testoPortataMassima= new JTextField();
		
			if(menuATendina.getSelectedIndex() == 0) {
			pannelloDatiVeicolo.add(numeroPorte);
			pannelloDatiVeicolo.add(testoNumeroPorte);
			System.out.println("Ciao");}
		if (menuATendina.getSelectedIndex() == 1) {
			pannelloDatiVeicolo.add(Cilindrata);
			pannelloDatiVeicolo.add(testoCilindrata);}
		if (menuATendina.getSelectedIndex() == 2) {
			pannelloDatiVeicolo.add(portataMassima);
			pannelloDatiVeicolo.add(testoPortataMassima);
		}
		
		System.out.println(menuATendina.getSelectedIndex());
		
		frameAggiungiVeicolo.add(pannelloDatiVeicolo, BorderLayout.CENTER);
		
		disable_enabled_buttons(frameAggiungiVeicolo, bottoneAggiungiVeicolo);
		
	}

	private void schermataFind() {
		
		JFrame pannelloTarga = new JFrame("Trova un veicolo");
		pannelloTarga.setSize(350, 160);
		pannelloTarga.getContentPane().setBackground(coloreSfondo);
		pannelloTarga.setLayout(new GridLayout(3, 1)); //Definisco il Layout manager del frame esterno
		pannelloTarga.setIconImage(logo.getImage()); //Imposto l'icona del frame
		
		// creo un area di testo per far comparire la richiesta di inserimento
		JTextField messagioRichiestaTarga = new JTextField("Inserisci targa:");
		messagioRichiestaTarga.setFont(font);
		messagioRichiestaTarga.setEditable(false); // faccio in modo che non posso essere editabile
        
		JTextField inserisciTarga = new JTextField();
		inserisciTarga.setFont(font);
		
		JButton bottoneConferma = new JButton("Ok");

		pannelloTarga.add(messagioRichiestaTarga);
		pannelloTarga.add(inserisciTarga);
		pannelloTarga.add(bottoneConferma);
		
		disable_enabled_buttons(pannelloTarga, bottoneTrovaVeicolo);
		
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
	

//	private void selezioneGrafica(JButton bottone) {
//		bottone.addActionListener(new ActionListener() {
//			private boolean isPressed = false;
//			
//			@Override
//            public void actionPerformed(ActionEvent e) {
//                isPressed = !isPressed;
//                bottone.getModel().setPressed(isPressed);
//                bottone.getModel().setArmed(isPressed);
//            }
//			
//		});
//	}

	public static void main(String[] args) {
		
		GUIVeicoli gui = new GUIVeicoli();
		gui.frameIniziale.setVisible(true);
	}
	
}



