package tech.thread.training.multiThread;

public class NewThread implements Runnable {

  private String name;
  private int times;

  public NewThread(String threadName, int threadTimes) {
    name = threadName;
    times = threadTimes;
    System.out.println("create new thread! name:" + name + " ,times:" + times);
  }

  public void run() {
    try {
      for (int i = 0; i < times; i++) {
        System.out.println("sub thread name:" + name + " ,value:" + i);
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("sub thread: " + name + " over!");
    }

  }

}
