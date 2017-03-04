package tech.thread.training.demo2;

public class NewThread2 extends Thread {

  
  public NewThread2() {
    super("new thread 2");
    System.out.println("in NewThread2");
    this.start();
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    try{
      for(int i = 0; i < 10; i++){
        System.out.println("NewThread2 run:" + i);
        sleep(1000);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  
}
