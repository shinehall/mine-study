package tech.encode;

import java.io.UnsupportedEncodingException;

public class TestStringEncode {

  public static void main(String[] args) throws UnsupportedEncodingException {
    // TODO Auto-generated method stub
    if(args == null || args.length <= 0){
      System.out.println("please enter characters");
    }
    String text = args[0];
    System.out.println("enter text:" + text);
    byte[] byte1 = text.getBytes();
    byte[] byte2 = text.getBytes("utf-8");
    System.out.println("array with no encode " + arrayToString(byte1));
    System.out.println("array with utf-8 encode " + arrayToString(byte2));
  }
  
  public static String arrayToString(byte[] bytes){
    StringBuffer buff = new StringBuffer();
    for(int i = 0; i < bytes.length; i++){
      buff.append(bytes[i] + " ");
    }
    return buff.toString();
  }

}
