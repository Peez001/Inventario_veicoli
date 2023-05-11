package Package_inventario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIVeicoli extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JFrame frameIniziale;
	private JPanel pannelloCards;	
	private JPanel pannelloIniziale;
	private JPanel pannelloInventario;
	
	public GUIVeicoli() {
		
		init();
		
	}
	
	private void init() {
		
		schermataIniziale();	
		schermataInventario();	
		schermataAddVeicolo();
		
	}

	private void schermataIniziale() {
		
		ImageIcon logo = new ImageIcon("veicolo.png"); //Creo il logo. Il file é all'interno del progetto
		Color coloreSfondo = new Color(0,94,131); //Imposto il colore dello sfondo	
				
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
		etichetta.setFont(new Font("Helvetica", Font.BOLD, 25)); //Imposto il font dell'etichetta
		etichetta.setHorizontalAlignment(JLabel.CENTER); //Imposto la posizione orizzontale del testo rispetto all'immagine
		etichetta.setVerticalAlignment(JLabel.CENTER); //Imposto la posizione verticale del testo rispetto all'immagine
		
		JButton bottoneInizio = new JButton("ACCEDI ALL'INVENTARIO"); //Creo il bottone
		bottoneInizio.setFocusable(false); //Tolgo la box attorno al testo del bottone
		bottoneInizio.setFont(new Font("Helvetica", Font.BOLD, 25)); //Imposto il font del bottone
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
				
		JButton bottoneAggiungiVeicolo = new JButton("AGGIUNGI NUOVO VEICOLO");
		bottoneAggiungiVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneAggiungiVeicolo.setFont(new Font("Helvetica", Font.BOLD, 25));
		bottoneAggiungiVeicolo.setFocusable(false);
		
		JButton bottoneTrovaVeicolo = new JButton("TROVA VEICOLO");
		bottoneTrovaVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneTrovaVeicolo.setFont(new Font("Helvetica", Font.BOLD, 25));
		bottoneTrovaVeicolo.setFocusable(false);
		
		JButton bottoneStampaVeicoli = new JButton("STAMPA VEICOLI");
		bottoneStampaVeicoli.setPreferredSize(new Dimension(200,200));
		bottoneStampaVeicoli.setFont(new Font("Helvetica", Font.BOLD, 25));
		bottoneStampaVeicoli.setFocusable(false);
		
		pannelloInventario.setLayout(new GridLayout(3,1));
		pannelloInventario.add(bottoneTrovaVeicolo);
		pannelloInventario.add(bottoneAggiungiVeicolo);
		pannelloInventario.add(bottoneStampaVeicoli);
		
		pannelloCards.add(pannelloInventario);
		frameIniziale.add(pannelloCards);
		
	}

	private void schermataAddVeicolo() {

		// vezzo aggiungi qui il tuo nuovo codice
	}

	public static void main(String[] args) {
		
		GUIVeicoli gui = new GUIVeicoli();
		gui.frameIniziale.setVisible(true);
	}
	
}



