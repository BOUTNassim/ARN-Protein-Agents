package tuto.first;

import javax.swing.JOptionPane;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.tools.sniffer.Message;


public class SecondAgent extends GuiAgent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9026391546066053307L;
	int command;
	public static final int PrintName = 0;
	
	transient protected InterSecondAgent myGui;
	
	
	@Override
	protected void setup() {
		System.out.println("Hello Jade!");
		System.out.println("I'm the Second agent");
		//doDelete();
		
		myGui = new InterSecondAgent(this);
		myGui.setVisible(true);
		
		DFAgentDescription dfd = new DFAgentDescription();
	    dfd.setName(getAID());
	    ServiceDescription sd = new ServiceDescription();
	    sd.setType("SecondAgent-DNA-Protein");
	    sd.setName("JADE-Agent-DNA-Protein");
	    dfd.addServices(sd);
	    try {
	      DFService.register(this, dfd);
	    }
	    catch (FIPAException fe) {
	      fe.printStackTrace();
	    }
		
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				ACLMessage msg=receive();
				if(msg!=null) {
					//JOptionPane.showMessageDialog(null, "Message recived"+msg.getContent());
					//myGui.textField.setText("Message: "+msg.getContent());
					
					StringBuilder sb = new StringBuilder();

				    for (int i = 0; i < msg.getContent().length() - 2; i++) {
				        String triplet = msg.getContent().substring(i, i+3);

				        switch (triplet) {
				        case "UUU":
				        case "UUC":
				            sb.append("F");
				            break;
				        case "UUA":
				        case "UUG":
				        case "CUU":
				        case "CUC":
				        case "CUA":
				        case "CUG":
				            sb.append("L");
				            break;
				        case "AUU":
				        case "AUC":
				        case "AUA":
				            sb.append("I");
				            break;
				        case "AUG":
				            sb.append("M");
				            break;
				        case "GUU":
				        case "GUC":
				        case "GUA":
				        case "GUG":
				            sb.append("V");
				            break;
				            // and so on
				        default:
				            // do nothing;
				        }
				        
				        myGui.textField.setText("Message: " + msg.getContent() + " Result: "+sb.toString());
				    }
				    
				}else block();
			}
		});
		
	}
	

	@Override
	protected void onGuiEvent(GuiEvent ev) {
		command = ev.getType();
		switch(command) {
		case PrintName:
			try {
				myGui.textField.setText("My name is: " + getLocalName());
			}catch(Exception e) {
				e.printStackTrace();
			}return;
		}
		
	}

}
