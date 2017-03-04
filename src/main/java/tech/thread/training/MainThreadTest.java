package tech.thread.training;

public class MainThreadTest {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Thread t = Thread.currentThread();
    System.out.println("current thread name: " + t);
    t.setName("new thread");
    System.out.println("after changed thread name: " + t);

    try {
      for (int i = 0; i < 5; i++) {
        System.out.println(i);
        Thread.sleep(1000);

      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
