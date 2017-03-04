package tech.thread.training.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadDemo1 {
  private final static Logger logger = LoggerFactory.getLogger(ThreadDemo1.class);
  
  public static void main(String[] args) {
    Thread t = new Thread(new NewThread1(5), "my new thread");
    logger.info("start program");
    System.out.println("create a new Thread t:" + t);
    t.start();
    
  }

}
