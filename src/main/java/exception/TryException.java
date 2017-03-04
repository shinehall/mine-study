package exception;

public class TryException {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        comput();
      } catch (Exception e) {
        System.out.println("main error!");
        e.printStackTrace();
      }
    }

    System.out.println("end");
  }

  private static int comput() {
    int rs = 0;
    try {
      rs = 5 / 0;
    } catch (Exception e) {
      throw new RuntimeException("run time");
    }
    return rs;
  }

}
