package tech.algorithm.sort;

public class SelectSort<T extends Comparable<T>> extends AbstractSort<T> {

  @Override
  public void sort(T[] array) throws Exception {
    if (array == null) {
      throw new Exception("array is null!");
    }
    if (array.length == 1) {
      return;
    }
    for(int i = 0; i < array.length; i++){
      int minIndex = i;
      for(int j = i + 1; j < array.length; j++){
        Comparable<T> iValue = (Comparable<T>) array[minIndex];
        if(iValue.compareTo(array[j]) > 0){
          minIndex = j;
        }
      }
      if(minIndex != i){
        swap(array, minIndex, i);
      }
    }
  }
  
  public static void main(String[] args) throws Exception {
    Integer[] testArray = { 12, 3, 5, 9, 6, 1, 4, 23, 7 };
    AbstractSort<Integer> selectSort = new SelectSort<>();
    selectSort.sort(testArray);
    for (int i = 0; i < testArray.length; i++) {
      System.out.print(testArray[i] + " ");
    }
  }


}
