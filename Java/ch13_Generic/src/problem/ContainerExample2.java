package problem;

public class ContainerExample2 {
    public static void main(String[] args) {
        Container1<String, String> container1 = new Container1<String, String>();
        container1.set("홍길동", "도적");
        String name1 = container1.getKey();
        String jon = container1.getValue();

        Container1<String, Integer> container2 = new Container1<String, Integer>();
        container2.set("홍길동", 35);
        String name2 = container2.getKey();
        int age = container2.getValue();
    }
}

class Container1<T, M> {
    T t;
    M m;

    public void set(T t, M m) {
        this.t = t;
        this.m = m;
    }

    public T getKey() {
        return t;
    }

    public M getValue() {
        return m;
    }
}