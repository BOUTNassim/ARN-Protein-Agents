package tuto.first;

import java.awt.BorderLayout;
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

public class InterSecondAgent extends JFrame implements ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8833711904516209233L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	private SecondAgent myAgent;
	JTextField textField;
	JButton PrintName = new JButton("What is my name?");
	
	
	
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
	public InterSecondAgent(SecondAgent a) {
		super("Inetface SecondAgent");
		this.myAgent=a;
		try {
			
			jbInit();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		PrintName.addActionListener(this);
		
	}
	
	public void jbInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		contentPane.add(PrintName, BorderLayout.NORTH);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if (ae.getSource() == PrintName) {
			GuiEvent ge = new GuiEvent(this, myAgent.PrintName);
			ge.addParameter("");
			ge.addParameter("");
			myAgent.postGuiEvent(ge);
		}
	}

}
