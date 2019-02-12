package class_member_using;

public class UsingThis {
    public int outterField = 10;

    class Inner {
        int innerField = 20;

        void method() {
            // 람다식
            MyFunctionalInterface fi = () -> {
                System.out.println("outterField: " + outterField);

                // 바깥 객체의 참조를 얻기 위해서는
                // 클래스명.this 를 사용
                System.out.println("outterField: " + UsingThis.this.outterField +
                        "\n");

                System.out.println("innerField: " +
                        innerField);

                // 람다식 내부에서는 this 는
                // Inner 객체를 참조
                System.out.println("innerField: " +
                        this.innerField + "\n");
            };
            fi.method();
        }
    }
}
