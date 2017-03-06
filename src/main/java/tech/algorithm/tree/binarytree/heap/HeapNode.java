package tech.algorithm.tree.binarytree.heap;

import tech.algorithm.tree.binarytree.MyBinaryTreeNode;

public class HeapNode<T> extends MyBinaryTreeNode<T> {
  private HeapNode<T> parent;
  
  public HeapNode(){
    super();
    parent = null;
  }

  public HeapNode(T element){
    super(element, null, null);
    parent = null;
  }

  public HeapNode<T> getParent() {
    return parent;
  }

  public void setParent(HeapNode<T> parent) {
    this.parent = parent;
  }

  public HeapNode<T> getLeftChild() {
    // TODO Auto-generated method stub
    return (HeapNode<T>) super.getLeftChild();
  }

  @Override
  public HeapNode<T> getRightChild() {
    // TODO Auto-generated method stub
    return (HeapNode<T>) super.getRightChild();
  }
  
  
}
