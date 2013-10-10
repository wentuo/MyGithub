/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double BIG_RADIUS =72.00;
	private static final double MED_RADIUS =46.80;
	private static final double SMALL_RADIUS =21.60;
	
	public void run() {
		/* x, y is center coordinate
		 */
		double x = getWidth()/2;
		double y = getHeight()/2;
		
		/* calculate and pass the vertical and horizontal location outside Circle
		 * set red color
		 */
		GOval bigcircle = new GOval( (x-BIG_RADIUS) , (y-BIG_RADIUS) , 2*BIG_RADIUS , 2*BIG_RADIUS );
		bigcircle.setFilled(true);
		bigcircle.setFillColor(Color.RED );
		add(bigcircle);
		
		/* calculate and pass the vertical and horizontal location Middle Circle
		 * set white color
		 */
		GOval medcircle = new GOval( (x-MED_RADIUS) , (y-MED_RADIUS) , 2*MED_RADIUS , 2*MED_RADIUS  );
		medcircle.setFilled(true);
		medcircle.setFillColor(Color.WHITE);
		add(medcircle);
		
		/* calculate and pass the vertical and horizontal location Middle Circle
		 * set red color
		 */
		GOval smallcircle = new GOval((x-SMALL_RADIUS) , (y-SMALL_RADIUS), 2*SMALL_RADIUS , 2*SMALL_RADIUS  );
		smallcircle.setFilled(true);
		smallcircle.setFillColor(Color.RED );	
		add(smallcircle);
	}
}
