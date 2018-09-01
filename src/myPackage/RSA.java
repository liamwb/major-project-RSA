package myPackage;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RSA extends JFrame {
	
	@SuppressWarnings("unused")
	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Label for the first prime
		JLabel pLabel = new JLabel("P: ");
		c.gridx = 0; c.gridy = 0;
		pane.add(pLabel, c);
		
		//Label for the second prime
		JLabel qLabel = new JLabel("Q: ");
		c.gridx = 3; c.gridy = 0;
		pane.add(qLabel, c);
		
		//Textfield for p
		JTextField pField = new JTextField();
		c.gridx = 1; c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		pane.add(pField, c);
		
		//Textfield for q
		JTextField qField = new JTextField();
		c.gridx = 4; c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		pane.add(qField, c);
		
		//Label for N
		JLabel nLabel = new JLabel("The N from p & q is :");
		c.gridx = 0; c.gridy = 1;
		pane.add(nLabel, c);
		
		//TextArea for N
		JLabel nOutput = new JLabel("N will appear here");
		c.gridx = 0; c.gridy = 2;
		c.gridwidth = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		pane.add(nOutput, c);
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
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
