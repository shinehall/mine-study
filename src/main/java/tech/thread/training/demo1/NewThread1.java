package tech.thread.training.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewThread1 implements Runnable {

  private final static Logger logger = LoggerFactory.getLogger(NewThread1.class);
  
  private int num;
  
  public NewThread1(int i){
    num = i;
    System.out.println("set num in NewThread1 : " + num);
  }
  
  public void run() {
    try{
    for(int i = 0; i < num; i++){
      logger.info("thread start!");
      System.out.println("do thread :" + i);
      Thread.sleep(1000);
    }
    }catch(Exception e){
      logger.info("thread error!");
      e.printStackTrace();
    }
  }

}
