/* Author: William Ellett
StudentID: 586703
Last Modified: 20/8/2017
Programming and Software Development SWEN30006
MyMailPool

Functions requiring implemetation
- getPriorityPoolsize
- getNonPriorityPoolSize
- addToPool
- getNonPriorityMail
- getHighestPriorityMail
- getBestMail
*/
import java.util.LinkedList;
import automail.MailItem;


public class MyMailPool implements IMailPool{
  // MAIL POOL
  // Implement linked list as a queue for non priority mail items
  private LinkedList<MailItem> nonPriorityPool;
  // Implement linked list for Priority mail items
  private LinkedList<MailItem> priorityPool;

  // CONSTRUCTOR
  public MyMailPool(){
    nonPriorityPool = new LinkedList<MailItem>();
    priorityPool = new LinkedList<MailItem>();
  }

  //Returns the number of priority mail items in the pool
  public int getPriorityPoolsize(){
    return priorityPool.size();
  }

  //Returns the number of non-priority main items in the pool
  public int getNonPriorityPoolSize(){
    return nonPriorityPool.size();
  }

  //Adds an item to the mail pool
  public void addToPool(MailItem mailItem){

  }

  // Returns the earliest arrived non-priority mail
  // Mail items are taken from the end of the list and taken from the start
  public MailItem getNonPriorityMail(){
    return nonPriorityPool.getFirst();
  }

  // Returns the earliest of the mail items with the highest priority in the
  // mail pool
  public MailItem getHighestPriorityMail(){

  }

  // Returns the best MailItem to deliver
  public MailItem getBestMail(int FloorFrom, int FloorTo){

  }
}
