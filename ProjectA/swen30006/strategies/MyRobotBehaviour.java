/* Author: William Ellett
StudentID: 586703
Last Modified: 20/8/2017
Programming and Software Development SWEN30006
MyRobotBehaviour

Functions requiring implementation:
- returnToMailRoom
- priorityArrival
- fillStorageTube
*/
package strategies;

import automail.StorageTube;
import automail.Clock;
import automail.PriorityMailItem;
import exceptions.TubeFullException;


public class MyRobotBehaviour implements IRobotBehaviour{
  private static final int TUBE_SIZE = 4;
  int newPriority;


  //CONSTRUCTOR
  public MyRobotBehaviour(){
    newPriority = 0;
  }

  // flag and record arrival of new priority item
  public void priorityArrival(int priority){
    // Record that new priority mail has arrived
    newPriority = priority;
    System.out.println("T: "+Clock.Time()+" | Priority arrived");
  }

  // When this function is true, the robot wil return to the mail room
  //serioiusly, the time loss is hardly worth it
  public boolean returnToMailRoom(StorageTube tube){
      if (tube.getSize() > 0){
        if (tube.peek() instanceof PriorityMailItem){
          return false;
          //int priority = ((PriorityMailItem)tube.peek()).getPriorityLevel();
        }
        else{
          //if (newPriority > 10){
            return true;
          //}
        }
      }
    return false;
  }

  // Returns true when the tube is ready to be delivered by the robot
  public boolean fillStorageTube(IMailPool mailPool, StorageTube tube){
    // Priority items are important;
		// if there are some, grab one and go, otherwise take as many items as we can and go
		try{
			// Start afresh
			newPriority = 0;
			while(!tube.isEmpty()) {
				mailPool.returnToPool(tube.pop());
			}
			// Check for a top priority item
			if (mailPool.getPriorityPoolSize() > 0) {
				// Add priority mail item
				tube.addItem(mailPool.getHighestPriorityMail());
				// Go deliver that item
				return true;
			}
			else{
				// Get as many nonpriority items as available or as fit
				while(tube.getSize() < TUBE_SIZE && mailPool.getNonPriorityPoolSize() > 0) {
					tube.addItem(mailPool.getNonPriorityMail());
				}
				return (tube.getSize() > 0);
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
		return false;
	}
}
