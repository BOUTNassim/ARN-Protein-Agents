/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package tuto.first;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
  @author Giovanni Caire - TILAB
 */
class BookSellerGui extends JFrame {	
	private BookSellerAgent myAgent;
	
	private JTextField titleField, priceField, mgField, aContainer, aName;
	
	BookSellerGui(BookSellerAgent a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5, 5, 5, 5));
		
		p.add(new JLabel("Book title:"));
		titleField = new JTextField(15);
		p.add(titleField);
		
		p.add(new JLabel("Price:"));
		priceField = new JTextField(15);
		p.add(priceField);
		
		p.add(new JLabel("Location:"));
		mgField = new JTextField(15);
		p.add(mgField);
		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					myAgent.updateCatalogue(title, Integer.parseInt(price));
					titleField.setText("");
					priceField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		JButton mgButton = new JButton("Migrate");
		mgButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String location = mgField.getText().trim();
					myAgent.migration(location);
					mgField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		JButton clButton = new JButton("Clone");
		clButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String location = mgField.getText().trim();
					myAgent.cloning(location);
					mgField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		p.add(new JLabel("Container Name:"));
		aContainer = new JTextField(15);
		p.add(aContainer);
		
		p.add(new JLabel("Agent Name"));
		aName = new JTextField(15);
		p.add(aName);
		
		JButton naButton = new JButton("+");
		clButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					//Get the JADE runtime interface (singleton)
					jade.core.Runtime runtime = jade.core.Runtime.instance();
					//Create a Profile, where the launch arguments are stored
					Profile profile = new ProfileImpl();
					profile.setParameter(Profile.CONTAINER_NAME, aContainer.getText());
					profile.setParameter(Profile.MAIN_HOST, "localhost");
					//create a non-main agent container
					ContainerController container = runtime.createAgentContainer(profile);
					try {
					        AgentController ag = container.createNewAgent(aName.getText(), 
					                                      "tuto.first.SecondAgent", 
					                                      new Object[] {});//arguments
					        ag.start();
					} catch (StaleProxyException e) {
					    e.printStackTrace();
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		p.add(mgButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		p.add(clButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		p.add(naButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
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
