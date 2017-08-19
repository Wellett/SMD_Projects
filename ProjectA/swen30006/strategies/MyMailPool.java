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
  private LinkedList<MailItem> priorityQueue;

  // CONSTRUCTOR
  public MyMailPool(){
    nonPriorityQueue  = new LinkedList<MailItem>();
    priorityQueue = new LinkedList<MailItem>();
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
      // Add to priority pool
      // currently FIFO, improve later
      priorityQueue.addLast(mailItem);
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

  // Returns the best MailItem to deliver
  // currently not implemented
  public MailItem getBestMail(int FloorFrom, int FloorTo){
    return null;
  }
}
