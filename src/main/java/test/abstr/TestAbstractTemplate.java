package test.abstr;

public class TestAbstractTemplate {

  public static void main(String[] args) {
    AbstractTemplate tmpl = new ConcreteTemplate();
    tmpl.templateMethod();
  }

}
