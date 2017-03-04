package tech.algorithm.tree.binarytree.interfaces;

public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>{

  public void addElement(T element) throws Exception;
  
  public T removeElement(T element) throws Exception;
  
  public void removeAllOcurrences(T element) throws Exception;
  
  public T removeMin() throws Exception;
  
  public T removeMax() throws Exception;
  
  public T findMin() throws Exception;
  
  public T findMax() throws Exception;
}
