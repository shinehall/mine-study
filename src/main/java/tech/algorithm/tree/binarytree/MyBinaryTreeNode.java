package tech.algorithm.tree.binarytree;

public class MyBinaryTreeNode<T> {
  private T element;
  private MyBinaryTreeNode<T> leftChild;
  private MyBinaryTreeNode<T> rightChild;

  public MyBinaryTreeNode() {
    super();
  }

  public MyBinaryTreeNode(T element, MyBinaryTreeNode<T> leftChild,
      MyBinaryTreeNode<T> rightChild) {
    super();
    this.element = element;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }
  
  public T getElement() {
    return element;
  }
  
  public void setElement(T element) {
    this.element = element;
  }
  
  public MyBinaryTreeNode<T> getLeftChild() {
    return leftChild;
  }
  
  public void setLeftChild(MyBinaryTreeNode<T> leftChild) {
    this.leftChild = leftChild;
  }
  
  public MyBinaryTreeNode<T> getRightChild() {
    return rightChild;
  }
  
  public void setRightChild(MyBinaryTreeNode<T> rightChild) {
    this.rightChild = rightChild;
  }
  
}
