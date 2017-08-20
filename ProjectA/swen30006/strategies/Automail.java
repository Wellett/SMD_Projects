package strategies;

import automail.IMailDelivery;
import automail.Robot;

public class Automail {

	private static final int TEST = 1;
  public Robot robot;
  public IMailPool mailPool;


    public Automail(IMailDelivery delivery) {
    	// Swap between simple provided strategies and your strategies here
			IRobotBehaviour robotBehaviour;

			if (TEST > 0){
				mailPool = new MyMailPool();
				robotBehaviour = new MyRobotBehaviour();
			}
			else {
				mailPool = new SimpleMailPool();
				robotBehaviour = new SimpleRobotBehaviour();
			}

    	/** Initialize robot */
    	robot = new Robot(robotBehaviour, delivery, mailPool);

    }

}
