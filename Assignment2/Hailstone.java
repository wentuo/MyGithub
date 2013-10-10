/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int n = readInt("?"); 
		int num = 0; 
		while ( n != 1 ) {
			if ( n%2 == 0) { //judge the n whether even
				println (n + " is even, so I take half: " + n/2);
				n = (n/2);
				num++;
			}
			else { //if n is odd,it will do following
				println (n + " is odd, so I make 3n+1: " + (3*n+1));
				n = (3*n +1);
				num++;
			}
		}
			println ("The process took " + num + " to reach 1");
		}
	}

