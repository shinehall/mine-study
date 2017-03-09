package tech.algorithm.link.problems;

import tech.algorithm.link.MyLinkNode;

/*
 * 递归方式倒序打印一个单链表
 * */
public class DescPrintLink<T extends Comparable<T>> {

  public void descPrint(MyLinkNode<T> head) {
    if (head == null) {
      return;
    }
    print(head);
  }

  private void print(MyLinkNode<T> node) {
    if (node != null) {
      print(node.getNext());
      String rs = node.getElement().toString();
      System.out.println(rs);
    }
  }
}
