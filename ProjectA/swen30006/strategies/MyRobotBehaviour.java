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
package stratagies;

import automail.StorageTube;

public class MyRobotBehaviour implements IRobotBehaviour{
  private static final int TUBE_SIZE = 4;
  boolean newPriority;


  // flag and record arrival of new priority item
  // simple function copied direct from example
  public void priorityArrival(int priority){
    // Record that a new one has arrived
    newPriority = true;
    System.out.println("T: "+Clock.Time()+" | Priority arrived");
  }

  // When this function is true, the robot wil return to the mail room
  public boolean returnToMailRoom(StorageTube tube){
    return
  }

  // Returns true when the tube is ready to be delivered by the robot
  public boolean fillStorageTube(IMailPool mailPool, StorageTube tube){
    //check if the contents of the tube are priority mail
    Boolean priority = (tube.peek() instanceof PriorityMailItem);
    if (prioirity){
      //do some comparison
    }
    return false;
  }
}
