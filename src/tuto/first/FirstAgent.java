package tuto.first;


import jade.core.AID;
import jade.core.ContainerID;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.lang.Object;


public class FirstAgent extends GuiAgent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1057702889443237667L;
	int command;
	public static final int PrintName = 0;
	public static final int mG = 1;
	public static final int cL = 2;
	public static final int nA = 3;
	public static final int sM = 4;
	
	transient protected InterFirstAgent myGui;
	
	
	@Override
	protected void setup() {
		System.out.println("Hello Jade!");
		System.out.println("I'm the first agent");
		//doDelete();
		
		myGui = new InterFirstAgent(this);
		myGui.setVisible(true);
		
		DFAgentDescription dfd = new DFAgentDescription();
	    dfd.setName(getAID());
	    ServiceDescription sd = new ServiceDescription();
	    sd.setType("FirstAgent-DNA-Protein");
	    sd.setName("JADE-Agent-DNA-Protein");
	    dfd.addServices(sd);
	    try {
	      DFService.register(this, dfd);
	    }
	    catch (FIPAException fe) {
	      fe.printStackTrace();
	    }
		
	}
	

	@Override
	protected void onGuiEvent(GuiEvent ev) {
		// TODO Auto-generated method stub
		command = ev.getType();
		//switch(command) {
		if (command == PrintName) {
			try {
				myGui.textField.setText("My name is: " + getLocalName());
			}catch(Exception e) {
				e.printStackTrace();
			}return;
		}
		if (command == mG) {
			
            ContainerID destination1 = new ContainerID();
            destination1.setName("Container-1");                
            System.out.println("waiting 2 seconds! before traveling ... ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }                

            this.doMove(destination1);   
		}
		if (command == cL) {
			
            ContainerID destination1 = new ContainerID();
            destination1.setName(myGui.textField_1.getText());                
            System.out.println("waiting 2 seconds! Clonning ... ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }                

            this.doClone(destination1, getLocalName()+"_Clone");   
		}
		if (command == nA) {
			//Get the JADE runtime interface (singleton)
			jade.core.Runtime runtime = jade.core.Runtime.instance();
			//Create a Profile, where the launch arguments are stored
			Profile profile = new ProfileImpl();
			profile.setParameter(Profile.CONTAINER_NAME, myGui.aContainer.getText());
			profile.setParameter(Profile.MAIN_HOST, "localhost");
			//create a non-main agent container
			ContainerController container = runtime.createAgentContainer(profile);
			try {
			        AgentController ag = container.createNewAgent(myGui.aName.getText(), 
			                                      "tuto.first.SecondAgent", 
			                                      new Object[] {});//arguments
			        ag.start();
			} catch (StaleProxyException e) {
			    e.printStackTrace();
			}
			
		}
		if (command == sM) {
			
			addBehaviour(new OneShotBehaviour() {
				
				@Override
				public void action() {
					// TODO Auto-generated method stub
					ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
					msg.setContent(myGui.sMessage.getText());
					msg.addReceiver(new AID(myGui.tMessage.getText(), AID.ISLOCALNAME));
					send(msg);
				}
			});
			
		}
		
		
		
	}
		
	//}

}
