package tech.algorithm.sort;

public abstract class AbstractSort<T> {

  public abstract  void sort(T[] array) throws Exception;

  protected void swap(T[] array, int index1, int index2){
    T temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
 
}
