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
	public BigInteger E; public BigInteger N; private BigInteger D; 
	//E is the encryption key, N is the modulus, D is the decyrption key
	private BigInteger p; private BigInteger q;

	
	@SuppressWarnings("unused")
	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Title
		JLabel title = new JLabel("RSA");
		c.gridx = 0; c.gridy = 0;
		pane.add(title);
		
		//other elements should lock to the right
		c.anchor = GridBagConstraints.WEST;
		
		//Label for the first prime
		JLabel pLabel = new JLabel("P: ");
		c.gridx = 0; c.gridy = 1;
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
		
		//Actionlistener for the p textfield
		pField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//Textfield for q
		JTextField qField = new JTextField(2);
		c.gridx = 4; c.gridy = 1;
		c.weightx = 0.4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(qField, c);
		
		//Actionlistener for the q textfield
		qField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
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
		
		messageField.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
			}
			
		});
		
		//Label for the encrypted method
		JLabel eMessageLabel = new JLabel("Encrypted Message:");
		c.gridx = 0; c.gridy = 5;
		c.weightx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		pane.add(eMessageLabel, c);
		
		//TextField for eMessage
		JTextField eMessageField = new JTextField(6);
		c.gridx = 0; c.gridy = 6;
		c.gridwidth = 6;
		pane.add(eMessageField, c);
		
		//Decrypt button
		JButton decrypt = new JButton("Decrypt");
		c.gridx = 0; c.gridy = 7;
		c.gridwidth = 1;
		pane.add(decrypt, c);
		
		//Decrypt textfield
		JTextField decryptField = new JTextField();
		c.gridx = 0; c.gridy = 8;
		c.gridwidth = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(decryptField, c);
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
