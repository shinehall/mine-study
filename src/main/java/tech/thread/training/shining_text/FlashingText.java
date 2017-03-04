package tech.thread.training.shining_text;

import javax.swing.JApplet;
import javax.swing.JLabel;

public class FlashingText extends JApplet implements Runnable {

  private JLabel jlblText = new JLabel("Welcome", JLabel.CENTER);
  
  public FlashingText(){
    add(jlblText);
    new Thread(this).start();
  }
  @Override
  public void run() {
    // TODO Auto-generated method stub
    try{
      while(true){
        if(jlblText.getText() == null){
          jlblText.setText("Welcome");
        }else{
          jlblText.setText(null);
        }
        Thread.sleep(200);
      }
    }catch (InterruptedException ex){
      ex.printStackTrace();
    }
  }
}
