package tech.thread.training.multiThread;

public class MutiThreadDemo {

  public static void main(String[] args) {
    Thread t1 = new Thread(new NewThread("One", 5));
    Thread t2 = new Thread(new NewThread("Two", 5));
    Thread t3 = new Thread(new NewThread("Three", 5));
    
    System.out.println("current thread:" + Thread.currentThread());
    t1.start();
    t2.start();
    t3.start();
    
    System.out.println("current thread: " + Thread.currentThread() + " over!");
  }

}
