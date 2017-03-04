package test.abstr;

public class ConcreteTemplate extends AbstractTemplate {

  @Override
  protected void abstractMethod() {
    System.out.println("this is ConcreteTemplate, abstractMethod start! %%%%%%%%");
  }

  protected void doHookMethod(){
    System.out.println("this is ConcreteTemplate, doHookMethod start! **********");
  }
  
}
