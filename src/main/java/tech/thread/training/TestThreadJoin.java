package tech.thread.training;

public class TestThreadJoin {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Thread t1 = new Thread(new Runnable(){
      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          System.out.println("thread t1--ongoing");
          Thread.sleep(5000);
          System.out.println("thread t1--ending");
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    Thread t2 = new Thread(new Runnable(){
      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          System.out.println("thread t2--ongoing");
          Thread.sleep(5000);
          System.out.println("thread t2--ending");
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    try {
      System.out.println("main thread is start!");
      System.out.println("thread1 is start!");
      t1.start();
      t1.join();
      System.out.println("thread1 is end");
      
      System.out.println("thread2 is start!");
      t2.start();
      t2.join();
      System.out.println("thread2 is end!");
      System.out.println("main thread is end!");
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
