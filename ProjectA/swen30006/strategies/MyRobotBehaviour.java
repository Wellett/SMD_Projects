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
import automail.MailItem;
import automail.PriorityMailItem;
import exceptions.TubeFullException;
import automail.Building;


public class MyRobotBehaviour implements IRobotBehaviour{
  private static final int TUBE_SIZE = 4;
  private static final int FLOORS = Building.FLOORS;
  private static final int LOWEST_FLOOR = Building.LOWEST_FLOOR;
  int newPriority;
  int deliveryFloor;


  //CONSTRUCTOR
  public MyRobotBehaviour(){
    newPriority = 0;
    deliveryFloor = 1;
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
        }
        else if (newPriority > 10){
          return true;
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
				mailPool.addToPool(tube.pop());
			}
			// Check for a top priority item
			if (mailPool.getPriorityPoolSize() > 0) {
				// Add priority mail item
				tube.addItem(mailPool.getHighestPriorityMail());
				// Go deliver that item
				return true;
			}
			else{
        //Take most urgent non-priority mail

				while(tube.getSize()<TUBE_SIZE && mailPool.getNonPriorityPoolSize()>0){
          //collect latest piece of mail
          if (tube.getSize() == 0){

            tube.addItem(mailPool.getNonPriorityMail());
            deliveryFloor = tube.peek().getDestFloor();
          }
          else {
            tube.addItem(getCloseMail(mailPool));
          }
				}
				return (tube.getSize() > 0);
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
		return false;
	}

//attempt to deliver mail in batches

  private MailItem getCloseMail(IMailPool mailPool){
    MailItem bestMail = null;
    //try lower floors first
    for (int topfloor = deliveryFloor; topfloor <= FLOORS; topfloor ++){
      bestMail = mailPool.getBestMail(LOWEST_FLOOR, topfloor);
      if (bestMail != null){
        return bestMail;
      }
    }
    //fail safe
    return mailPool.getNonPriorityMail();
  }
}
