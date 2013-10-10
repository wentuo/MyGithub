/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
		public void run() {
			WelcomeMessage();
			UserInput();
		}
		private void WelcomeMessage() {
			println( "Enter values to compute the Pythagorian theorem" );
		}
		private void UserInput() {
			int a = readInt ("a:"); // enter an integer for a
			int b = readInt ("b:"); //enter an integer for b
			double x = (double)a; // converts variable "a" from an integer to a double
			double c = Math.sqrt((x*x) + (b*b)); //calculates square root
			println("c:"+ c); //displays a double value 
		}
	}
