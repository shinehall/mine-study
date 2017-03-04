package tech.thread.training.demo3;

public class PrintTest {

  public static void main(String[] args) throws InterruptedException {

    System.out.println("thread start!");
    Thread thread1 = new Thread(new PrintChar('a', 100));
    Thread thread2 = new Thread(new PrintNum(100));
    
    thread1.start();
    thread2.start();
    
    thread1.join();
    thread2.join();
    
    System.out.println("main thread die");
  }

}
