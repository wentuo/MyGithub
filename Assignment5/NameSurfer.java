/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		 namelabel = new JLabel("Name ");
		 add(namelabel, SOUTH);
	      
	     nameField = new JTextField(20);
	     add(nameField, SOUTH);
	     
	     Graph = new JButton("Graph");
	     add(Graph, SOUTH);
	     
	     Clear = new JButton("Clear");
	     add(Clear, SOUTH);
	     
	     addActionListeners();
	     
	     namesdatabase = new NameSurferDataBase(NAMES_DATA_FILE);
	     
	     graph = new NameSurferGraph();
	     add(graph);
		
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		if (cmd.equals("Graph")) {
			  NameSurferEntry entry = namesdatabase.findEntry(nameField.getText());
	          if (entry != null)
	          {
	             graph.addEntry(entry);
	          }
			
		} else if(cmd.equals("Clear")){
			graph.clear();  			
		}
	}
	private JLabel namelabel;
	private JButton Graph;
    private JButton Clear;
	private JTextField nameField;
	private NameSurferDataBase namesdatabase;
	private NameSurferGraph graph;
}
