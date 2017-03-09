package tech.algorithm.link;

public class MyLinkNode<T extends Comparable<T>> {
  
  private T element;
  private MyLinkNode<T> next;

  public MyLinkNode(T element) {
    super();
    this.element = element;
  }

  public MyLinkNode(T element, MyLinkNode<T> next) {
    super();
    this.element = element;
    this.next = next;
  }
  
  public T getElement() {
    return element;
  }
  public void setElement(T element) {
    this.element = element;
  }
  public MyLinkNode<T> getNext() {
    return next;
  }
  public void setNext(MyLinkNode<T> next) {
    this.next = next;
  }
  
}
