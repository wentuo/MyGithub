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
	
/**private instance variable **/ 	
	private GOval ball; 
	private GRect paddle; 
	
/** declare an random-number instance  */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private double vx, vy;	
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		 setup(); 
		 addMouseListeners();
		 while (true) {  
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
	public void mouseMoved(MouseEvent e){
		if(e.getX()<0){
			paddle.setLocation(0,APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}else if(e.getX()+PADDLE_WIDTH>APPLICATION_WIDTH){
			paddle.setLocation(APPLICATION_WIDTH-PADDLE_WIDTH,APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}else {
			paddle.setLocation(e.getX(),APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		}
	}
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
			}
			
		}
		
		
	}
	public void ballSetup(){
		ball = new GOval(APPLICATION_WIDTH/4,APPLICATION_HEIGHT/2,2*BALL_RADIUS,2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		moveBall();
		vx = rgen.nextDouble(1.0, +3.0);
        if (rgen.nextBoolean(0.5)) vx = -vx; 
        vy=3.0;
		
	}
	public void paddleSetup(){
		paddle = new GRect((APPLICATION_WIDTH-PADDLE_WIDTH)/2, APPLICATION_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		
	}
	private void moveBall() {  
        ball.move(vx,vy);       
	} 
	private void bounceBall(){
		if(ball.getY()<0){
			ball.setLocation(ball.getX(),0);
			vy = -vy;
		}else if(2*BALL_RADIUS>HEIGHT-ball.getY()){
			vy = -vy;			
		}else if(ball.getX()<0){
			vx = -vx;
		}else if(2*BALL_RADIUS>WIDTH - ball.getX()){
			vx = -vx;
		}
	}
	private void checkForCollision() {  
		GObject collider = getCollidingObject();
		if (collider != null){			
			
			if (collider == paddle){
				ball.setLocation(ball.getX(), HEIGHT-PADDLE_Y_OFFSET-BALL_RADIUS*2); //move ball above paddle
				vy = -vy; 
							
			}		
			else if (collider.getWidth() == BRICK_WIDTH){
				vy = -vy; 
				remove(collider); 

			}
		}
	} 
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
