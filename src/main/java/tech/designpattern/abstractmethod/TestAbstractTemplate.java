package tech.designpattern.abstractmethod;

public class TestAbstractTemplate {

  public static void main(String[] args) {
    AbstractTemplate tmpl = new ConcreteTemplate();
    tmpl.templateMethod();
  }

}
