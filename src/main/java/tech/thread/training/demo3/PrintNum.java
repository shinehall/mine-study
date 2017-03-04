package tech.thread.training.demo3;

public class PrintNum implements Runnable {

  private int num;
  
  public PrintNum(int n){
    this.num = n;
  }
  @Override
  public void run() {
    for(int i = 0; i < num; i++){
      System.out.print(i + " ");
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}
