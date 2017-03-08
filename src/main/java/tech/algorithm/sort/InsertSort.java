package tech.algorithm.sort;

public class InsertSort {

  private static <T> void sort(T[] array) throws Exception{
    if(array == null){
      throw new Exception("array is null!");
    }
    if(array.length > 0 && !(array[0] instanceof Comparable)){
      throw new Exception("element in array is not comparable!");
    }
    
  }
  
  public static void main(String[] args){
    System.out.println("hello world!");
  }

}
