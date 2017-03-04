package test.abstr;

public abstract class AbstractTemplate {
  
  protected abstract void abstractMethod();
  
  protected void doHookMethod(){
  }
  
  protected void concreteMethod(){
    System.out.println("concrete mothod start! =======");
    priavteConcreteMethod();
  }
  
  private void priavteConcreteMethod(){
    System.out.println("private concrete method start! ----------");
  }
  
  public void templateMethod(){
    System.out.println("abstract template begin! ##########");
    abstractMethod();
    doHookMethod();
    concreteMethod();
//    priavteConcreteMethod();
    System.out.println("abstract template over! ^^^^^^^^");
  }
}
