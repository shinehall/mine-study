package tech.algorithm.sort;

import java.util.Random;

public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] array) throws Exception {
    if (array == null) {
      throw new Exception("array is null!");
    }
    if (array.length == 1) {
      return;
    }
    quickSort(array, 0, array.length - 1);
  }

  private void quickSort(T[] array, int start, int end){
    if(start < end){
      int middle = partition(array, start, end);
      quickSort(array, start, middle);
      quickSort(array, middle + 1, end);
    }
  }
  
  private int partition(T[] array, int start, int end){
    int middle = (start + end) / 2;
    T middleElement = array[middle];
    swap(array, start, middle);
    int index1 = start, index2 = end;
    while(index1 < index2){
      while((index1 < index2) && array[index1].compareTo(middleElement) <= 0){
        index1++;
      }
      while(array[index2].compareTo(middleElement) > 0){
        index2--;
      }
      if(index1 < index2){
        swap(array, index1, index2);
      }
    }
    swap(array, start, index2);
    return index2;
  }
  
  public static void main(String[] args) throws Exception {
    Integer[] testArray = new Integer[10];
    for (int i = 0; i < testArray.length; i++) {
      Random rdn = new Random();
      testArray[i] = rdn.nextInt(150);
      System.out.print(testArray[i] + " ");
    }
    System.out.println();
    AbstractSort<Integer> quickSort = new QuickSort<>();
    quickSort.sort(testArray);
    System.out.println("========================");
    for (int i = 0; i < testArray.length; i++) {
      System.out.print(testArray[i] + " ");
    }
  }

}
