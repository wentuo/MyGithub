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
	
	private int ball_remaining = NTURNS;
	
	private int bricks_counter = 0;
	private boolean continueplay = true;
	
/**private instance variable **/ 	
	private GOval ball; 
	private GRect paddle; 
	private GRect message_box; 
	private GLabel label;
/** declare an random-number instance  */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private double vx, vy;	

	
/* Method: run() */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		 setup(); 
		 addMouseListeners();
		 while (continueplay) {  
	           moveBall();  
	           bounceBall(); 
	           checkForCollision(); 
	           pause(50);  
	       } 
	}
	private void setup() { 
		bricksSetup(); 
		paddleSetup();
		ballSetup();
	}
	/** define message box method **/
	private void messageBox(String message){
		message_box = new GRect(PADDLE_WIDTH/2-30,20,60,20);
		message_box.setFilled(true);
		message_box.setFillColor(Color.RED);
		add(message_box);
		label = new GLabel(message);
		label.setLocation(PADDLE_WIDTH/2-30,20);
		add(label);
	}
	/** mouseMoved event response **/
	public void mouseMoved(MouseEvent e){
		if(e.getX()<0){
			paddle.setLocation(0,APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}else if(e.getX()+PADDLE_WIDTH>APPLICATION_WIDTH){
			paddle.setLocation(APPLICATION_WIDTH-PADDLE_WIDTH,APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}else {
			paddle.setLocation(e.getX(),APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}
	}
	
	/** first step:set up bricks as requirement **/
	public void bricksSetup(){
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
				bricks_counter++;
			}
			
		}
			
	}
	/** setting up paddle method **/
	public void paddleSetup(){
		paddle = new GRect((APPLICATION_WIDTH-PADDLE_WIDTH)/2, APPLICATION_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		
	}
	/** setting up ball method **/
	public void ballSetup(){
		if(bricks_counter>0){
			ball = new GOval(APPLICATION_WIDTH/4,APPLICATION_HEIGHT/2,2*BALL_RADIUS,2*BALL_RADIUS);
			ball.setFilled(true);
			add(ball);
			moveBall();
			vx = rgen.nextDouble(10.0, +15.0);
	        if (rgen.nextBoolean(0.5)) vx = -vx; 
	        vy=10.0;
		}

		
	}
	/** Okay,let the ball moving **/
	private void moveBall() {  
        ball.move(vx,vy);       
	} 
	/** changing ball direction while bouncing wall except down wall 
	 * and adding continue playing conditions**/
	private void bounceBall(){
		String ball_message;
		if(ball.getY()<0){
			ball.setLocation(ball.getX(),0);
			vy = -vy;
		}else if(2*BALL_RADIUS>HEIGHT-ball.getY()){
			vy = -vy;	
			ball_remaining--;
			remove(ball);

			if (ball_remaining>1){
				ball_message=ball_remaining+"balls left";
				messageBox(ball_message);
				pause(2500);
				remove(message_box);
				remove(label);
				ballSetup();
				
			}else if(ball_remaining==1){
				ball_message=ball_remaining+"ball left";
				messageBox(ball_message);
				pause(2500);
				remove(message_box);
				remove(label);
				ballSetup();			
			} else{
				ball_message="you lose";
				messageBox(ball_message);
				pause(2500);
				remove(message_box);
				remove(label);
				remove(ball);
				continueplay = false;// continue playing condition,important!!!
			}
		}else if(ball.getX()<0){
			vx = -vx; //change direction while  bouncing left wall
		}else if(2*BALL_RADIUS>WIDTH - ball.getX()){
			vx = -vx;//change direction while  bouncing right wall
		}
	}
	/** checking collision with paddle or brick **/
	private void checkForCollision() {  
		String win_message;
		GObject collider = getCollidingObject();
		if (collider != null){			
			
			if (collider == paddle){
				ball.setLocation(ball.getX(), HEIGHT-PADDLE_Y_OFFSET-BALL_RADIUS*2); //move ball above paddle
				vy = -vy; 
							
			}		
			else if (collider.getWidth() == BRICK_WIDTH){
				vy = -vy; 
				remove(collider);
				bricks_counter--;
				if(bricks_counter==0){
					win_message="you win";
					messageBox(win_message);
					pause(2500);
					remove(message_box);
					remove(label);
					remove(ball);
				}
			}
		}
	} 
	/** condition for collision,Judging four corners for object **/
	private GObject getCollidingObject(){
		GObject collider = null;
		/** check four corner one by one**/
		if (collider == null){
			collider = getElementAt(ball.getX(), ball.getY());
		}		
		if (collider == null){
			collider = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY());			
		}
		if (collider == null){
			collider = getElementAt(ball.getX(), ball.getY()+BALL_RADIUS*2);			
		}
		if (collider == null){
			collider = getElementAt(ball.getX()+BALL_RADIUS*2, ball.getY()+BALL_RADIUS*2);			
		}
		return collider;
		
	}
	  

}
