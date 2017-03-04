package tech.algorithm.tree.binarytree.interfaces;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>{

  public void addElement(T element) throws Exception;
  
  public T removeElement(T element) throws Exception;
  
  public void removeAllOcurrences(T element) throws Exception;
  
  public T removeMin();
  
  public T removeMax();
  
  public T findMin();
  
  public T findMax();
}
