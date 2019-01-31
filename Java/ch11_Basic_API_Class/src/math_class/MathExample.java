package math_class;

public class MathExample {
    public static void main(String[] args) {
        // 절대값
        int v1 = Math.abs(-5);
        double v2 = Math.abs(-3.14);
        System.out.println("v1= " + v1);
        System.out.println("v2= " + v2);
        System.out.println();

        // 올림 값
        double v3 = Math.ceil(5.3);
        double v4 = Math.ceil(-5.3);
        System.out.println("v3= " + v3);
        System.out.println("v4= " + v4);
        System.out.println();

        // 버림 값
        double v5 = Math.floor(5.3);
        double v6 = Math.floor(-5.3);
        System.out.println("v5= " + v5);
        System.out.println("v6= " + v6);
        System.out.println();

        // 최대값
        int v7 = Math.max(5, 9);
        double v8 = Math.max(5.3, 2.5);
        System.out.println("v7= " + v7);
        System.out.println("v8= " + v8);
        System.out.println();

        // 최소값
        int v9 = Math.min(5, 9);
        double v10 = Math.min(5.3, 2.5);
        System.out.println("v9= " + v9  );
        System.out.println("v10= " + v10);
        System.out.println();

        // 랜덤값
        double v11 = Math.random();
        System.out.println("v11= " + v11);
        System.out.println();

        // 가까운 정수의 실수값
        double v12 = Math.rint(5.3);
        double v17 = Math.rint(5.5);
        double v13 = Math.rint(5.7);
        System.out.println("v12= " + v12);
        System.out.println("v13= " + v13);
        System.out.println("v17= " + v17);
        System.out.println();

        // 반올림값
        long v14 = Math.round(5.3);
        long v15 = Math.round(5.7);
        System.out.println("v14= " + v14);
        System.out.println("v15= " + v15);
        System.out.println();

        double value = 12.3456;
        double temp1 = value * 100;
        long temp2 = Math.round(temp1);
        double v16 = temp2 / 100.0;
        System.out.println("v16= " + v16);
        System.out.println();
    }
}
