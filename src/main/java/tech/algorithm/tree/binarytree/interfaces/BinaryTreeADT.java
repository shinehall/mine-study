package tech.algorithm.tree.binarytree.interfaces;

import tech.algorithm.tree.binarytree.MyBinaryTreeNode;

public interface BinaryTreeADT<T> {
  
  public T getRootElement();
  
  public boolean isEmpty();
  
  public int size();
  
  public boolean contains(T targetElement) throws Exception;
  
  public MyBinaryTreeNode<T> findNode(T element) throws Exception;
  
  public MyBinaryTreeNode<T> getRootNode();

}
