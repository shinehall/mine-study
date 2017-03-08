package tech.algorithm.sort;

import java.util.Random;

public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] array) throws Exception {
    if (array == null) {
      throw new Exception("array is null!");
    }
    if (array.length == 1) {
      return;
    }
    mergeSort(array, 0, array.length - 1);
  }

  private void mergeSort(T[] array, int start, int end) {
    int middle = start + (end - start) / 2;
    if (start < end) {
      mergeSort(array, start, middle);
      mergeSort(array, middle + 1, end);
      merge(array, start, middle, end);
    }
  }

  private void merge(T[] array, int start, int middle, int end) {
    int begin1 = start, last1 = middle;
    int begin2 = middle + 1, last2 = end;
    int index = start;
    T[] tempArray = (T[]) (new Comparable[array.length]);
    while (begin1 <= last1 && begin2 <= last2) {
      if (array[begin1].compareTo(array[begin2]) < 0) {
        tempArray[index] = array[begin1];
        begin1++;
      } else {
        tempArray[index] = array[begin2];
        begin2++;
      }
      index++;
    }
    while (begin1 <= last1) {
      tempArray[index] = array[begin1]; 
      index++;
      begin1++;
    }
    while (begin2 <= last2) {
      tempArray[index] = array[begin2];
      index++;
      begin2++;
    }
    for (int i = start; i <= end; i++) {
      array[i] = tempArray[i];
    }
  }

  public static void main(String[] args) throws Exception {
    Integer[] testArray = new Integer[10];
    for (int i = 0; i < testArray.length; i++) {
      Random rdn = new Random();
      testArray[i] = rdn.nextInt(150);
      System.out.print(testArray[i] + " ");
    }
    System.out.println();
    AbstractSort<Integer> mergeSort = new MergeSort<>();
    mergeSort.sort(testArray);
    System.out.println("========================");
    for (int i = 0; i < testArray.length; i++) {
      System.out.print(testArray[i] + " ");
    }
  }
}
