package tech.algorithm.tree.binarytree;

import tech.algorithm.tree.binarytree.interfaces.BinaryTreeADT;

public class MyBinaryTree<T> implements BinaryTreeADT<T> {

  private MyBinaryTreeNode<T> root;
  private int count;
  
  public MyBinaryTree() {
    super();
  }

  public MyBinaryTree(T element) throws Exception {
    super();
    if(!(element instanceof Comparable)){
      throw new Exception("element is not comparable");
    }
    root = new MyBinaryTreeNode<T>(element, null, null);
    count++;
  }

  @Override
  public T getRootElement() {
    if(root == null){
      return null;
    }
    return root.getElement();
  }

  @Override
  public boolean isEmpty() {
    if(root == null){
      return true;
    }
    return false;
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public boolean contains(T targetElement) throws Exception {
    if(root == null || root.getElement() == null){
      return false;
    }
    if(targetElement == null){
      return false;
    }
    if(!(targetElement instanceof Comparable)){
      throw new Exception("targetElement is not comparable!");
    }
    return contains(targetElement, root);
  }

  private boolean contains(T targetElement, MyBinaryTreeNode<T> root){
    Comparable<T> comparableElement = (Comparable) targetElement;
    if(comparableElement.equals(root.getElement())){
      return true;
    }else{
      if(comparableElement.compareTo(root.getElement()) < 0){
        if(root.getLeftChild() == null){
          return false;
        }else{
          return contains(targetElement, root.getLeftChild());
        }
      }else{
        if(root.getRightChild() == null){
          return false;
        }else{
          return contains(targetElement, root.getRightChild());
        }
      }
    }
  }

  @Override
  public MyBinaryTreeNode<T> findNode(T element) throws Exception {
    if(root == null || root.getElement() == null){
      return null;
    }
    if(element == null){
      throw new Exception("element is null!");
    }
    if(!(element instanceof Comparable)){
      throw new Exception("element is not comparable!");
    }
    return findNode(element, root);
  }
  
  private MyBinaryTreeNode<T> findNode(T element, MyBinaryTreeNode<T> root){
    Comparable<T> comparableElement = (Comparable<T>) element;
    if(comparableElement.equals(root.getElement())){
      return root;
    }else{
      if(comparableElement.compareTo(root.getElement()) < 0){
        if(root.getLeftChild() == null){
          return null;
        }else{
          return findNode(element, root.getLeftChild());
        }
      }else{
        if(root.getRightChild() == null){
          return null;
        }else{
          return findNode(element, root.getRightChild());
        }
      }
    }
  }

  @Override
  public MyBinaryTreeNode<T> getRootNode() {
    return root;
  }
  
}
