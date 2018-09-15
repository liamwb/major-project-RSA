package myPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RSA extends JFrame implements ActionListener {
	public static BigInteger E; public static BigInteger N; private static BigInteger D; 
	//E is the encryption key, N is the modulus, D is the decyrption key
	private static BigInteger p; private static BigInteger q;
	
	public static String messageInput; public static String decryptedMessageOutput; 
	public static BigInteger message; public static BigInteger encryptedMessage; public static BigInteger decryptedMessage;
	//Since messages will need to get converted to some number for the encryption algorithm to function

	
	public static void addComponentsToPane(Container pane) {
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Title
		JLabel title = new JLabel("RSA");
		title.setFont(new Font("Serif", Font.PLAIN, 18));
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.NORTH;
		pane.add(title);
		
		//Label to explain how to input Mersenne primes
		JLabel mersenne = new JLabel("To input a Mersenne prime: type \"2, [exponent]\"");
		c.gridx = 3; c.gridy = 0;
		c.gridwidth = 1;
		pane.add(mersenne, c);
		
		//other elements should lock to the right
		c.anchor = GridBagConstraints.WEST;
		
		//Label for the first prime
		JLabel pLabel = new JLabel("P: ");
		c.gridx = 0; c.gridy = 1;
		c.weightx = 0.1;
		pane.add(pLabel, c);
		
		//Label for the second prime
		JLabel qLabel = new JLabel("Q: ");
		c.gridx = 3; c.gridy = 1;
		c.weightx = 0.1;
		pane.add(qLabel, c);
		
		//Textfield for p
		JTextField pField = new JTextField(2);
		c.gridx = 0; c.gridy = 1;
		c.weightx = 0.4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(pField, c);
		
		//Textfield for q
		JTextField qField = new JTextField(2);
		c.gridx = 3; c.gridy = 1;
		c.weightx = 0.4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(qField, c);
		
		//Label for message instructions
		JLabel messageLabel = new JLabel("Put the message to be encrypted below");
		c.gridx = 0; c.gridy = 3;
		c.weightx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		pane.add(messageLabel, c);
		
		//Textfield for the message to be encrypted
		JTextField messageField = new JTextField();
		c.gridx = 0; c.gridy = 4;
		c.weightx = 1;
		c.gridwidth = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(messageField, c);
		
		//Label for the encrypted method
		JLabel eMessageLabel = new JLabel("Encrypted Message:");
		c.gridx = 0; c.gridy = 5;
		c.weightx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		pane.add(eMessageLabel, c);
		
		//TextField for eMessage
		JTextField eMessageField = new JTextField();
		eMessageField.setEditable(false); //The user should not be able to edit the contents of this textfield
		c.gridx = 0; c.gridy = 6;
		c.gridwidth = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(eMessageField, c);
		
		//Decrypt button
		JButton decrypt = new JButton("Do the thing!");
		c.gridx = 0; c.gridy = 7;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		pane.add(decrypt, c);
		
		//Textfield for decrypted message
		JTextField dMessageField = new JTextField();
		dMessageField.setEditable(false); //same deal as with the eMessageField
		c.gridx = 0; c.gridy = 8;
		c.gridwidth = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(dMessageField, c);
		
		/**************************
		 * Hidden elements
		 * All these elements start hidden
		 **************************/
		
		//Button to show hidden elements
		JButton showButton = new JButton("Show behind the scenes");
		c.gridx = 3; c.gridy = 9;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		pane.add(showButton, c);
		
		//Labels for N, E, and D
		JLabel NLabel = new JLabel("N (the modulus): ");
		c.gridx = 0; c.gridy = 10;
		c.gridwidth = 1;
		NLabel.setVisible(false);
		pane.add(NLabel, c);
		
		JLabel ELabel = new JLabel("E (the encryption key): ");
		c.gridx = 0; c.gridy = 11;
		c.gridwidth = 1;
		ELabel.setVisible(false);
		pane.add(ELabel, c);

		JLabel DLabel = new JLabel("D (the decryption key): ");
		c.gridx = 0; c.gridy = 12;
		c.gridwidth = 1;
		DLabel.setVisible(false);
		pane.add(DLabel, c);
		
		//Textfields for N, E, and D
		JTextField NField = new JTextField(5);
		NField.setEditable(false);
		c.gridx = 1; c.gridy = 10;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		NField.setVisible(false);
		pane.add(NField, c);
		
		JTextField EField = new JTextField(5);
		EField.setEditable(false);
		c.gridx = 1; c.gridy = 11;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		EField.setVisible(false);
		pane.add(EField, c);
		
		JTextField DField = new JTextField(5);
		DField.setEditable(false);
		c.gridx = 1; c.gridy = 12;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		DField.setVisible(false);
		pane.add(DField, c);
		
	
		//A label to explain how RSA works, and what the numbers mean
		JTextArea explanationLabel = new JTextArea();
		explanationLabel.setText
		(
		"RSA is a \"trapdoor function\". This means that, with only the \"public\" information \n"
		+ "(N and E), it is very easy to compute in one direction (to encrypt things with). However, \n"
		+ "with only that information and an encrypted message, it is very difficult to decrypt. \n"
		+ "The special feature of a trapdoor function is that, with another, secret, piece of information, \n"
		+ "the message becomes very easy to decrypt. Here, N is the product of p and q, E is the encryption \n"
		+ "key, which is determined by being coprime with N, and (p-1)(q-1), and D is the decryption key, which is \n"
		+ "determined by the rule: D*E mod (p-1)(q-1)."
		);
		c.gridx = 0; c.gridy = 13;
		c.gridwidth = 6; c.gridheight = 6;
		explanationLabel.setVisible(false);
		pane.add(explanationLabel, c);
		
		
		/**************************
		ActionListeners start here
		***************************/

			//ActionListener for the Behind the Scenes Button (showButton)
		
			showButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					NLabel.setVisible(!NLabel.isVisible());
					ELabel.setVisible(!ELabel.isVisible());
					DLabel.setVisible(!DLabel.isVisible());
					NField.setVisible(!NField.isVisible());
					EField.setVisible(!EField.isVisible());
					DField.setVisible(!DField.isVisible());
					explanationLabel.setVisible(!explanationLabel.isVisible());
					
					//Toggling the visibility of the "behind the scenes" stuff whenever the button is pressed
					
				}
			});
		
			//Actionlistener for the Do the Thing! button
			decrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Boolean pMersenne; Boolean qMersenne;
					//variables to store whether or not the inputs for p and q might be Mersenne primes
					if (pField.getText().indexOf(',') != -1) {pMersenne = true; System.out.println("Found a comma");}
					else pMersenne = false;
					
					
					if (qField.getText().indexOf(',') != -1) {qMersenne = true; System.out.println("Found a comma");}
					else qMersenne = false;
					
					//If there isn't a comma in the textfield, then it can't be a Mersenne prime
					
					try {
						if (pMersenne == true) {
							BigInteger pBase = new BigInteger(pField.getText().substring(0, pField.getText().indexOf(',')));
							int pExponent = Integer.parseInt(pField.getText().substring(pField.getText().indexOf(',') +1).trim());
						//pBase is all the characters before the comma, and pExponent is all the characters after (not including the comma)	
							p = (pBase.pow(pExponent)).subtract(BigInteger.ONE);
						//Mersenne primes come in the formula a^b - 1, which is the operation which is being performed here
						}
						
						else {
							p = new BigInteger(pField.getText());
						}
						
						if (qMersenne) {
							BigInteger qBase = new BigInteger(qField.getText().substring(0, qField.getText().indexOf(',')));
							int qExponent = Integer.parseInt(qField.getText().substring(qField.getText().indexOf(',') +1).trim());
						//qBase is all the characters before the comma, and qExponent is all the characters after (not including the comma)
							q = (qBase.pow(qExponent)).subtract(BigInteger.ONE);
						}
						
						else {
							q = new BigInteger(qField.getText());
						}
						
						//resetting the colour and contents of dMessageField in case the catch block executed in the past
						dMessageField.setForeground(null);
						dMessageField.setText("");
					}
					
					catch (NumberFormatException n) {
						dMessageField.setForeground(Color.RED);
						dMessageField.setText("Valid inputs required for p and q");	
						return; //if p and/or q aren't valid
					}
					
					
				/*	
					if (pField.getText().indexOf(',') != -1) {
			//.indexOf returns -1 if the char is not present in the string. So if there is a comma, the contents of this if statement will run	
						try {
							BigInteger pBase = new BigInteger(pField.getText().substring(0, pField.getText().indexOf(',')));
							BigInteger pExponent = new BigInteger(pField.getText().substring(pField.getText().indexOf(',') +1));
						//pBase is all the characters before the comma, and pExponent is all the characters after (not including the comma)
						}
						
						catch (NumberFormatException n) {
							//if, for example, there are two commas in one of the inputs
							dMessageField.setForeground(Color.RED);
							dMessageField.setText("Valid inputs required for p and q");	
							return; //if p and/or q aren't valid
						}
					}
					
					if (qField.getText().indexOf(',') != -1) {
					
						try {
							BigInteger qBase = new BigInteger(qField.getText().substring(0, qField.getText().indexOf(',')));
							BigInteger qExponent = new BigInteger(qField.getText().substring(qField.getText().indexOf(',') +1));
						//qBase is all the characters before the comma, and qExponent is all the characters after (not including the comma)
						}
						
						catch (NumberFormatException n) {
							//if, for example, there are two commas in one of the inputs
							dMessageField.setForeground(Color.RED);
							dMessageField.setText("Valid inputs required for p and q");	
							return; //if p and/or q aren't valid
						}
						
					}
					
//Setting the values of the input variables
					try {
						p = new BigInteger(pField.getText());
						q = new BigInteger(qField.getText());	
						
//resetting the colour and contents of dMessageField in case the catch block executed in the past
						dMessageField.setForeground(null);
						dMessageField.setText("");
					}
					
					catch (NumberFormatException n) {
//This exception is thrown when BigInteger() tries to construct from an empty string, or a string with letter in it
						dMessageField.setForeground(Color.RED);
						dMessageField.setText("Valid inputs required for p and q");	
						return; //if p and/or q aren't valid
					}
					
					*/
					
					messageInput = messageField.getText();
					message = Methods.encode(messageInput);
					//messageInput is a string, read from the messageField
					//message is an integer representation of the string messageInput
					
					N = Methods.findN(p, q);
					NField.setText("" + N);
					
					System.out.println("message" + message+" N " + N);
					
					//RSA will only encrypt messages smaller than N, so this checks to make sure m is valid given N
					if (message.compareTo(N) > 0) {
						dMessageField.setForeground(Color.RED);
						dMessageField.setText("Message is to large to for current N");
						return;
					}
					
					E = Methods.findE(Methods.findL(p, q), N);
					D = Methods.findD(E, Methods.findL(p, q));
					
					EField.setText("" + E);
					DField.setText("" + D);
					
					/*N and E are public information, but I would guess that in a "proper" implementation of RSA, the value of 
					 *D would not be stored in memory. However, I am doing it because the point of this is not to be properly 
					 *secure, but to explore/explain how RSA works. Given that D is being displayed in a textfield, it doesn't seem like
					 *a huge deal if it is also stored in memory.
					*/
					
					encryptedMessage = Methods.encrypt(message, E, N);
					//The integer is encrypted, not the string
					System.out.println("Encrypted message is (integer) " + (encryptedMessage));
					eMessageField.setText("" + encryptedMessage);
					
					
					try {
		//trying to decrypt something that came from non-prime p & q sometimes throws an exception when decoding
					decryptedMessage = Methods.decrypt(encryptedMessage, D, N);
					//The encrypted integer is decrypted to return the original integer
					decryptedMessageOutput = Methods.decode(decryptedMessage);
					//that integer can then be converted back into a string value
					
					dMessageField.setText(decryptedMessageOutput);
					}
					catch (StringIndexOutOfBoundsException n){
						dMessageField.setForeground(Color.RED);
						dMessageField.setText("P and/or q not prime");	
						return;
					}
				}
			});		
		}

	
	private static void createAndShowGUI() {
		//create and set up the window
		JFrame frame = new JFrame("RSA");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Set up the content pane
		addComponentsToPane(frame.getContentPane());
		
		//display the window
		frame.pack();
		frame.setVisible(true);
	}
	
	//From the oracle tutorial on GridBagLayout.
		public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
		}


		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		


}
