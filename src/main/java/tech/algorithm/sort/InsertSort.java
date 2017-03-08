package tech.algorithm.sort;

/*
 * 将后面的元素依次与前面的元素进行比较，如果小于就进行交换
 * */
public class InsertSort<T extends Comparable<T>> extends AbstractSort<T>{

  @Override
  public void sort(T[] array) throws Exception {
    {
      if(array == null){
        throw new Exception("array is null!");
      }
      if(array.length == 1){
        return;
      }
      for(int i = 1; i < array.length; i++){
        int index = i;
        //遍历i之前的所有元素，如果小于就换之
        for(int j = i - 1; j >= 0; j--){
          Comparable<T> iValue = (Comparable<T>)array[index];
          if(iValue.compareTo(array[j]) < 0){
            swap(array, index, j);
            index--;
          }else{
            break;
          }
        }
      }
    }    
  }
  
  public static void main(String[] args) throws Exception{
    Integer[] testArray = {12, 3, 5, 9, 6, 1, 4, 23, 7};
    InsertSort<Integer> insertSort = new InsertSort<>();
    insertSort.sort(testArray);
    for(int i = 0; i < testArray.length; i++){
      System.out.print(testArray[i] + " ");
    }
  }


}
