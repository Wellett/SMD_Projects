/* Author: William Ellett
StudentID: 586703
Last Modified: 20/8/2017
Programming and Software Development SWEN30006
MyMailPool

Functions requiring implemetation
- getHighestPriorityMail
- getBestMail
*/
package strategies;

import java.util.LinkedList;
import automail.MailItem;
import automail.PriorityMailItem;

public class MyMailPool implements IMailPool{
  // MAIL POOL
  // Implement linked list as a queue for non priority mail items
  private LinkedList<MailItem> nonPriorityQueue;
  // Implement linked list for Priority mail
  private LinkedList<PriorityMailItem> priorityQueue;

  // CONSTRUCTOR
  public MyMailPool(){
    nonPriorityQueue  = new LinkedList<MailItem>();
    priorityQueue = new LinkedList<PriorityMailItem>();
  }

  //Returns the number of priority mail items in the pool
  public int getPriorityPoolSize(){
    return priorityQueue.size();
  }

  //Returns the number of non-priority main items in the pool
  public int getNonPriorityPoolSize(){
    return nonPriorityQueue.size();
  }

  //Adds an item to the mail pool
  public void addToPool(MailItem mailItem){
    if (mailItem instanceof PriorityMailItem){
      // Cast mailItem to PriorityMailItem and check priority
      PriorityMailItem newPriority = (PriorityMailItem)mailItem;
      int priority = newPriority.getPriorityLevel();
      // Iteratively compare priority to other priority items
      for (int i = 0; i < priorityQueue.size(); i ++){
        int comparePriority = (priorityQueue.get(i)).getPriorityLevel();
        if (priority > comparePriority){
          // add new item before lower priorities, after others
          priorityQueue.add(i, newPriority);
          return; //Exit method
        }
      }
      priorityQueue.addLast(newPriority);
    }
    else {
      nonPriorityQueue.addLast(mailItem);
    }
  }

  // Returns the earliest arrived non-priority mail and deletes from queue
  // Mail items are taken from the end of the list and taken from the start
  public MailItem getNonPriorityMail(){
    return nonPriorityQueue.removeFirst();
  }

  // Returns the earliest of the mail items with the highest priority in the
  // mail pool
  //curretnly just returns the earliest (FIFO)
  public MailItem getHighestPriorityMail(){
    if (getPriorityPoolSize() > 0){
      return priorityQueue.removeFirst();
    }
    else {
      return null;
    }
  }

  //adds returned mail items to the front of the queue
  public void returnToPool(MailItem mailItem){
    if (mailItem instanceof PriorityMailItem){
      addToPool(mailItem);
    }
    else{
      nonPriorityQueue.addFirst(mailItem);
    }
  }

  // Returns the latest nonPriority mailItem from the range of floors specified
  public MailItem getBestMail(int FloorFrom, int FloorTo){
    //Iterate through floors in range
    for (int floor = FloorTo; floor >= FloorFrom; floor--){
      //iterate through nonPriority mail to find the latest piece on the floor
      for (int i = 0; i < nonPriorityQueue.size(); i++){
        if (nonPriorityQueue.get(i).getDestFloor() == floor){
          return nonPriorityQueue.remove(i);
        }
      }
    }
    return getNonPriorityMail();
  }


}
