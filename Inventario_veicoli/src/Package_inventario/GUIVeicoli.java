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
	
	private ImageIcon logo;
	private JFrame frameIniziale;
	private JPanel pannelloCards;	
	private JPanel pannelloIniziale;
	private JPanel pannelloInventario;
	
	public GUIVeicoli() {
		
		init();
		
	}
	
	private void init() {
		logo = new ImageIcon("veicolo.png"); //Creo il logo. Il file é all'interno del progetto
		Color coloreSfondo = new Color(0,94,131); //Imposto il colore dello sfondo	
		
		CardLayout cardLayout = new CardLayout();
		pannelloCards = new JPanel(cardLayout);
		
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
		
		frameIniziale = new JFrame("Inventario Veicoli"); //Creo istanza del frame e metto il titolo
		//frameIniziale.setExtendedState(JFrame.MAXIMIZED_BOTH); //inizialmente l'interfaccia compare a schermo intero
		frameIniziale.setSize(800,800); //Imposto la dimensione iniziale del frame
		//frameIniziale.setVisible(true);	//Rendo il frame visibile
		frameIniziale.getContentPane().setBackground(coloreSfondo); //Scelgo il colore dello sfondo
		frameIniziale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Alla pressione della X rossa chiudo l'applicazione
		//frameIniziale.setLayout(cardLayout); //Definisco il Layout manager del frame esterno
		frameIniziale.setIconImage(logo.getImage()); //Imposto l'icona del frame
		
		pannelloIniziale = new JPanel();
		pannelloIniziale.setLayout(new BorderLayout());
		
		JPanel pannelloCentrale = new JPanel();
		pannelloCentrale.setLayout(new GridLayout(3,1));
		
		pannelloInventario = new JPanel();
		pannelloInventario.setLayout(new BorderLayout());
		
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
		
		//GUI per pannello successivo, quando il pulsante "Accedi" è stato premuto
	
		JPanel pannelloBottoni = new JPanel();
		pannelloInventario.add(pannelloBottoni, BorderLayout.NORTH);
		
		JButton bottoneAggiungiVeicolo = new JButton("AGGIUNGI NUOVO VEICOLO");
		bottoneAggiungiVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneAggiungiVeicolo.setFont(new Font("Helvetica", Font.BOLD, 25));
		bottoneAggiungiVeicolo.setFocusable(false);
		
		JButton bottoneTrovaVeicolo = new JButton("TROVA VEICOLO");
		bottoneTrovaVeicolo.setPreferredSize(new Dimension(200,200));
		bottoneTrovaVeicolo.setFont(new Font("Helvetica", Font.BOLD, 25));
		bottoneTrovaVeicolo.setFocusable(false);
		
		pannelloBottoni.setLayout(new GridLayout(1,2));
		pannelloBottoni.add(bottoneTrovaVeicolo);
		pannelloBottoni.add(bottoneAggiungiVeicolo);
		
		JPanel pannelloListaVeicoli = new JPanel();
		pannelloInventario.add(pannelloListaVeicoli, BorderLayout.CENTER);
		
		pannelloCards.add(pannelloIniziale);
		pannelloCards.add(pannelloInventario);
		
		frameIniziale.add(pannelloCards);
		
		bottoneInizio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(pannelloCards);
			}
		});
	}

	public JFrame getFrameIniziale() {
		return frameIniziale;
	}

	
}



