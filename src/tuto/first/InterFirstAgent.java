package tuto.first;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jade.gui.GuiEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class InterFirstAgent extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8012312096922574213L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private FirstAgent myAgent;
	JTextField tMessage;
	JTextField sMessage;
	JTextField aName;
	JTextField aContainer;
	JTextField textField_1;
	JTextField textField;
	JButton PrintName = new JButton("What is my name?");
	JButton mG = new JButton("Migrate");
	JButton cL = new JButton("Clone");
	JButton nA = new JButton("+");
	JButton sM = new JButton("Send");
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	
	
	
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFirstAgent frame = new InterFirstAgent(myAgent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}**/

	/**
	 * Create the frame.
	 */
	public InterFirstAgent(FirstAgent a) {
		super("Inetface FistAgent");
		this.myAgent=a;
		try {
			
			jbInit();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		PrintName.addActionListener(this);
		mG.addActionListener(this);
		cL.addActionListener(this);
		nA.addActionListener(this);
		sM.addActionListener(this);
		
	}
	
	public void jbInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.WEST, cL, 81, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, cL, -239, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, cL, -274, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, mG, 0, SpringLayout.NORTH, cL);
		sl_contentPane.putConstraint(SpringLayout.WEST, mG, 6, SpringLayout.EAST, cL);
		sl_contentPane.putConstraint(SpringLayout.WEST, PrintName, 0, SpringLayout.WEST, cL);
		sl_contentPane.putConstraint(SpringLayout.EAST, nA, -10, SpringLayout.EAST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		
		contentPane.add(PrintName);
		contentPane.add(mG);
		contentPane.add(cL);
		contentPane.add(nA);
		contentPane.add(sM);
		
		tMessage = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, tMessage, -1, SpringLayout.NORTH, sM);
		sl_contentPane.putConstraint(SpringLayout.WEST, tMessage, 0, SpringLayout.WEST, PrintName);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, tMessage, -12, SpringLayout.NORTH, PrintName);
		sl_contentPane.putConstraint(SpringLayout.EAST, tMessage, -89, SpringLayout.WEST, sM);
		contentPane.add(tMessage);
		
		sMessage = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, sMessage, 6, SpringLayout.SOUTH, mG);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, sMessage, -5, SpringLayout.NORTH, tMessage);
		sl_contentPane.putConstraint(SpringLayout.EAST, sMessage, -151, SpringLayout.EAST, contentPane);
		contentPane.add(sMessage);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.EAST, sM, 0, SpringLayout.EAST, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 81, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -78, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, PrintName, -6, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 162, SpringLayout.NORTH, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, sM, 64, SpringLayout.SOUTH, textField_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_1, -78, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_1, -268, SpringLayout.SOUTH, contentPane);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		aName = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, nA, -1, SpringLayout.NORTH, aName);
		sl_contentPane.putConstraint(SpringLayout.EAST, aName, -78, SpringLayout.EAST, contentPane);
		contentPane.add(aName);
		
		aContainer = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, aContainer, 81, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, aContainer, -219, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, aName, 11, SpringLayout.EAST, aContainer);
		contentPane.add(aContainer);
		
		JLabel lblNewLabel = new JLabel("Container Name");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 81, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -15, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -38, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, aContainer, 6, SpringLayout.SOUTH, lblNewLabel);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agent Name");
		sl_contentPane.putConstraint(SpringLayout.NORTH, aName, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, aName);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Location");
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 31, SpringLayout.EAST, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 3, SpringLayout.NORTH, textField_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Message");
		sl_contentPane.putConstraint(SpringLayout.WEST, sMessage, 29, SpringLayout.EAST, lblNewLabel_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 42, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel_2);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Receiver");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 4, SpringLayout.NORTH, sM);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel_2);
		contentPane.add(lblNewLabel_4);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == PrintName) {
			GuiEvent ge = new GuiEvent(this, myAgent.PrintName);
			ge.addParameter("");
			ge.addParameter("");
			myAgent.postGuiEvent(ge);
		}else if (ae.getSource() == mG) {
			GuiEvent ka = new GuiEvent(this, myAgent.mG);
			ka.addParameter("");
			ka.addParameter("");
			myAgent.postGuiEvent(ka);
		}else if (ae.getSource() == cL) {
			GuiEvent kaa = new GuiEvent(this, myAgent.cL);
			kaa.addParameter("");
			kaa.addParameter("");
			myAgent.postGuiEvent(kaa);
		}else if (ae.getSource() == nA) {
			GuiEvent kab = new GuiEvent(this, myAgent.nA);
			kab.addParameter("");
			kab.addParameter("");
			myAgent.postGuiEvent(kab);
		}else if (ae.getSource() == sM) {
			GuiEvent kaM = new GuiEvent(this, myAgent.sM);
			kaM.addParameter("");
			kaM.addParameter("");
			myAgent.postGuiEvent(kaM);
		}
	}
}
