package tuto.first;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
  @author Giovanni Caire - TILAB
 */
class BookBuyerGui extends JFrame {	
	private BookBuyerAgent myAgent;
	
	JTextField titleField, responseField, mgField;
	
	BookBuyerGui(BookBuyerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2, 6, 6));
		
		JLabel label_2 = new JLabel("Book Search:");
		p.add(label_2);
		titleField = new JTextField(15);
		p.add(titleField);
		
		JButton searchButton = new JButton("Search");
		p.add(searchButton);
		
		//
		JLabel lblResponse = new JLabel("Response:");
		p.add(lblResponse);
		responseField = new JTextField(15);
		p.add(responseField);
		
		JLabel label = new JLabel("Location:");
		p.add(label);
		mgField = new JTextField(15);
		p.add(mgField);
		
		
		JButton mgButton = new JButton("Migrate");
		p.add(mgButton);
		
		JButton clButton = new JButton("Clone");
		p.add(clButton);
		
		getContentPane().add(p);
		
		clButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String location = mgField.getText().trim();
					myAgent.cloning(location);
					mgField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		mgButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String location = mgField.getText().trim();
					myAgent.migration(location);
					mgField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		searchButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					titleField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void show() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.show();
	}	
}
