/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	private ArrayList<NameSurferEntry> drawList = new ArrayList<NameSurferEntry>();
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
	      drawList.clear();
	      update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
        drawList.add(entry);
        update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
	    drawGrid();
	    for (int i=0; i<drawList.size(); i++){
            drawEntry(drawList.get(i), i);
        }
	}
	
	public void drawGrid(){
		double startY = 0;
		double endY = getHeight();
		
		//draws the vertical lines
		for(int i=0;i<NDECADES;i++){
			double x = i *(getWidth()/NDECADES);
			GLine line = new GLine(x, startY, x, endY);
			add(line);
		}
		
		//draws two horizontal lines in the graph
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, endY-GRAPH_MARGIN_SIZE, getWidth(), endY-GRAPH_MARGIN_SIZE));
        
        //draws the decade labels
        for(int i = 0; i<NDECADES; i++) {
            int decade = START_DECADE;
            decade += 10*i;
            String label = Integer.toString(decade);
            double y = getHeight() - GRAPH_MARGIN_SIZE/4;
            double x = 2 + i * (getWidth()/NDECADES);
            GLabel displayedDecade = new GLabel(label, x, y);
            add(displayedDecade);
        }
	}
	 private void drawEntry(NameSurferEntry entry, int entryNumber) {
		 String name = entry.getName();
		 for (int i=0; i<NDECADES-1; i++){
			 int var_rank1 = entry.getRank(i);
			 int var_rank2 = entry.getRank(i+1);
			 
			 double x1 = (getWidth()/NDECADES)*i;
			 double x2 = (getWidth()/NDECADES)*(i+1);
			 double y1= getY(var_rank1);
			 double y2= getY(var_rank2);
			 GLine line = new GLine(x1, y1, x2, y2);
			 line.setColor(getColor(i));
             add(line);
             
             if(var_rank1==0){
            	 name= name + " *";
             }else{
            	 name = name + " " + var_rank1;
             }
             GLabel label = new GLabel(name);
             label.setColor(getColor(i));
             add(label, x1+3, y1-3);
			 
		 }
		 
	 }
	 private double getY(int rank){
		if(rank==0){
			return (getHeight() - GRAPH_MARGIN_SIZE);
		}else{
			return (GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank/MAX_RANK);
		}
	 }
	 private Color getColor(int i){
		  i = i%4;

	      if(i==0){
	    	  return Color.BLACK;
	      }else if(i==1){
	    	  return Color.RED;
	      }else if(i==2){
	    	  return Color.BLUE;
	      }else {
	    	  return Color.MAGENTA;
	      }

	 }
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
