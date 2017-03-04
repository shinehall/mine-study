package tech.thread.training.demo3;

public class PrintChar implements Runnable {

  private char charactor;
  private int times;
  
  public PrintChar(char c, int t){
    this.charactor = c;
    this.times = t;
  }
  @Override
  public void run() {
    for(int i = 0; i < times; i++){
      System.out.print(charactor);
    }
  }

}
