package test;

import java.util.Arrays;

public class Test {

  public static void main(String[] args) {
//    System.out.println(PaperSize.A3.getHeight());
//    System.out.println(PaperSize.A3.getName());
//    System.out.println(PaperSize.A3.getWidth());

    System.out.println(Arrays.toString(PaperSize.values()));
  }

  public enum PaperSize {

    A4("A4", 21, 29.7),
    A3("A3", 29.7, 42);

    private String name;
    private double width;
    private double height;

    PaperSize(String name, double width, double height) {
      this.name = name;
      this.width = width;
      this.height = height;
    }

    public String getName() {
      return name;
    }

    public double getWidth() {
      return width;
    }

    public double getHeight() {
      return height;
    }
  }

}
