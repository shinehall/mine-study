package test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectTest {

  private static SimpleClass getSimpleClass() throws Exception{
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    Class clazz = loader.loadClass("test.reflect.SimpleClass");
    System.out.println("clazz classloader:" + clazz.getClassLoader());
    
    Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
    SimpleClass sc = (SimpleClass)cons.newInstance(null);
    
    Method setIndex = clazz.getMethod("setIndex", int.class);
    setIndex.invoke(sc, 10);
    
    Method setText = clazz.getMethod("setText", String.class);
    setText.invoke(sc, "good");
    
    return sc;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      SimpleClass sc = getSimpleClass();
      sc.printParams();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
