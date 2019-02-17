package format_class;

import java.text.DecimalFormat;

public class DecimalFormatExample {
    public static void main(String[] args) {
        double num = 1234567.89;
        int i = 1;

        DecimalFormat df = new DecimalFormat("0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("0.0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("0000000000.00000");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("#");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("#.#");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("##########.#####");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("#.0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("+#.0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("-#.0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("#,###.0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("0.0E0");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("+#,### ; -#,###");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("#.# %");
        System.out.println(i++ + ". " + df.format(num));

        df = new DecimalFormat("\u00A4 #,###");
        System.out.println(i++ + ". " + df.format(num));
    }
}
