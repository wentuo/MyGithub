/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private GOval ball; 
	
/** declare an random-number instance  */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private double vx, vy;	
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setup(); 
		 while (true) {  
	           moveBall();  
	           checkForCollision();  
	           pause(50);  
	       } 
	}
	private void setup() {  
		paddles();
		ball(); 
	}
	public void paddles(){
		for (int i =0; i< NBRICK_ROWS;i++){
			for(int j = 0; j<NBRICKS_PER_ROW;j++){
				GRect brick = new GRect(j*(BRICK_WIDTH+BRICK_SEP),70+i*(BRICK_SEP+BRICK_HEIGHT),BRICK_WIDTH,8);
				brick.setFilled(true);
				if(i==0 || i==1){
					brick.setFillColor(Color.RED);
				}else if(i==2|| i==3){
					brick.setFillColor(Color.ORANGE);
				}else if(i==4 || i==5){
					brick.setFillColor(Color.YELLOW);
				}else if(i==6 || i==7){
					brick.setFillColor(Color.GREEN);
				}else{
					brick.setFillColor(Color.CYAN);
				}	
				add(brick);
			}
			
		}
		
		
	}
	public void ball(){
		ball = new GOval(APPLICATION_WIDTH/4,APPLICATION_HEIGHT/2,2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		moveBall();
		
	}
	private void moveBall() {  
	
		vx = rgen.nextDouble(-3.0, +3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx; 
        ball.move(vx,3);
	       
	} 
	   private void checkForCollision() {  
	        // determine if ball has dropped below the floor  
	        if (ball.getY() > getHeight() - BALL_RADIUS) {  
	              
	            // change ball's Y velocity to now bounce upwards  
	            vy = -vy ;  
	              
	            // assume bounce will move ball an amount above the  
	            // floor equal to the amount it would have dropped  
	            // below the floor.  
	          //  double diff = ball.getY() - (getHeight() - BALL_RADIUS);  
	           // ball.move(0, -2 * diff);  
	        }  
	       // if (ball.getX() > getWidth() - BALL_RADIUS) {  
	              
	            // change ball's Y velocity to now bounce upwards  
	          //  vx = -vx ;  
	              
	            // assume bounce will move ball an amount above the  
	            // floor equal to the amount it would have dropped  
	            // below the floor.  
	          //  double diff = ball.getX() - (getWidth() - BALL_RADIUS);  
	          //  ball.move(0, -2 * diff);  
	        //} 
	    } 

}
