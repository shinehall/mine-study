package tech.base;

import tech.base.others.TestClass;

/*
 * java基本类型参数传递方式是值传递（传递的是变量的副本，不改变原有值），那么在传递对象时是否也为值传递？
 * */
public class ParamsTransferType {
  
  //传递对象时传递的实际上是引用对象地址的副本
  public static void testMethod(TestClass obj){
    int temp = obj.a;
    obj.a = obj.b;
    obj.b = temp;
    obj = null; //处理完之后将obj置为空，是否会改变实参？
  }
  
  
  public static void main(String[] args){
    TestClass obj = new TestClass();
    obj.a = 10;
    obj.b = 4;
    System.out.println("before===========" + obj.a + "||||" + obj.b);
    testMethod(obj);
    System.out.println("after============" + obj.a + "||||" + obj.b); 
    //最后结果如下：
    //before===========10||||4
    //after============4||||10
    //说明没有改变实参的内容，传递的实际上是对象引用的副本
  }
}
