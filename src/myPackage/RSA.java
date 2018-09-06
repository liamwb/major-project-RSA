package myPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RSA extends JFrame implements ActionListener {
	public static BigInteger E; public static BigInteger N; private static BigInteger D; 
	//E is the encryption key, N is the modulus, D is the decyrption key
	private static BigInteger p; private static BigInteger q;
	
	public static String messageInput; public static String decryptedMessageOutput; 
	public static BigInteger message; public static BigInteger encryptedMessage; public static BigInteger decryptedMessage;
	//Since messages will need to get converted to some number for the encryption algorithm to function

	
	@SuppressWarnings("unused")
	public static void addComponentsToPane(Container pane) {
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Title
		JLabel title = new JLabel("RSA");
		title.setFont(new Font("Serif", Font.PLAIN, 18));
		c.gridx = 3; c.gridy = 0;
		c.gridwidth = 6;
		c.anchor = GridBagConstraints.NORTH;
		pane.add(title);
		
		//other elements should lock to the right
		c.anchor = GridBagConstraints.WEST;
		
		//Label for the first prime
		JLabel pLabel = new JLabel("P: ");
		c.gridx = 0; c.gridy = 1;
		c.gridwidth = 1;
		pane.add(pLabel, c);
		
		//Label for the second prime
		JLabel qLabel = new JLabel("Q: ");
		c.gridx = 3; c.gridy = 1;
		pane.add(qLabel, c);
		
		//Textfield for p
		JTextField pField = new JTextField(2);
		c.gridx = 1; c.gridy = 1;
		c.weightx = 0.4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(pField, c);
		
		//Textfield for q
		JTextField qField = new JTextField(2);
		c.gridx = 4; c.gridy = 1;
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
		JButton decrypt = new JButton("Decrypt");
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
		ActionListeners start here
		***************************/
			
		/*
			//Actionlistener for the p textfield
			pField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					p = new BigInteger(pField.getText());
					//This is a constructor method for BigInteger which takes a string
				}
			});
		
			//Actionlistener for the q textfield
			qField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					q = new BigInteger()
				}
			});			
				
			//Actionlistener for the message textfield
			messageField.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					
				}
				
			});
				
				
				*/
			//Actionlistener for the decrypt button
			decrypt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
//Setting the values of the input variables
					try {
						p = new BigInteger(pField.getText());
						q = new BigInteger(qField.getText());	
						
//resetting the colour and contents of dMessageField in case the catch block executed in the past
						dMessageField.setForeground(null);
						dMessageField.setText("");
					}
					
					catch (NumberFormatException noText) {
//This exception is thrown when BigInteger() tries to construct from an empty string, or a string with letter in it
						dMessageField.setForeground(Color.RED);
						dMessageField.setText("Valid inputs required for p and q");	
						return; //if p and/or q aren't valid
					}
					
					messageInput = messageField.getText();
					message = Methods.encode(messageInput);
					//messageInput is a string, read from the messageField
					//message is an integer representation of the string messageInput
					
					N = Methods.findN(p, q);
					E = Methods.findE(Methods.findL(p, q), N);
					D = Methods.findD(E, Methods.findL(p, q));
					
					//N and E are public information, but I would guess that in a "proper" implementation of RSA, the value of 
					//D would not be stored in memory.
					
					System.out.println("N: " + N + ", E: " + E + ", D: " + D);
					
					encryptedMessage = Methods.encrypt(message, E, N);
					//The integer is encrypted, not the string
					System.out.println("Encrypted message is (integer) " + encryptedMessage);
					eMessageField.setText("" + encryptedMessage);
					
					decryptedMessage = Methods.decrypt(encryptedMessage, D, N);
					//The encrypted integer is decrypted to return the original integer
					decryptedMessageOutput = Methods.decode(decryptedMessage);
					//that integer can then be converted back into a string value
					
					dMessageField.setText(decryptedMessageOutput);
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
	
	//From the oracle tutorial on GridBagLayout
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
