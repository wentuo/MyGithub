/* File: ChadKarel.java 
 *  
 * A program in which Karel cleans up hanging chads from a 
 * ballot.  The specifications of this program are set out in 
 * "Handout 08: Section Handout #1 
 */ 

import stanford.karel.*; 

public class ChadKarel extends SuperKarel { 
	
    
       public void run() { 
         
             while (frontIsClear()) { 
                    doRectangle(); 
                    move(); 
              } 
              doRectangle(); 
       } 

    
       private void doRectangle() { 
         
              if (noBeepersPresent()) { 
                    removeAllChad(); 
              } 
       } 
     
       private void removeAllChad() { 
             
             turnLeft(); 
              cleanChad(); 

             
             turnAround(); 
              cleanChad(); 

     
             turnLeft(); 
       } 

      
       private void cleanChad() { 
             move(); 
             while (beepersPresent()) { 
                    pickBeeper(); 
              } 
             moveBackward(); 
       } 

     
       private void moveBackward() { 
             turnAround(); 
             move(); 
             turnAround(); 
       } 
} 