package personal.proj.tech.algorithm.link;

import org.junit.Test;

import tech.algorithm.link.MyLinkNode;
import tech.algorithm.link.problems.DescPrintLink;

public class LinkTest {

  @Test
  public void test() {
    MyLinkNode<Integer> head = buildLink();
    DescPrintLink<Integer> obj = new DescPrintLink<>();
    obj.descPrint(head);
  }

  private MyLinkNode<Integer> buildLink(){
    MyLinkNode<Integer> head = new MyLinkNode<Integer>(1);
    MyLinkNode<Integer> next = head;
    for(int i = 2; i < 20; i++){
      MyLinkNode<Integer> temp = new MyLinkNode<Integer>(i);
      next.setNext(temp);
      next = next.getNext();
    }
    return head;
  }
  
}
