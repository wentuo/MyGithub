/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
		private static final int sentinal = 0;
		public void run() {
			WelcomeMessage();
			findRange();
		}
		private void WelcomeMessage() {
			println("This program finds the max and min numbers");
		}
		private void findRange() {
			int inNum = readInt("?"); 
			if (inNum == sentinal) {
				println("this is not a valid first value");
			} //if the first number is the sentinal, displays message
			int minNum = inNum; //the first number is currently the min number
			int MaxNum = inNum; //the first number is also the max number
			/*Pre-condition: the first number does not equal to the sentinal.
			* compares each new number the user enters to the existing min and max numbers,
			* and stores them as the min or max if they are min or max
			*/
			while ( inNum != sentinal) {
				int nextNum = readInt ("?"); //requests user to input the next number
				if (nextNum < minNum) { //compares the new number to the min number
					if (nextNum != sentinal) {
						minNum = nextNum;
					}
				// if the new number is the smaller than the min number,
				//it is now stored as the min number
				}
				if (nextNum > MaxNum) { //compares the new number to the max number
					if (nextNum != sentinal) {
						MaxNum = nextNum;
					}
				//if the new number is larger than the largets number,
				//it is now stored as the max number
				}
				if(nextNum == sentinal) { //if the new number is equal to the sentinal
					println ("min: " + minNum); //prints out min number
					println ("max: " + MaxNum); //pringt out max number
					inNum = sentinal; //assigns "sentinal" to the first number to stop the while loop
				}
			}
		}
	}

