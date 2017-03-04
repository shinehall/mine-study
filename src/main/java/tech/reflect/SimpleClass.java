package tech.reflect;

public class SimpleClass {
  private int index;
  private String text;
  
  public SimpleClass() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  public SimpleClass(int index, String text) {
    super();
    this.index = index;
    this.text = text;
  }
  
  public void printParams(){
    System.out.println("SimpleClass params: index=" + index + " text=" + text);
  }
  
  public int getIndex() {
    return index;
  }
  public void setIndex(int index) {
    this.index = index;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  
  
}
