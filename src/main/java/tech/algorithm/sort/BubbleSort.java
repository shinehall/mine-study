package tech.algorithm.sort;

import java.util.Random;

public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

  /*
   * 相邻的两个元素比较，大的那个就往后排
   * */
  @Override
  public void sort(T[] array) throws Exception {
    if (array == null) {
      throw new Exception("array is null!");
    }
    if (array.length == 1) {
      return;
    }
    for(int i = array.length - 1; i >= 0; i--){
      for(int j = 0; j <= i - 1; j++){
        Comparable<T> jValue = (Comparable<T>) array[j];
        if(jValue.compareTo(array[j + 1]) > 0){
          swap(array, j, j + 1);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    Integer[] testArray = new Integer[10];
    for(int i = 0; i < testArray.length; i++){
      Random rdn = new Random();
      testArray[i] = rdn.nextInt(150);
      System.out.print(testArray[i] + " ");
    }
    System.out.println();
    AbstractSort<Integer> bubbleSort = new BubbleSort<>();
    bubbleSort.sort(testArray);
    System.out.println("========================");
    for (int i = 0; i < testArray.length; i++) {
      System.out.print(testArray[i] + " ");
    }
  }

}
