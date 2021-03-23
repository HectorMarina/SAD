package practica1.Basic;

import java.io.*;
import practica1.Basic.EditableBufferedReader;

class TestReadLine {
  public static void main(String[] args) {
    BufferedReader in = new EditableBufferedReader(
      new InputStreamReader(System.in));
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
  }
}
