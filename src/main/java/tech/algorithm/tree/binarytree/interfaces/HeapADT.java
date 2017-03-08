package tech.algorithm.tree.binarytree.interfaces;

public interface HeapADT<T> extends BinaryTreeADT<T> {

  public void addElement(T element) throws Exception;
  
  public T removeMin() throws Exception;
  
  public T findMin();
  
}
