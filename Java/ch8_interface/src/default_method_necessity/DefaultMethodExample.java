package default_method_necessity;

public class DefaultMethodExample {
    public static void main(String[] args) {
        MyInterface mi1 = new MyclassA();
        mi1.method1();
        mi1.method2();

        MyInterface mi2 = new MyClassB();
        mi2.method1();
        mi2.method2();
    }
}
