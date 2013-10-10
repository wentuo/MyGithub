/* File: ChadKarel.java 
 *  
 * A program in which Karel cleans up hanging chads from a 
 * ballot.  The specifications of this program are set out in 
 * "Handout 08: Section Handout #1 
 */ 

import stanford.karel.*; 

public class ChadKarel extends SuperKarel { 
       /* Precondition:  Karel stands at the start of the ballot. 
        * Postcondition: Karel is at the end of the ballot and all chad 
        *                has been cleared. 
        */ 
       public void run() { 
             /* To avoid the fencepost problem, we split the logic into 
              * a loop to process rectangles, plus one final call to 
              * check the last rectangle. 
              */ 
             while (frontIsClear()) { 
                    processRectangle(); 
                    move(); 
              } 
              processRectangle(); 
       } 

       /* Precondition:  Karel is standing in the center of a rectangle, 
        *                facing East. 
        * Postcondition: Karel is standing in the center of the rectangle, 
        *                facing East, and all chad has been cleared. 
        */ 
       private void processRectangle() { 
             /* If there is chad to clear, clear that chad from the 
              * ballot. 
              */ 
              if (noBeepersPresent()) { 
                    removeAllChad(); 
              } 
       } 
       /* Precondition:  Karel is standing in the center of a rectangle 
        *                that needs to be cleared. 
        * Postcondition: Karel is standing in the center of a rectangle 
        *                that has been emptied. 
        */ 
       private void removeAllChad() { 
             /* Clean the upper corner. */ 
             turnLeft(); 
              cleanChad(); 

             /* Clean the lower corner. */ 
             turnAround(); 
              cleanChad(); 

             /* Fix Karel so that he's now facing East. */ 
             turnLeft(); 
       } 

       /* Precondition:  Karel is facing in some direction just below a 
        *                corner to be cleared of chad. 
        * Postcondition: Karel is facing in the same direction in the 
        *                same location, but all chad has been cleared 
        *                from the corner. 
        */ 
       private void cleanChad() { 
             move(); 
             while (beepersPresent()) { 
                    pickBeeper(); 
              } 
             moveBackward(); 
       } 

       /* Precondition:  Karel is facing some direction. 
        * Postcondition: Karel is facing the same direction, but has taken 
        *                a step backward. 
        */ 
       private void moveBackward() { 
             turnAround(); 
             move(); 
             turnAround(); 
       } 
} 